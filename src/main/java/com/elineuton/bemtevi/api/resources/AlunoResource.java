package com.elineuton.bemtevi.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService service;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> listar(){
		List<Aluno> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> consultarPorId(@PathVariable Integer id) {
		Aluno obj = service.consultaPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}

}
