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

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.ProfissionalDTO;
import com.elineuton.bemtevi.api.dto.ProfissionalNewDTO;
import com.elineuton.bemtevi.api.dto.TurmaDTO;
import com.elineuton.bemtevi.api.services.ProfissionalService;
import com.elineuton.bemtevi.api.services.TurmaService;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalResource {

	@Autowired
	private ProfissionalService service;
	
	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<List<ProfissionalDTO>> listar() {
		List<Profissional> lista = service.listar();
		List<ProfissionalDTO> listaDto = lista.stream().map(obj -> new ProfissionalDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Profissional> consultarPorId(@PathVariable Integer id) {
		Profissional obj = service.consultarPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Profissional> inserir(@Valid @RequestBody ProfissionalNewDTO objDto) {
		Profissional obj = service.fromDTO(objDto);
		obj = service.inserir(obj);

		// Mapear o recurso -> instituicao + id

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Profissional> atualizar(@Valid @RequestBody ProfissionalNewDTO objDto,
			@PathVariable Integer id) {
		Profissional obj = service.fromDTO(objDto);
		obj = service.atualizar(obj, id);
		return ResponseEntity.ok(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/turmas")
	public ResponseEntity<List<TurmaDTO>> consultaTurmasPorProfissionalId(@PathVariable Integer id) {
		List<Turma> lista = turmaService.consultaTurmasPorProfissionalId(id);
		List<TurmaDTO> listaDto = lista.stream().map(obj -> new TurmaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

}
