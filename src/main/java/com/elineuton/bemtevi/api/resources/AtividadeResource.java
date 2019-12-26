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

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.services.AtividadeService;

@RestController
@RequestMapping("/atividades")
public class AtividadeResource {
	
	@Autowired
	private AtividadeService service;
	
	@GetMapping
	public ResponseEntity<List<Atividade>> listar(){
		List<Atividade> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> consultarPorId(@PathVariable Integer id) {
		Atividade obj = service.consultaPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Atividade> inserir(@Valid @RequestBody Atividade obj) {
		Atividade objSalvo = service.inserir(obj);
		
		//Mapear o recurso -> instituicao + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(objSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(objSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Atividade> atualizar(@Valid @RequestBody Atividade obj, @PathVariable Integer id) {
		Atividade objSalvo = service.atualizar(obj, id);
		return ResponseEntity.ok(objSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

}
