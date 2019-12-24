package com.elineuton.bemtevi.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.services.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalResource {
	
	@Autowired
	private ProfissionalService service;
	
	@GetMapping
	public ResponseEntity<List<Profissional>> listar(){
		List<Profissional> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Profissional> consultarPorId(@PathVariable Integer id) {
		Profissional obj = service.consultaPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}

}
