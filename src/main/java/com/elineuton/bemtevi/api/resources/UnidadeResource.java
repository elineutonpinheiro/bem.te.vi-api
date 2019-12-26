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

import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {
	
	@Autowired
	private UnidadeService service;
	
	@GetMapping
	public ResponseEntity<List<Unidade>> listar(){
		List<Unidade> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Unidade> consultarPorId(@PathVariable Integer id) {
		Unidade obj = service.consultaPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Unidade> inserir(@Valid @RequestBody Unidade obj) {
		Unidade objSalvo = service.inserir(obj);
		
		//Mapear o recurso -> instituicao + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(objSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(objSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Unidade> atualizar(@Valid @RequestBody Unidade obj, @PathVariable Integer id) {
		Unidade objSalvo = service.atualizar(obj, id);
		return ResponseEntity.ok(objSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

}
