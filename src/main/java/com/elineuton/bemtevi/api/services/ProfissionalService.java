package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

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
	
	public Profissional inserir(Profissional obj) {
		Profissional objSalvo = repo.save(obj);
		return objSalvo;
	}
	
	public Profissional atualizar(Profissional obj, Integer id) {
		Profissional objSalvo = repo.findById(id).get();
		
		if(objSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(obj, objSalvo, "id");
		return repo.save(objSalvo);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}
		
	}

}
