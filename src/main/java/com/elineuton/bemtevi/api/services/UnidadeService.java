package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.dto.UnidadeDTO;
import com.elineuton.bemtevi.api.repositories.InstituicaoRepository;
import com.elineuton.bemtevi.api.repositories.UnidadeRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class UnidadeService {
	
	@Autowired
	private UnidadeRepository repo;
	
	@Autowired InstituicaoRepository instituicaoRepository;
	
	@Autowired InstituicaoService instituicaoService;
	
	public List<Unidade> listar() {
		return repo.findAll();
	}
	
	public Unidade consultaPorId(Integer id) {
		Optional<Unidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Unidade.class.getName()));
	}
	
	public Unidade inserir(Unidade obj) {
		instituicaoRepository.save(obj.getInstituicao());
		obj = repo.save(obj);
		return obj;
	}
	
	public Unidade atualizar(Unidade obj, Integer id) {
		Unidade newObj = consultaPorId(id);
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
	
	public Unidade fromDTO(UnidadeDTO objDto) {
		Instituicao instituicao = instituicaoService.consultaPorId(objDto.getInstituicaoId());
		Unidade unidade = new Unidade(null, objDto.getNome(), objDto.getEndereco(), objDto.getTelefone(), objDto.getEmail(), objDto.getStatus(), instituicao);
		return unidade;
	}
	
	private void updateData (Unidade newObj, Unidade obj) {
		newObj.setNome(obj.getNome());
		newObj.setEndereco(obj.getEndereco());
		newObj.setTelefone(obj.getTelefone()); 
		newObj.setEmail(obj.getEmail());
		newObj.setStatus(obj.getStatus());
		newObj.setInstituicao(obj.getInstituicao());
	}

}
