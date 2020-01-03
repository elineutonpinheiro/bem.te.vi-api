package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	public List<Aluno> listar() {
		return repo.findAll();
	}

	public Aluno consultarPorId(Integer id) {
		Optional<Aluno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
	}

	public Aluno inserir(Aluno obj) {
		obj = repo.save(obj);
		return obj;
	}

	public Aluno atualizar(Aluno obj, Integer id) {
		Aluno newObj = consultarPorId(id);
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir alunos que possuem entidades relacionadas");
		}
	}

	public Aluno fromDTO(AlunoDTO objDto) {
		return new Aluno(objDto.getId(), objDto.getNome(), objDto.getSobrenome(), null);
	}

	public Aluno fromDTO(AlunoNewDTO objDto) {
		Aluno aluno = new Aluno(null, objDto.getNome(), objDto.getSobrenome(), objDto.getDataNascimento());
		aluno.getPessoalAutorizado().addAll(objDto.getPessoalAutorizado());
		return aluno;
	}

	private void updateData(Aluno newObj, Aluno obj) {
		newObj.setNome(obj.getNome());
		newObj.setSobrenome(obj.getSobrenome());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setPessoalAutorizado(obj.getPessoalAutorizado());
	}

	public List<Aluno> consultaAlunosPorTurmaId(Integer id) {
		Turma turma = turmaService.consultarPorId(id);
		List<Aluno> lista = repo.findByTurma(turma);
		return lista;
	}

}
