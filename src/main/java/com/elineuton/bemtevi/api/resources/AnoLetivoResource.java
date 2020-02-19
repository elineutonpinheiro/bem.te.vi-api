package com.elineuton.bemtevi.api.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.services.AnoLetivoService;

@RestController
@RequestMapping("/anosletivos")
public class AnoLetivoResource {
	
	@Autowired
	private AnoLetivoService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<AnoLetivo>> listar(){
		List<AnoLetivo> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<AnoLetivo> consultarPorId(@PathVariable Integer id) {
		AnoLetivo anoLetivo = service.consultaPorId(id);
		return anoLetivo != null ? ResponseEntity.ok(anoLetivo) : ResponseEntity.notFound().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<AnoLetivo> inserir(@Valid @RequestBody AnoLetivo anoLetivo) {
		AnoLetivo anoLetivoSalvo = service.inserir(anoLetivo);
		
		//Mapear o recurso -> anoLetivo + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(anoLetivoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(anoLetivoSalvo);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<AnoLetivo> atualizar(@Valid @RequestBody AnoLetivo anoLetivo, @PathVariable Integer id) {
		AnoLetivo anoLetivoSalvo = service.atualizar(anoLetivo, id);
		return ResponseEntity.ok(anoLetivoSalvo);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

}
