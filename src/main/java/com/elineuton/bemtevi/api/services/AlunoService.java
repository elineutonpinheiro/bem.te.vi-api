package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.repositories.AlunoRepository;
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

}
