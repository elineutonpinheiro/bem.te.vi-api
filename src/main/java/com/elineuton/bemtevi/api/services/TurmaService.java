package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.dto.TurmaDTO;
import com.elineuton.bemtevi.api.dto.TurmaNewDTO;
import com.elineuton.bemtevi.api.repositories.TurmaRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repo;

	@Autowired
	private UnidadeService unidadeService;

	@Autowired
	private AnoLetivoService anoLetivoService;
	
	@Autowired
	private ProfissionalService profissionalService;

	public List<Turma> listar() {
		return repo.findAll();
	}

	public Turma consultarPorId(Integer id) {
		Optional<Turma> turma = repo.findById(id);
		return turma.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Turma.class.getName()));
	}

	public Turma inserir(Turma turma) {
		turma = repo.save(turma);
		return turma;
	}

	public Turma atualizar(Turma turma, Integer id) {
		Turma turmaSalva = repo.findById(id).get();
		
		if(turmaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(turma, turmaSalva, "id");
		return repo.save(turmaSalva);
	}

	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}
	}

	// TODO Trocar os NULL pelos IDs de Turma e Ano Letivo
	public Turma fromDTO(TurmaDTO turmaDto) {
		return new Turma(turmaDto.getNome(), null, null, null, null, null);
	}

	public Turma fromDTO(TurmaNewDTO turmaDto) {
		Unidade unidade = unidadeService.consultaPorId(turmaDto.getUnidadeId());
		AnoLetivo anoLetivo = anoLetivoService.consultaPorId(turmaDto.getAnoLetivoId());
		Turma turma = new Turma(turmaDto.getNome(), unidade, turmaDto.getPeriodo(), 
				turmaDto.getSala(), anoLetivo, null);
		return turma;
	}

	// TODO Implementar busca paginada - Udemy
	public List<Turma> consultaTurmasPorProfissionalId(Integer id) {
		Profissional profissional = profissionalService.consultarPorId(id);
		List<Turma> lista = repo.findByProfissional(profissional);
		return lista;
	}
	
	public List<Turma> consultarTurmasPorEmailProfissional(String email) {
		Profissional profissional = profissionalService.consultarPorEmail(email);
		List<Turma> lista = repo.findByProfissional(profissional);
		return lista;
	}

}
