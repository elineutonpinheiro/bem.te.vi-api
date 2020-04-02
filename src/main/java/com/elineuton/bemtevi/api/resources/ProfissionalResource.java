package com.elineuton.bemtevi.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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

import com.elineuton.bemtevi.api.domain.Avaliacao;
import com.elineuton.bemtevi.api.domain.Lotacao;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.AvaliacaoDTO;
import com.elineuton.bemtevi.api.dto.LotacaoDTO;
import com.elineuton.bemtevi.api.dto.ProfissionalDTO;
import com.elineuton.bemtevi.api.dto.ProfissionalNewDTO;
import com.elineuton.bemtevi.api.dto.TurmaDTO;
import com.elineuton.bemtevi.api.services.AvaliacaoService;
import com.elineuton.bemtevi.api.services.LotacaoService;
import com.elineuton.bemtevi.api.services.ProfissionalService;
import com.elineuton.bemtevi.api.services.TurmaService;


@RestController
@RequestMapping("/profissionais")
public class ProfissionalResource {

	@Autowired
	private ProfissionalService service;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private LotacaoService lotacaoService;

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ProfissionalDTO>> listar() {
		List<Profissional> lista = service.listar();
		List<ProfissionalDTO> listaDto = lista.stream().map(profissional -> new ProfissionalDTO(profissional))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

	// ------- Usuário só recupera ele mesmo --------//
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Profissional> consultarPorId(@PathVariable Integer id) {
		Profissional profissional = service.consultarPorId(id);
		return profissional != null ? ResponseEntity.ok(profissional) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/email")
	public ResponseEntity<ProfissionalDTO> consultarPorEmail(@RequestParam(value="value") String email) {
		Profissional profissional = service.consultarPorEmail(email);
		return ResponseEntity.ok().body(new ProfissionalDTO(profissional));
	}

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Profissional> inserir(@Valid @RequestBody ProfissionalNewDTO profissionalNewDto) {
		Profissional profissional = service.fromDTO(profissionalNewDto);
		profissional = service.inserir(profissional);

		// Mapear o recurso -> instituicao + id

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(profissional.getId()).toUri();

		return ResponseEntity.created(uri).body(profissional);
	}

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Profissional> atualizar(@Valid @RequestBody ProfissionalDTO profissionalDto,
			@PathVariable Integer id) {
		Profissional profissional = service.fromDTO(profissionalDto);
		profissional = service.atualizar(profissional, id);
		return ResponseEntity.ok(profissional);
	}

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}

	//@PreAuthorize("hasAnyRole('PROFISSIONAL', 'ADMIN')")
	@GetMapping("/{email}/turmas")
	public ResponseEntity<List<TurmaDTO>> consultarTurmasPorEmailProfissional(@PathVariable String email) {
		List<Turma> lista = turmaService.consultarTurmasPorEmailProfissional(email);
		List<TurmaDTO> listaDto = lista.stream().map(profissional -> new TurmaDTO(profissional)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	//@PreAuthorize("hasAnyRole('PROFISSIONAL', 'ADMIN')")
	/*@GetMapping("/{id}/avaliacoes")
	public ResponseEntity<List<AvaliacaoDTO>> consultaAvaliacoesPorProfissionalId(@PathVariable Integer id) {
		List<Avaliacao> lista = avaliacaoService.consultaAvaliacaoPorProfissionald(id);
		List<AvaliacaoDTO> listaDto = lista.stream().map(profissional -> new AvaliacaoDTO(profissional)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	*/
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}/lotacoes")
	public ResponseEntity<List<LotacaoDTO>> consultarLotacaosPorProfissionalId(@PathVariable Integer id) {
		List<Lotacao> lista = lotacaoService.consultarLotacaoPorProfissionalId(id);
		List<LotacaoDTO> listaDto = lista.stream().map(profissional -> new LotacaoDTO(profissional)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

}
