package com.elineuton.bemtevi.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Matricula;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.MatriculaDTO;
import com.elineuton.bemtevi.api.repositories.MatriculaRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository repo;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private AlunoService alunoService;

	
	public List<Matricula> listar() { 
		return repo.findAll(); 
	}
	  
	/*
	 * public Matricula consultarPorId(Integer id) { Optional<Matricula> matricula =
	 * repo.findById(id); return matricula.orElseThrow(() -> new ObjectNotFoundException(
	 * "Objeto não encontrado! Id: " + id + ", Tipo: " +
	 * Matricula.class.getName())); }
	 */
	  
	public List<Matricula> consultarMatriculaPorAlunoId(Integer id) {
		Aluno aluno = alunoService.consultarPorId(id);
		List<Matricula> lista = repo.findByAluno(aluno);
		return lista;
	}
		 

	public Matricula inserir(Matricula matricula) {
		matricula = repo.save(matricula);
		return matricula;
	}

	
	public Matricula atualizar(Matricula matricula, Integer id) { 
		Matricula matriculaSalva = repo.findById(id).get();
		
		if(matriculaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(matricula, matricula, "id");
		return repo.save(matriculaSalva);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir alunos que possuem entidades relacionadas");
		}
	}
	   
	public Matricula fromDTO(MatriculaDTO matriculaDto) {
		Turma turma = turmaService.consultarPorId(matriculaDto.getTurmaId());
		Matricula matricula = new Matricula(matriculaDto.getAluno(), turma, matriculaDto.getDataTermino());
		return matricula;
	}

}
