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
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.AlunoDTO;
import com.elineuton.bemtevi.api.dto.AlunoNewDTO;
import com.elineuton.bemtevi.api.repositories.AlunoRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repo;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private AlunoService alunoService;

	public List<Aluno> listar() {
		return repo.findAll();
	}

	public Aluno consultarPorId(Integer id) {
		Optional<Aluno> aluno = repo.findById(id);
		return aluno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
	}

	public Aluno inserir(Aluno aluno) {
		aluno = repo.save(aluno);
		return aluno;
	}

	public Aluno atualizar(Aluno aluno, Integer id) {
		Aluno alunoSalvo = repo.findById(id).get();
		
		if(alunoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(aluno, alunoSalvo, "id");
		return repo.save(alunoSalvo);
	}

	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}
	}

	public Aluno fromDTO(AlunoDTO alunoDto) {
		return new Aluno(alunoDto.getNome(), null, null);
	}

	public Aluno fromDTO(AlunoNewDTO alunoDto) {
		Aluno aluno = alunoService.consultarPorId(alunoDto.getAlunoId());
		//aluno.setPessoalAutorizado(alunoDto.getPessoalAutorizado());
		aluno.getPessoalAutorizado().addAll(alunoDto.getPessoalAutorizado());
		return aluno;
	}

	public List<Aluno> consultarAlunosPorTurmaId(Integer id) {
		Turma turma = turmaService.consultarPorId(id);
		List<Aluno> lista = repo.findByTurma(turma);
		return lista;
	}

	public void atualizarPresenca(Integer id, LocalDate dataPresenca) {
		Aluno alunoSalvo = alunoService.consultarPorId(id);
		alunoSalvo.setDataPresenca(dataPresenca);
		repo.save(alunoSalvo);
	}

	/*
	public void atualizarPessoalAutorizado(Integer id, Set<String> pessoalAutorizado) {
		Aluno alunoSalvo = alunoService.consultarPorId(id);
		alunoSalvo.setPessoalAutorizado(pessoalAutorizado);
		repo.save(alunoSalvo);
	}
	*/
	
	public void removerPessoalAutorizado(Integer id, String pessoaAutorizada) {
		try {
			Aluno alunoSalvo = alunoService.consultarPorId(id);
			alunoSalvo.getPessoalAutorizado().remove(pessoaAutorizada);
			repo.save(alunoSalvo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}
	}

}
