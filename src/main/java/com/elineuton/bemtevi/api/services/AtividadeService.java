package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.AtividadeDTO;
import com.elineuton.bemtevi.api.repositories.AtividadeRepository;
import com.elineuton.bemtevi.api.repositories.filter.AtividadeFilter;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repo;
	
	@Autowired
	private TurmaService turmaService;
	
	/*
	 * public List<Atividade> listar() { return repo.findAll(); }
	 */
	
	public List<Atividade> pesquisar(Turma turma, AtividadeFilter atividadeFilter) {
		return repo.filtrar(turma, atividadeFilter);
	}
	
	public Atividade consultarPorId(Integer id) {
		Optional<Atividade> atividade = repo.findById(id);
		return atividade.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Atividade.class.getName()));
	}
	
	public Atividade inserir(Atividade atividade) {
		atividade = repo.save(atividade);
		return atividade;
	}
	
	public Atividade atualizar(Atividade atividade, Integer id) {
		Atividade atividadeSalva = repo.findById(id).get();
		
		if(atividadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(atividade, atividadeSalva, "id");
		return repo.save(atividadeSalva);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir atividades que possuem entidades relacionadas");
		}
	}
	
	public Atividade fromDTO(AtividadeDTO atividadeDto) {
		Turma turma = turmaService.consultarPorId(atividadeDto.getTurmaId());
		Atividade atividade = new Atividade(atividadeDto.getDescricao(), atividadeDto.getDataCriacao(), turma);
		return atividade;
	}
	
	public List<Atividade> consultarAtividadesPorTurmaId(Integer id) {
		Turma turma = turmaService.consultarPorId(id);
		List<Atividade> lista = repo.findByTurma(turma);
		return lista;
	}
	
	/* Busca Atividade por Turma e Descrição
	 * 
	public List<Atividade> pesquisar(Integer turmaId, String descricao) {
		Turma turma = turmaService.consultarPorId(turmaId);
		List<Atividade> lista = repo.findByTurmaAndDescricaoContaining(turma, descricao);
		return lista;
	}
	
	*/
	
	/* Implementado em TurmaResource
	 *
	
	public List<Atividade> consultarPorTurmaEDescricao(Integer turmaId, String descricao) {
		Turma turma = turmaService.consultarPorId(turmaId);
		List<Atividade> lista = repo.findByTurmaAndDescricaoContaining(turma, descricao);
		return lista;
	}
	*/

}
