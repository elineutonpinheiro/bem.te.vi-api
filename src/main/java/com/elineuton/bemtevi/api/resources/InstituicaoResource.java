package com.elineuton.bemtevi.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.services.InstituicaoService;

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
		Instituicao obj = service.consultaPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}

}
