package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.dto.ProfissionalDTO;
import com.elineuton.bemtevi.api.dto.ProfissionalNewDTO;
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
	
	public Profissional consultarPorId(Integer id) {
		Optional<Profissional> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Profissional.class.getName()));
	}
	
	public Profissional inserir(Profissional obj) {
		obj = repo.save(obj);
		return obj;
	}
	
	public Profissional atualizar(Profissional obj, Integer id) {
		Profissional newObj = consultarPorId(id);
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}
		
	}
	
	public Profissional fromDTO(ProfissionalDTO objDto) {
		return new Profissional(objDto.getId(), objDto.getNome(), objDto.getCargo(), objDto.getTelefone(), null, null);
	}
	
	public Profissional fromDTO(ProfissionalNewDTO objDto) {
		Profissional profissional = new Profissional(null, objDto.getNome(), objDto.getCargo(), objDto.getTelefone(), objDto.getEmail(), objDto.getSenha());
		return profissional;
	}
	
	private void updateData (Profissional newObj, Profissional obj) {
		newObj.setNome(obj.getNome());
		newObj.setCargo(obj.getCargo());
		newObj.setTelefone(obj.getTelefone());
		newObj.setEmail(obj.getEmail());
		newObj.setSenha(obj.getSenha());
	}

}
