package com.elineuton.bemtevi.api.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.repositories.InstituicaoRepository;

@Service
public class InstituicaoService {
	
	@Autowired
	private InstituicaoRepository repo;
	
	
	public List<Instituicao> listar() {
		return repo.findAll();
	}
	
	public Instituicao consultaPorId(Integer id) {
		Instituicao obj = repo.findById(id).orElse(null);
		return obj;
	}

}
