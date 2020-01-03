package com.elineuton.bemtevi.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.AlunoDTO;
import com.elineuton.bemtevi.api.dto.AtividadeDTO;
import com.elineuton.bemtevi.api.dto.TurmaDTO;
import com.elineuton.bemtevi.api.dto.TurmaNewDTO;
import com.elineuton.bemtevi.api.services.AlunoService;
import com.elineuton.bemtevi.api.services.AtividadeService;
import com.elineuton.bemtevi.api.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

	@Autowired
	private TurmaService service;

	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private AlunoService alunoService;

	@GetMapping
	public ResponseEntity<List<TurmaDTO>> listar() {
		List<Turma> lista = service.listar();
		List<TurmaDTO> listaDto = lista.stream().map(obj -> new TurmaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> consultarPorId(@PathVariable Integer id) {
		Turma obj = service.consultarPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Turma> inserir(@Valid @RequestBody TurmaNewDTO objDto) {
		Turma obj = service.fromDTO(objDto);
		obj = service.inserir(obj);

		// Mapear o recurso -> instituicao + id

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Turma> atualizar(@Valid @RequestBody TurmaNewDTO objDto, @PathVariable Integer id) {
		Turma obj = service.fromDTO(objDto);
		obj = service.atualizar(obj, id);
		return ResponseEntity.ok(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/atividades")
	public ResponseEntity<List<AtividadeDTO>> consultaAtividadesPorTurmaId(@PathVariable Integer id) {
		List<Atividade> lista = atividadeService.consultaAtividadesPorTurmaId(id);
		List<AtividadeDTO> listaDto = lista.stream().map(obj -> new AtividadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

	@GetMapping("/{id}/alunos")
	public ResponseEntity<List<AlunoDTO>> consultaAlunosPorTurmaId(@PathVariable Integer id) {
		List<Aluno> lista = alunoService.consultaAlunosPorTurmaId(id);
		List<AlunoDTO> listaDto = lista.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

}
