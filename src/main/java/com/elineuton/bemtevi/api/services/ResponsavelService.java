package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Responsavel;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.ResponsavelDTO;
import com.elineuton.bemtevi.api.dto.ResponsavelNewDTO;
import com.elineuton.bemtevi.api.repositories.ResponsavelRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class ResponsavelService {
	
	@Autowired
	private ResponsavelRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<Responsavel> listar() {
		return repo.findAll();
	}
	
	public Responsavel consultarPorId(Integer id) {
		Optional<Responsavel> responsavel = repo.findById(id);
		return responsavel.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Responsavel.class.getName()));
	}
	
	public Responsavel consultarPorEmail(String email) {
		Responsavel responsavel = repo.findByEmail(email);
		return responsavel;
	}
	
	
	public Responsavel inserir(Responsavel responsavel) {
		responsavel = repo.save(responsavel);
		return responsavel;
	}
	
	public Responsavel atualizar(Responsavel responsavel, Integer id) {
		Responsavel responsavelSalvo = repo.findById(id).get();
		
		if(responsavelSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(responsavel, responsavelSalvo, "id");
		return repo.save(responsavelSalvo);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}
		
	}
	
	public Responsavel fromDTO(ResponsavelDTO responsavelDTO) {
		return new Responsavel(responsavelDTO.getNome(), responsavelDTO.getParentesco(), null, null, null);
	}
	
	public Responsavel fromDTO(ResponsavelNewDTO responsavelNewDTO) {
		return new Responsavel(responsavelNewDTO.getNome(), 
				responsavelNewDTO.getParentesco(), 
				passwordEncoder.encode(responsavelNewDTO.getEmail()), 
				passwordEncoder.encode(responsavelNewDTO.getSenha()), 
				responsavelNewDTO.getAtivo());
	}

}
