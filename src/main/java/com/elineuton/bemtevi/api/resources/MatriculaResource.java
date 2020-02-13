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

import com.elineuton.bemtevi.api.domain.Matricula;
import com.elineuton.bemtevi.api.dto.MatriculaDTO;
import com.elineuton.bemtevi.api.repositories.AlunoRepository;
import com.elineuton.bemtevi.api.services.MatriculaService;

@RestController
@RequestMapping("/matriculas")
public class MatriculaResource {
	
	@Autowired
	private MatriculaService service;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public ResponseEntity<List<MatriculaDTO>> listar(){
		List<Matricula> lista = service.listar();
		List<MatriculaDTO> listaDto = lista.stream().map(matricula -> new MatriculaDTO(matricula))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@PostMapping
	public ResponseEntity<Matricula> inserir(@Valid @RequestBody MatriculaDTO matriculaDto) {
		Matricula matricula = service.fromDTO(matriculaDto);
		alunoRepository.save(matriculaDto.getAluno());
		//matricula.setAluno(matriculaDto.getAluno());
		matricula = service.inserir(matricula);
		
		//Mapear o recurso -> matricula + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(matricula.getId()).toUri();
		
		return ResponseEntity.created(uri).body(matricula);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Matricula> atualizar(@Valid @RequestBody MatriculaDTO matriculaDTO, @PathVariable Integer id) {
		Matricula matricula = service.fromDTO(matriculaDTO);
		matricula = service.atualizar(matricula, id);
		return ResponseEntity.ok(matricula);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

}
