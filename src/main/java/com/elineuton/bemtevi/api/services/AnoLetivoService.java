package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		Optional<AnoLetivo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + AnoLetivo.class.getName()));
	}
	
	public AnoLetivo inserir(AnoLetivo obj) {
		obj = repo.save(obj);
		return obj;
	}
	
	public AnoLetivo atualizar(AnoLetivo obj, Integer id) {
		AnoLetivo newObj = consultaPorId(id);
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir instituição que possui turmas");
		}
		
	}
	
	private void updateData (AnoLetivo newObj, AnoLetivo obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setDataInicial(obj.getDataInicial());
		newObj.setDataFinal(obj.getDataFinal());
	}

}
