package com.elineuton.bemtevi.api.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.domain.services.exceptions.ObjectNotFoundException;
import com.elineuton.bemtevi.api.repositories.AnoLetivoRepository;

@Service
public class AnoLetivoService {
	
	@Autowired
	private AnoLetivoRepository repo;
	
	
	public List<AnoLetivo> listar() {
		return repo.findAll();
	}
	
	public AnoLetivo consultaPorId(Integer id) {
		Optional<AnoLetivo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + AnoLetivo.class.getName()));
	}

}
