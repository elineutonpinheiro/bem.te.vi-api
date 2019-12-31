package com.elineuton.bemtevi.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.elineuton.bemtevi.api.dto.AtividadeDTO;
import com.elineuton.bemtevi.api.services.AtividadeService;

@RestController
@RequestMapping("/atividades")
public class AtividadeResource {
	
	@Autowired
	private AtividadeService service;
	
	@GetMapping
	public ResponseEntity<List<AtividadeDTO>> listar(){
		List<Atividade> lista = service.listar();
		List<AtividadeDTO> listaDto = lista.stream().map(obj -> new AtividadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> consultarPorId(@PathVariable Integer id) {
		Atividade obj = service.consultarPorId(id);
		return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Atividade> inserir(@Valid @RequestBody AtividadeDTO objDto) {
		Atividade obj = service.fromDTO(objDto);
		obj = service.inserir(obj);
		
		//Mapear o recurso -> instituicao + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Atividade> atualizar(@Valid @RequestBody AtividadeDTO objDto, @PathVariable Integer id) {
		Atividade obj = service.fromDTO(objDto);
		obj = service.atualizar(obj, id);
		return ResponseEntity.ok(obj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

}
