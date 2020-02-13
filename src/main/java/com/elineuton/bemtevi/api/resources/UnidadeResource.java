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

import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.dto.UnidadeDTO;
import com.elineuton.bemtevi.api.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {
	
	@Autowired
	private UnidadeService service;
	
	@GetMapping
	public ResponseEntity<List<UnidadeDTO>> listar(){
		List<Unidade> lista = service.listar();
		List<UnidadeDTO> listaDto = lista.stream().map(unidade -> new UnidadeDTO(unidade)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Unidade> consultarPorId(@PathVariable Integer id) {
		Unidade unidade = service.consultaPorId(id);
		return unidade != null ? ResponseEntity.ok(unidade) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Unidade> inserir(@Valid @RequestBody UnidadeDTO unidadeDto) {
		Unidade unidade = service.fromDTO(unidadeDto); 
		unidade = service.inserir(unidade);
		
		//Mapear o recurso -> unidade + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(unidade.getId()).toUri();
		
		return ResponseEntity.created(uri).body(unidade);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Unidade> atualizar(@Valid @RequestBody UnidadeDTO unidadeDto, @PathVariable Integer id) {
		Unidade unidade = service.fromDTO(unidadeDto);
		unidade = service.atualizar(unidade, id);
		return ResponseEntity.ok(unidade);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

}
