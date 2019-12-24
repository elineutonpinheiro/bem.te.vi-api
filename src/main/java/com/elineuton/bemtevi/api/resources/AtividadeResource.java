package com.elineuton.bemtevi.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.services.AtividadeService;

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

}
