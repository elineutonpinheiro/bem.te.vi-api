package com.elineuton.bemtevi.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.domain.services.AnoLetivoService;

@RestController
@RequestMapping("/anosletivos")
public class AnoLetivoResource {
	
	@Autowired
	private AnoLetivoService service;
	
	@GetMapping
	public ResponseEntity<List<AnoLetivo>> listar(){
		List<AnoLetivo> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AnoLetivo> consultarPorId(@PathVariable Integer id) {
		AnoLetivo obj = service.consultaPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}

}
