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

import com.elineuton.bemtevi.api.domain.Avaliacao;
import com.elineuton.bemtevi.api.dto.AvaliacaoDTO;
import com.elineuton.bemtevi.api.services.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService service;
	
	@GetMapping
	public ResponseEntity<List<AvaliacaoDTO>> listar(){
		List<Avaliacao> lista = service.listar();
		List<AvaliacaoDTO> listaDto = lista.stream().map(avaliacao -> new AvaliacaoDTO(avaliacao)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Avaliacao> consultarPorId(@PathVariable Integer id) {
		Avaliacao avaliacao = service.consultaPorId(id);
		return avaliacao != null ? ResponseEntity.ok(avaliacao) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Avaliacao> inserir(@Valid @RequestBody AvaliacaoDTO avaliacaoDto) {
		Avaliacao avaliacao = service.fromDTO(avaliacaoDto);
		avaliacao = service.salvar(avaliacao);
		
		//Mapear o recurso -> avaliacao + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(avaliacao.getId()).toUri();
		
		return ResponseEntity.created(uri).body(avaliacao);
	}
	
	@PutMapping
	public ResponseEntity<AvaliacaoDTO> atualizar(@Valid @RequestBody AvaliacaoDTO avaliacaoDto) {
		Avaliacao avaliacao = service.fromDTO(avaliacaoDto);
		avaliacao = service.salvar(avaliacao);
		
		return ResponseEntity.ok(service.convertToDTO(avaliacao));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

}
