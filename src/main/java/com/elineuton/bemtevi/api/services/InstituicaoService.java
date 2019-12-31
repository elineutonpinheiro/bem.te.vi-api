package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.repositories.InstituicaoRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

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
		obj = repo.save(obj);
		return obj;
	}
	
	public Instituicao atualizar(Instituicao obj, Integer id) {
		Instituicao newObj = consultaPorId(id);
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
	
	private void updateData (Instituicao newObj, Instituicao obj) {
		newObj.setNome(obj.getNome());
	}

}
