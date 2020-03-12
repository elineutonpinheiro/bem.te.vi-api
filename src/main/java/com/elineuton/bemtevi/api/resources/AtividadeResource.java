package com.elineuton.bemtevi.api.resources;

import java.net.URI;

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
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> consultarPorId(@PathVariable Integer id) {
		Atividade atividade = service.consultarPorId(id);
		return atividade != null ? ResponseEntity.ok(atividade) : ResponseEntity.notFound().build();
	}
	
	//@PreAuthorize("hasAnyRole('PROFISSIONAL')")
	@PostMapping
	public ResponseEntity<Atividade> inserir(@Valid @RequestBody AtividadeDTO atividadeDto) {
		Atividade atividade = service.fromDTO(atividadeDto);
		atividade = service.inserir(atividade);
		
		//Mapear o recurso -> atividade + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(atividade.getId()).toUri();
		
		return ResponseEntity.created(uri).body(atividade);
	}
	
	//@PreAuthorize("hasAnyRole('PROFISSIONAL')")
	@PutMapping("/{id}")
	public ResponseEntity<Atividade> atualizar(@Valid @RequestBody AtividadeDTO atividadeDto, @PathVariable Integer id) {
		Atividade atividade = service.fromDTO(atividadeDto);
		atividade = service.atualizar(atividade, id);
		return ResponseEntity.ok(atividade);
	}
	
	//@PreAuthorize("hasAnyRole('PROFISSIONAL')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

}

/*
@GetMapping
public ResponseEntity<List<AtividadeDTO>> listar(){
	List<Atividade> lista = service.listar();
	List<AtividadeDTO> listaDto = lista.stream().map(atividade -> new AtividadeDTO(atividade)).collect(Collectors.toList());
	return ResponseEntity.ok(listaDto);
}
*/
/*
@GetMapping
public ResponseEntity<List<AtividadeDTO>> pesquisar(AtividadeFilter atividadeFilter){
	List<Atividade> lista = service.pesquisar(atividadeFilter);
	List<AtividadeDTO> listaDto = lista.stream().map(atividade -> new AtividadeDTO(atividade)).collect(Collectors.toList());
	return ResponseEntity.ok(listaDto);
}
*/


/* Implementado em TurmaResource
@GetMapping
public ResponseEntity<List<AtividadeDTO>> listar(
		@RequestParam(value="turma", defaultValue = "") Integer turma,
		@RequestParam(value="descricao", defaultValue = "") String descricao){
	List<Atividade> lista = service.consultarPorTurmaEDescricao(turma, descricao);
	List<AtividadeDTO> listaDto = lista.stream().map(atividade -> new AtividadeDTO(atividade)).collect(Collectors.toList());
	return ResponseEntity.ok(listaDto);
}
*/
