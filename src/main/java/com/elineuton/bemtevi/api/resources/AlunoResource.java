package com.elineuton.bemtevi.api.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.dto.AlunoDTO;
import com.elineuton.bemtevi.api.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService service;
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> listar(){
		List<Aluno> lista = service.listar();
		List<AlunoDTO> listaDto = lista.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> consultarPorId(@PathVariable Integer id) {
		Aluno obj = service.consultaPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizar(@Valid @RequestBody AlunoDTO objDto, @PathVariable Integer id) {
		Aluno obj = service.fromDTO(objDto);
		obj = service.atualizar(obj, id);
		return ResponseEntity.ok(obj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
