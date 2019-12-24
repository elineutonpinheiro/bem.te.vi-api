package com.elineuton.bemtevi.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.domain.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {
	
	@Autowired
	private TurmaService service;
	
	@GetMapping
	public ResponseEntity<List<Turma>> listar(){
		List<Turma> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> consultarPorId(@PathVariable Integer id) {
		Turma obj = service.consultaPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}

}
