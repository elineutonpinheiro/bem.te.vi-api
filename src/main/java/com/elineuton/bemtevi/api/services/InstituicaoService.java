package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
		Optional<Instituicao> instituicao = repo.findById(id);
		return instituicao.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Instituicao.class.getName()));
	}
	
	public Instituicao inserir(Instituicao instituicao) {
		instituicao = repo.save(instituicao);
		return instituicao;
	}
	
	public Instituicao atualizar(Instituicao instituicao, Integer id) {
		Instituicao instituicaoSalva = repo.findById(id).get();
		
		if(instituicaoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(instituicao, instituicaoSalva, "id");
		return repo.save(instituicaoSalva);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir instituição que possui turmas");
		}
	}
	
}
