package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.repositories.AnoLetivoRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class AnoLetivoService {
	
	@Autowired
	private AnoLetivoRepository repo;
	
	
	public List<AnoLetivo> listar() {
		return repo.findAll();
	}
	
	public AnoLetivo consultaPorId(Integer id) {
		Optional<AnoLetivo> anoLetivo = repo.findById(id);
		return anoLetivo.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + AnoLetivo.class.getName()));
	}
	
	public AnoLetivo inserir(AnoLetivo anoLetivo) {
		anoLetivo = repo.save(anoLetivo);
		return anoLetivo;
	}
	
	public AnoLetivo atualizar(AnoLetivo anoLetivo, Integer id) {
		AnoLetivo anoLetivoSalvo = repo.findById(id).get();
		
		if(anoLetivoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(anoLetivo, anoLetivoSalvo, "id");
		return repo.save(anoLetivoSalvo);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir instituição que possui turmas");
		}
	}

}
