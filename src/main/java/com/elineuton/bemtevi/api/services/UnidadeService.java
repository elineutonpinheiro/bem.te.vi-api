package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.dto.UnidadeDTO;
import com.elineuton.bemtevi.api.repositories.UnidadeRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class UnidadeService {
	
	@Autowired
	private UnidadeRepository repo;
	
	@Autowired 
	private InstituicaoService instituicaoService;
	
	public List<Unidade> listar() {
		return repo.findAll();
	}
	
	public Unidade consultaPorId(Integer id) {
		Optional<Unidade> unidade = repo.findById(id);
		return unidade.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Unidade.class.getName()));
	}
	
	public Unidade inserir(Unidade unidade) {
		unidade = repo.save(unidade);
		return unidade;
	}
	
	public Unidade atualizar(Unidade unidade, Integer id) {
		Unidade unidadeSalva = repo.findById(id).get();
		
		if(unidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(unidade, unidadeSalva, "id");
		return repo.save(unidadeSalva);
	}
	
	public void remover(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir entidades que possuem relacionamentos");
		}	
	}
	
	public Unidade fromDTO(UnidadeDTO unidadeDto) {
		Instituicao instituicao = instituicaoService.consultaPorId(unidadeDto.getInstituicaoId());
		Unidade unidade = new Unidade(unidadeDto.getNome(), unidadeDto.getEndereco(), 
				unidadeDto.getTelefone(), unidadeDto.getEmail(), instituicao, null);
		return unidade;
	}

}
