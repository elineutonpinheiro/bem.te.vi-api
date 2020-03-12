package com.elineuton.bemtevi.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.elineuton.bemtevi.api.domain.Lotacao;
import com.elineuton.bemtevi.api.dto.LotacaoDTO;
import com.elineuton.bemtevi.api.services.LotacaoService;

@RestController
@RequestMapping("/lotacoes")
public class LotacaoResource {
	
	@Autowired
	private LotacaoService service;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<LotacaoDTO>> listar(){
		List<Lotacao> lista = service.listar();
		List<LotacaoDTO> listaDto = lista.stream().map(obj -> new LotacaoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Lotacao> inserir(@Valid @RequestBody LotacaoDTO lotacaoDto) {
		Lotacao lotacao = service.fromDTO(lotacaoDto);
		lotacao = service.inserir(lotacao);
		
		//Mapear o recurso -> instituicao + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(lotacao.getId()).toUri();
		
		return ResponseEntity.created(uri).body(lotacao);
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Lotacao> atualizar(@Valid @RequestBody LotacaoDTO lotacaoDTO, @PathVariable Integer id) {
		Lotacao lotacao = service.fromDTO(lotacaoDTO);
		lotacao = service.atualizar(lotacao, id);
		return ResponseEntity.ok(lotacao);
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
}
