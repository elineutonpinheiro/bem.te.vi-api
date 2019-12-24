package com.elineuton.bemtevi.api.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.services.exceptions.ObjectNotFoundException;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;

@Service
public class ProfissionalService {
	
	@Autowired
	private ProfissionalRepository repo;
	
	
	public List<Profissional> listar() {
		return repo.findAll();
	}
	
	public Profissional consultaPorId(Integer id) {
		Optional<Profissional> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Profissional.class.getName()));
	}

}
