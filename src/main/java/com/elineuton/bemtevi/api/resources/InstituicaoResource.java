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

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Responsavel;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.dto.AlunoNewDTO;
import com.elineuton.bemtevi.api.dto.ResponsavelDTO;
import com.elineuton.bemtevi.api.dto.UnidadeDTO;
import com.elineuton.bemtevi.api.services.AlunoService;
import com.elineuton.bemtevi.api.services.InstituicaoService;
import com.elineuton.bemtevi.api.services.ProfissionalService;
import com.elineuton.bemtevi.api.services.ResponsavelService;
import com.elineuton.bemtevi.api.services.UnidadeService;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoResource {
	
	@Autowired
	private InstituicaoService service;
	
	@Autowired
	private UnidadeService unidadeService;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private ResponsavelService responsavelService;
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<List<Instituicao>> listar(){
		List<Instituicao> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Instituicao> consultarPorId(@PathVariable Integer id) {
		Instituicao instituicao = service.consultaPorId(id);
		return instituicao != null ? ResponseEntity.ok(instituicao) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Instituicao> inserir(@Valid @RequestBody Instituicao instituicao) {
		Instituicao instituicaoSalvo = service.inserir(instituicao);
		
		//Mapear o recurso -> instituicao + id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(instituicaoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(instituicaoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Instituicao> atualizar(@Valid @RequestBody Instituicao instituicao, @PathVariable Integer id) {
		Instituicao instituicaoSalvo = service.atualizar(instituicao, id);
		return ResponseEntity.ok(instituicaoSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{instituicaoId}/unidades")
	public ResponseEntity<List<UnidadeDTO>> consultarUnidadesPorInstituicaoId(@PathVariable Integer instituicaoId) {
		List<Unidade> lista = unidadeService.consultarUnidadesPorInstituicaoId(instituicaoId);
		List<UnidadeDTO> listaDto = lista.stream().map(unidade -> new UnidadeDTO(unidade)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{instituicaoId}/profissionais")
	public ResponseEntity<List<Profissional>> consultarProfissionaisPorInstituicaoId(@PathVariable Integer instituicaoId) {
		List<Profissional> lista = profissionalService.consultarProfissionaisPorInstituicaoId(instituicaoId);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{instituicaoId}/responsaveis")
	public ResponseEntity<List<ResponsavelDTO>> consultarResponsaveisPorInstituicaoId(@PathVariable Integer instituicaoId) {
		List<Responsavel> lista = responsavelService.consultarResponsaveisPorInstituicaoId(instituicaoId);
		List<ResponsavelDTO> listaDto = lista.stream().map(responsavel -> new ResponsavelDTO(responsavel)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{instituicaoId}/alunos")
	public ResponseEntity<List<AlunoNewDTO>> consultarAlunosPorInstituicaoId(@PathVariable Integer instituicaoId) {
		List<Aluno> lista = alunoService.consultarAlunosPorInstituicaoId(instituicaoId);
		List<AlunoNewDTO> listaDto = lista.stream().map(aluno -> new AlunoNewDTO(aluno)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	 
}
