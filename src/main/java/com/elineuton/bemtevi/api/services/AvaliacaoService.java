package com.elineuton.bemtevi.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Avaliacao;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Questionario;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.domain.enums.Perfil;
import com.elineuton.bemtevi.api.dto.AvaliacaoDTO;
import com.elineuton.bemtevi.api.dto.AvaliacaoNewDTO;
import com.elineuton.bemtevi.api.repositories.AvaliacaoRepository;
import com.elineuton.bemtevi.api.security.Usuario;
import com.elineuton.bemtevi.api.services.exceptions.AuthorizationException;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository repo;
	
	@Autowired 
	private AlunoService alunoService;
	
	@Autowired
	private TurmaService turmaService;
	
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
	
	public Avaliacao inserir(Avaliacao avaliacao) {
		LocalDate a = LocalDate.now();
		avaliacao.setData(a);
		
		//Usuario usuario = UsuarioService.authenticated();
		//Profissional profissional = profissionalService.consultarPorId(usuario.getId());
		//avaliacao.setProfissional(profissional);
		
		/*
		 * for (Resposta resposta : avaliacao.getRespostas()) {
		 * resposta.setQuestao(questaoRepository.findById(resposta.getQuestao().getId())
		 * .get()); }
		 */

		avaliacao = repo.save(avaliacao);
		return avaliacao;
	}
	
	public Avaliacao atualizar(Avaliacao avaliacao, Integer id) {
		Avaliacao avaliacaoSalva = repo.findById(id).get();
		
		if(avaliacaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(avaliacao, avaliacaoSalva, "id");
		return repo.save(avaliacaoSalva);
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
		Avaliacao avaliacao = new Avaliacao(aluno, profissional, avaliacaoDto.getData(), 
				avaliacaoDto.getStatus(), questionario);
		return avaliacao;
	}
	
	public List<Avaliacao> consultaAvaliacaoPorAlunoId(Integer id) {
		Aluno aluno = alunoService.consultarPorId(id);
		List<Avaliacao> lista = repo.findByAluno(aluno);
		return lista;
	}
	
	
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
