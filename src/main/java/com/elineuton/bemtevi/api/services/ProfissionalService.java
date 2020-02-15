package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.enums.Perfil;
import com.elineuton.bemtevi.api.dto.ProfissionalDTO;
import com.elineuton.bemtevi.api.dto.ProfissionalNewDTO;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class ProfissionalService {
	
	@Autowired
	private ProfissionalRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<Profissional> listar() {
		return repo.findAll();
	}
	
	public Profissional consultarPorId(Integer id) {
		Optional<Profissional> profissional = repo.findById(id);
		return profissional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Profissional.class.getName()));
	}
	
	public Profissional inserir(Profissional profissional) {
		profissional = repo.save(profissional);
		return profissional;
	}
	
	public Profissional atualizar(Profissional profissional, Integer id) {
		Profissional profissionalSalvo = repo.findById(id).get();
		
		if(profissionalSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(profissional, profissionalSalvo, "id");
		return repo.save(profissionalSalvo);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}
		
	}
	
	public Profissional fromDTO(ProfissionalDTO profissionalDto) {
		return new Profissional(profissionalDto.getNome(), profissionalDto.getCargo(), 
				profissionalDto.getTelefone(), null, null, null, null);
	}
	
	public Profissional fromDTO(ProfissionalNewDTO profissionalNewDto) {
		return new Profissional(profissionalNewDto.getNome(), profissionalNewDto.getCargo(), 
				profissionalNewDto.getTelefone(), 
				passwordEncoder.encode(profissionalNewDto.getCodigoAcesso()), 
				passwordEncoder.encode(profissionalNewDto.getSenha()), 
				profissionalNewDto.getAtivo(), Perfil.PROFISSIONAL);
	}
	

}
