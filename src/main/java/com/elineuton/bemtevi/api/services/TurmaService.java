package com.elineuton.bemtevi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.dto.TurmaDTO;
import com.elineuton.bemtevi.api.dto.TurmaNewDTO;
import com.elineuton.bemtevi.api.repositories.TurmaRepository;
import com.elineuton.bemtevi.api.services.exceptions.DataIntegrityException;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repo;

	@Autowired
	private UnidadeService unidadeService;

	@Autowired
	private AnoLetivoService anoLetivoService;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private TurmaRepository turmaRepository;

	public List<Turma> listar() {
		return repo.findAll();
	}

	public Turma consultarPorId(Integer id) {
		Optional<Turma> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Turma.class.getName()));
	}

	public Turma inserir(Turma obj) {
		obj = repo.save(obj);
		return obj;
	}

	public Turma atualizar(Turma obj, Integer id) {
		Turma newObj = consultarPorId(id);
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

	// TODO Trocar os NULL pelos IDs de Turma e Ano Letivo
	public Turma fromDTO(TurmaDTO objDto) {
		return new Turma(objDto.getId(), objDto.getNome(), null, null, null, null, null);
	}

	public Turma fromDTO(TurmaNewDTO objDto) {
		Unidade unidade = unidadeService.consultaPorId(objDto.getUnidadeId());
		AnoLetivo anoLetivo = anoLetivoService.consultaPorId(objDto.getAnoLetivoId());
		Turma turma = new Turma(null, objDto.getNome(), unidade, objDto.getPeriodo(), objDto.getSala(),
				objDto.getStatus(), anoLetivo);
		return turma;
	}

	private void updateData(Turma newObj, Turma obj) {
		newObj.setNome(obj.getNome());
		newObj.setUnidade(obj.getUnidade());
		newObj.setPeriodo(obj.getPeriodo());
		newObj.setSala(obj.getSala());
		newObj.setStatus(obj.getStatus());
		newObj.setAnoLetivo(obj.getAnoLetivo());
	}

	public List<Turma> consultaTurmasPorProfissionalId(Integer id) {
		Profissional profissional = profissionalService.consultarPorId(id);
		List<Turma> lista = turmaRepository.findByProfissional(profissional);
		return lista;
	}

}
