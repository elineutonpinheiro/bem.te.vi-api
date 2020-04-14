package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.dto.ProfissionalDTO;
import com.elineuton.bemtevi.api.dto.ProfissionalNewDTO;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;
import com.elineuton.bemtevi.api.repositories.TurmaRepository;
import com.elineuton.bemtevi.api.repositories.UnidadeRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class ProfissionalService {
	
	@Autowired
	private ProfissionalRepository repo;
	
	@Autowired
	private InstituicaoService instituicaoService;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private UnidadeRepository unidadeRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<Profissional> listar() {
		return repo.findAll();
	}
	
	public Profissional consultarPorId(Integer id) {
		/*
		Usuario usuario = UsuarioService.authenticated();
		if (usuario == null || !usuario.hasRole(Perfil.ADMIN) && !id.equals(usuario.getId())) {
			throw new AuthorizationException("Acesso negado");
			
		}
		*/
		Optional<Profissional> profissional = repo.findById(id);
		return profissional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Profissional.class.getName()));
	}
	
	public Profissional consultarPorEmail(String email) {
		Profissional profissional = repo.findByEmail(email);
		return profissional;
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
	
	public Profissional fromDTO(ProfissionalDTO profissionalDTO) {
		return new Profissional(profissionalDTO.getNome(), profissionalDTO.getCargo(), null, null, profissionalDTO.isAtivo());
	}
	
	public Profissional fromDTO(ProfissionalNewDTO profissionalNewDTO) {
		return new Profissional(profissionalNewDTO.getNome(), profissionalNewDTO.getCargo(), 
				passwordEncoder.encode(profissionalNewDTO.getEmail()), 
				passwordEncoder.encode(profissionalNewDTO.getSenha()), 
				profissionalNewDTO.getAtivo());
	}
	
	public List<Profissional> consultarProfissionaisPorInstituicaoId(Integer id) {
		Instituicao instituicao = instituicaoService.consultaPorId(id);
		List<Profissional> lista = repo.findByInstituicao(instituicao);
		
		/*
		 * for (Profissional profissional : lista) {
		 * profissional.setTurmas(turmaRepository.findNomeTurmaByProfissional(
		 * profissional));
		 * profissional.setUnidades(unidadeRepository.findNomeUnidadeByProfissional(
		 * profissional)); }
		 */
		
		return lista;
	}
	

}
