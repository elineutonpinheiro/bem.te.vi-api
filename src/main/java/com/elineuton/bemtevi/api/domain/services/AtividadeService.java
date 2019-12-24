package com.elineuton.bemtevi.api.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.services.exceptions.ObjectNotFoundException;
import com.elineuton.bemtevi.api.repositories.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repo;
	
	
	public List<Atividade> listar() {
		return repo.findAll();
	}
	
	public Atividade consultaPorId(Integer id) {
		Optional<Atividade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Atividade.class.getName()));
	}

}
