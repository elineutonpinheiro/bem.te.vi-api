package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.dto.AlunoDTO;
import com.elineuton.bemtevi.api.repositories.AlunoRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository repo;
	
	
	public List<Aluno> listar() {
		return repo.findAll();
	}
	
	public Aluno consultaPorId(Integer id) {
		Optional<Aluno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Aluno.class.getName()));
	}
	
	public Aluno inserir(Aluno obj) {
		Aluno objSalvo = repo.save(obj);
		return objSalvo;
	}
	
	public Aluno atualizar(Aluno obj, Integer id) {
		Aluno newObj = consultaPorId(id);
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
		return new Aluno(objDto.getId(), objDto.getNome(), objDto.getSobrenome(), null, null);
	}
	
	private void updateData (Aluno newObj, Aluno objSalvo) {
		newObj.setNome(objSalvo.getNome());
		newObj.setSobrenome(objSalvo.getSobrenome());
	}

}
