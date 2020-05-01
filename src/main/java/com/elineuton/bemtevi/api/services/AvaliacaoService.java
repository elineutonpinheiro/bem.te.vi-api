package com.elineuton.bemtevi.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Avaliacao;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Questionario;
import com.elineuton.bemtevi.api.dto.AvaliacaoDTO;
import com.elineuton.bemtevi.api.repositories.AvaliacaoRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository repo;
	
	@Autowired 
	private AlunoService alunoService;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	public List<Avaliacao> listar() {
		return repo.findAll();
	}
	
	public Avaliacao consultaPorId(Integer id) {
		Optional<Avaliacao> avaliacao = repo.findById(id);
		return avaliacao.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Avaliacao.class.getName()));
	}
	
	public Avaliacao salvar(Avaliacao avaliacao) {
		LocalDate a = LocalDate.now();
		avaliacao.setData(a);
		
		Avaliacao avaliacaoTemp = buscarAvaliacaoPorAlunoIdEData(avaliacao.getAluno().getId(), avaliacao.getData());
		
		if(avaliacaoTemp != null && avaliacao.getId() == null) {
			return avaliacaoTemp;
		}

		avaliacao = repo.save(avaliacao);
		return avaliacao;
	}
	
	public Avaliacao atualizar(Avaliacao avaliacao) {
		return repo.save(avaliacao);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}	
	}
	
	public Avaliacao fromDTO(AvaliacaoDTO avaliacaoDto) {
		Aluno aluno = alunoService.consultarPorId(avaliacaoDto.getAlunoId());
		Profissional profissional = profissionalService.consultarPorId(avaliacaoDto.getProfissionalId());
		Questionario questionario = new Questionario(avaliacaoDto.getCafeDaManha(), avaliacaoDto.getLancheDaManha(),
				avaliacaoDto.getAlmoco(), avaliacaoDto.getLancheDaTarde(), avaliacaoDto.getBanho(), avaliacaoDto.getFralda(),
				avaliacaoDto.getEscovacao(), avaliacaoDto.isDormiu(), avaliacaoDto.getEstadoDoSono(), avaliacaoDto.isFebre(),
				avaliacaoDto.getUrina(), avaliacaoDto.getEvacuacao(), avaliacaoDto.getInteracao(), avaliacaoDto.getParticipacao(),
				avaliacaoDto.getObservacao());
		Avaliacao avaliacao = new Avaliacao(avaliacaoDto.getId(), aluno, profissional, avaliacaoDto.getData(), 
				avaliacaoDto.getStatus(), questionario);
		return avaliacao;
	}
	
	public Avaliacao buscarAvaliacaoAndamento(Integer id, LocalDate data) {
		Aluno aluno = alunoService.consultarPorId(id);
		Avaliacao avaliacao = repo.findByAlunoAndData(aluno, data);
		
		if(avaliacao == null) {
			avaliacao = new Avaliacao();
		}
		return avaliacao;
	}
	

	public Avaliacao buscarAvaliacaoPorAlunoIdEData(Integer alunoId, LocalDate data) {
		Aluno aluno = alunoService.consultarPorId(alunoId);
		Avaliacao avaliacao = repo.findByAlunoAndData(aluno, data);
	
		return avaliacao;
	}
	
	
	public AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.addMappings(new PropertyMap<Avaliacao, AvaliacaoDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAlunoId(source.getAluno().getId());
				map().setProfissionalId(source.getProfissional().getId());
				map().setData(source.getData());
				map().setStatus(source.getStatus());
				map().setCafeDaManha(source.getQuestionario().getCafeDaManha());
				map().setLancheDaManha(source.getQuestionario().getLancheDaManha());
				map().setAlmoco(source.getQuestionario().getAlmoco());
				map().setLancheDaTarde(source.getQuestionario().getLancheDaTarde());
				map().setBanho(source.getQuestionario().getBanho());
				map().setFralda(source.getQuestionario().getFralda());
				map().setEscovacao(source.getQuestionario().getEscovacao());
				map().setDormiu(source.getQuestionario().isDormiu());
				map().setEstadoDoSono(source.getQuestionario().getEstadoDoSono());
				map().setFebre(source.getQuestionario().isFebre());
				map().setUrina(source.getQuestionario().getUrina());
				map().setEvacuacao(source.getQuestionario().getEvacuacao());
				map().setInteracao(source.getQuestionario().getInteracao());
				map().setParticipacao(source.getQuestionario().getParticipacao());
				map().setObservacao(source.getQuestionario().getObservacao());
			}
			
		});
		
	    AvaliacaoDTO avaliacaoDTO = modelMapper.map(avaliacao, AvaliacaoDTO.class);
	    return avaliacaoDTO;
	}
	
	
	/*
	 * public List<Avaliacao> consultarAvaliacaoPorAlunoIdEData(Integer id,
	 * LocalDate data) { Aluno aluno = alunoService.consultarPorId(id);
	 * List<Avaliacao> lista = repo.findByAlunoAndData(aluno, data); return lista; }
	 */
	
	public List<Avaliacao> consultarAvaliacaoPorAlunoId(Integer id) {
		Aluno aluno = alunoService.consultarPorId(id);
		List<Avaliacao> lista = repo.findByAluno(aluno);
		return lista;
	}
	
	/*
	public List<Avaliacao> consultaAvaliacaoPorProfissionald(Integer id) {
		Usuario usuario = UsuarioService.authenticated();
		if (usuario == null || !usuario.hasRole(Perfil.ADMIN) && !id.equals(usuario.getId())) {
			throw new AuthorizationException("Acesso negado");
			
		}
		
		Profissional profissional = profissionalService.consultarPorId(id);
		List<Avaliacao> lista = repo.findByProfissional(profissional);
		return lista;
	}
	
	
	public List<Avaliacao> consultarAvaliacaoPorTurmaId(Integer id) {
		Turma turma = turmaService.consultarPorId(id);
		List<Avaliacao> lista = repo.findByTurma(turma);
		return lista;
	}
	*/
	
	/*
	public List<Avaliacao> consultarAvaliacaoPorTurmaEProfissional(Integer turmaId, Integer profissionalId) {
		Turma turma = turmaService.consultarPorId(turmaId);
		//Se não informar id do profissional, então busca apenas por turma
		if(profissionalId == null) {
			List<Avaliacao> lista = repo.findByTurma(turma);
			return lista;
		}
		Profissional profissional = profissionalService.consultarPorId(profissionalId);
		List<Avaliacao> lista = repo.findByTurmaEProfissional(turma, profissional);
		return lista;
	}
	*/
	
	/*
	 	public List<Avaliacao> consultarAvaliacaoPorTurma(Integer id) { Turma turma =
	 	turmaService.consultarPorId(id); List<Avaliacao> lista =
	 	repo.findByTurma(turma); return lista; }
	 */

}
