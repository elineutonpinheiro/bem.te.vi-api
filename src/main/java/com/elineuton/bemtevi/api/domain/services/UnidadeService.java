package com.elineuton.bemtevi.api.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.domain.services.exceptions.ObjectNotFoundException;
import com.elineuton.bemtevi.api.repositories.UnidadeRepository;

@Service
public class UnidadeService {
	
	@Autowired
	private UnidadeRepository repo;
	
	
	public List<Unidade> listar() {
		return repo.findAll();
	}
	
	public Unidade consultaPorId(Integer id) {
		Optional<Unidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Unidade.class.getName()));
	}

}
