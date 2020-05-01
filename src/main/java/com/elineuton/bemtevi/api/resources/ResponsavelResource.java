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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Matricula;
import com.elineuton.bemtevi.api.domain.Responsavel;
import com.elineuton.bemtevi.api.dto.AlunoDTO;
import com.elineuton.bemtevi.api.dto.MatriculaNewDTO;
import com.elineuton.bemtevi.api.dto.ResponsavelDTO;
import com.elineuton.bemtevi.api.dto.ResponsavelNewDTO;
import com.elineuton.bemtevi.api.services.AlunoService;
import com.elineuton.bemtevi.api.services.MatriculaService;
import com.elineuton.bemtevi.api.services.ResponsavelService;


@RestController
@RequestMapping("/responsaveis")
public class ResponsavelResource {

	@Autowired
	private ResponsavelService service;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private MatriculaService matriculaService;

	@GetMapping
	public ResponseEntity<List<ResponsavelDTO>> listar() {
		List<Responsavel> lista = service.listar();
		List<ResponsavelDTO> listaDto = lista.stream().map(profissional -> new ResponsavelDTO(profissional))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Responsavel> consultarPorId(@PathVariable Integer id) {
		Responsavel profissional = service.consultarPorId(id);
		return profissional != null ? ResponseEntity.ok(profissional) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/email")
	public ResponseEntity<ResponsavelDTO> consultarPorEmail(@RequestParam(value="value") String email) {
		Responsavel profissional = service.consultarPorEmail(email);
		return ResponseEntity.ok().body(new ResponsavelDTO(profissional));
	}

	@PostMapping
	public ResponseEntity<Responsavel> inserir(@Valid @RequestBody ResponsavelNewDTO profissionalNewDto) {
		Responsavel profissional = service.fromDTO(profissionalNewDto);
		profissional = service.inserir(profissional);

		// Mapear o recurso -> responsavel + id

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(profissional.getId()).toUri();

		return ResponseEntity.created(uri).body(profissional);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Responsavel> atualizar(@Valid @RequestBody ResponsavelDTO profissionalDto,
			@PathVariable Integer id) {
		Responsavel profissional = service.fromDTO(profissionalDto);
		profissional = service.atualizar(profissional, id);
		return ResponseEntity.ok(profissional);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{email}/alunos")
	public ResponseEntity<List<AlunoDTO>> consultarAlunosPorEmailResponsavel(@PathVariable String email) {
		List<Aluno> lista = alunoService.consultarAlunosPorEmailResponsavel(email);
		List<AlunoDTO> listaDto = lista.stream().map(aluno -> new AlunoDTO(aluno)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{email}/matriculas")
	public ResponseEntity<List<MatriculaNewDTO>> consultarMatriculasPorEmailResponsavel(@PathVariable String email) {
		List<Matricula> lista = matriculaService.consultarMatriculasPorEmailResponsavel(email);
		List<MatriculaNewDTO> listaDto = lista.stream().map(matricula -> new MatriculaNewDTO(matricula)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
}
