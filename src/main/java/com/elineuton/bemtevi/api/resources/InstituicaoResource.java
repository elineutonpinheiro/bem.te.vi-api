package com.elineuton.bemtevi.api.resources;

import java.net.URI;
import java.util.List;

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

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.services.InstituicaoService;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoResource {
	
	@Autowired
	private InstituicaoService service;
	
	@GetMapping
	public ResponseEntity<List<Instituicao>> listar(){
		List<Instituicao> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Instituicao> consultarPorId(@PathVariable Integer id) {
		Instituicao instituicao = service.consultaPorId(id);
		return instituicao != null ? ResponseEntity.ok(instituicao) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Instituicao> inserir(@Valid @RequestBody Instituicao instituicao) {
		Instituicao instituicaoSalvo = service.inserir(instituicao);
		
		//Mapear o recurso -> instituicao + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(instituicaoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(instituicaoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Instituicao> atualizar(@Valid @RequestBody Instituicao instituicao, @PathVariable Integer id) {
		Instituicao instituicaoSalvo = service.atualizar(instituicao, id);
		return ResponseEntity.ok(instituicaoSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	 
}
