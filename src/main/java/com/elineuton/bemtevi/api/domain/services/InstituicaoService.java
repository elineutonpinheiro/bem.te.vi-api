package com.elineuton.bemtevi.api.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.services.exceptions.ObjectNotFoundException;
import com.elineuton.bemtevi.api.repositories.InstituicaoRepository;

@Service
public class InstituicaoService {
	
	@Autowired
	private InstituicaoRepository repo;
	
	
	public List<Instituicao> listar() {
		return repo.findAll();
	}
	
	public Instituicao consultaPorId(Integer id) {
		Optional<Instituicao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Instituicao.class.getName()));
	}
	
	public Instituicao inserir(Instituicao obj) {
		Instituicao objSalvo = repo.save(obj);
		return objSalvo;
	}
	
	public Instituicao atualizar(Instituicao obj, Integer id) {
		Instituicao objSalvo = repo.findById(id).get();
		
		if(objSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(obj, objSalvo, "id");
		return repo.save(objSalvo);
	}
	
	public void remover(Integer id) {
		repo.deleteById(id);
	}

}
