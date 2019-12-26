package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.repositories.AtividadeRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

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
	
	public Atividade inserir(Atividade obj) {
		Atividade objSalvo = repo.save(obj);
		return objSalvo;
	}
	
	public Atividade atualizar(Atividade obj, Integer id) {
		Atividade objSalvo = repo.findById(id).get();
		
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
			throw new DataIntegrityException("Não é possível excluir instituição que possui turmas");
		}
		
	}

}
