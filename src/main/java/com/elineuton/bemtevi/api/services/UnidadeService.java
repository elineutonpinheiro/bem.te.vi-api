package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.repositories.UnidadeRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

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
	
	public Unidade inserir(Unidade obj) {
		Unidade objSalvo = repo.save(obj);
		return objSalvo;
	}
	
	public Unidade atualizar(Unidade obj, Integer id) {
		Unidade objSalvo = repo.findById(id).get();
		
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