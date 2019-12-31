package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.AtividadeDTO;
import com.elineuton.bemtevi.api.repositories.AtividadeRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repo;
	
	@Autowired
	private TurmaService turmaService;
	
	public List<Atividade> listar() {
		return repo.findAll();
	}
	
	public Atividade consultarPorId(Integer id) {
		Optional<Atividade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Atividade.class.getName()));
	}
	
	public Atividade inserir(Atividade obj) {
		obj = repo.save(obj);
		return obj;
	}
	
	public Atividade atualizar(Atividade obj, Integer id) {
		Atividade newObj = consultarPorId(id);
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
	
	public Atividade fromDTO(AtividadeDTO objDto) {
		Turma turma = turmaService.consultarPorId(objDto.getTurmaId());
		Atividade atividade = new Atividade(objDto.getId(), objDto.getDescricao(), objDto.getDataHora(), turma);
		return atividade;
	}
	
	private void updateData (Atividade newObj, Atividade obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setDataHora(obj.getDataHora());
		newObj.setTurma(obj.getTurma());
	}
	
	public List<Atividade> consultaAtividadesPorTurmaId(Integer id) {
		Turma turma = turmaService.consultarPorId(id);
		List<Atividade> lista = repo.findByTurma(turma);
		return lista;
	}

}
