package com.elineuton.bemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Lotacao;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.LotacaoDTO;
import com.elineuton.bemtevi.api.repositories.LotacaoRepository;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;

@Service
public class LotacaoService {

	@Autowired
	private LotacaoRepository repo;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private TurmaService turmaService;

	@Autowired 
	private ProfissionalService profissionalService;
	 

	public List<Lotacao> listar() {
		return repo.findAll();
	}


	public List<Lotacao> consultarLotacaoPorProfissionalId(Integer id) {
		Profissional profissional = profissionalService.consultarPorId(id);
		List<Lotacao> lista = repo.findByProfissional(profissional);
		return lista;
	}
		 

	public Lotacao inserir(Lotacao lotacao) {
		lotacao = repo.save(lotacao);
		return lotacao;
	}

	
	public Lotacao atualizar(Lotacao lotacao, Integer id) { 
		Lotacao lotacaoSalva = repo.findById(id).get();
		
		if(lotacaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(lotacao, lotacao, "id");
		return repo.save(lotacaoSalva);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir profissionals que possuem entidades relacionadas");
		}
	}
	   
	public Lotacao fromDTO(LotacaoDTO lotacaoDto) {
		Turma turma = turmaService.consultarPorId(lotacaoDto.getTurmaId());
		profissionalRepository.save(lotacaoDto.getProfissional());
		Lotacao lotacao = new Lotacao(lotacaoDto.getProfissional(), turma, lotacaoDto.getDataInicio(), lotacaoDto.getDataTermino());
		return lotacao;
	}

}
