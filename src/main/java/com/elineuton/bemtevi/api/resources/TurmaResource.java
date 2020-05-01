package com.elineuton.bemtevi.api.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.AlunoDTO;
import com.elineuton.bemtevi.api.dto.AtividadeDTO;
import com.elineuton.bemtevi.api.dto.TurmaDTO;
import com.elineuton.bemtevi.api.dto.TurmaNewDTO;
import com.elineuton.bemtevi.api.repositories.filter.AtividadeFilter;
import com.elineuton.bemtevi.api.services.AlunoService;
import com.elineuton.bemtevi.api.services.AtividadeService;
import com.elineuton.bemtevi.api.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

	@Autowired
	private TurmaService service;

	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<List<TurmaDTO>> listar() {
		List<Turma> lista = service.listar();
		List<TurmaDTO> listaDto = lista.stream().map(turma -> new TurmaDTO(turma)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> consultarPorId(@PathVariable Integer id) {
		Turma turma = service.consultarPorId(id);
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Turma> inserir(@Valid @RequestBody TurmaNewDTO turmaDto) {
		Turma turma = service.fromDTO(turmaDto);
		turma = service.inserir(turma);

		// Mapear o recurso -> turma + id

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(turma.getId()).toUri();

		return ResponseEntity.created(uri).body(turma);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Turma> atualizar(@Valid @RequestBody TurmaNewDTO turmaDto, @PathVariable Integer id) {
		Turma turma = service.fromDTO(turmaDto);
		turma = service.atualizar(turma, id);
		return ResponseEntity.ok(turma);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/atividades")
	public ResponseEntity<List<AtividadeDTO>> pesquisar(
		@PathVariable Integer id,
		@RequestParam(value="descricao", defaultValue = "") String descricao, 
		@RequestParam(value="dataCriacaoDe", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataCriacaoDe,
		@RequestParam(value="dataCriacaoAte", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataCriacaoAte) {
		AtividadeFilter atividadeFilter = new AtividadeFilter(descricao, dataCriacaoDe, dataCriacaoAte);
		Turma t = service.consultarPorId(id);
		List<Atividade> lista = atividadeService.pesquisar(t, atividadeFilter);
		List<AtividadeDTO> listaDto = lista.stream().map(turma -> new AtividadeDTO(turma)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{id}/alunos")
	public ResponseEntity<List<AlunoDTO>> consultarAlunosPorTurmaId(@PathVariable Integer id) {
		List<Aluno> lista = alunoService.consultarAlunosPorTurmaId(id);
		List<AlunoDTO> listaDto = lista.stream().map(turma -> new AlunoDTO(turma)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	
	/*@GetMapping("/{id}/avaliacoes")
	public ResponseEntity<List<AvaliacaoDTO>> consultarAvaliacaoPorTurmaId(
			@PathVariable Integer id) {
		List<Avaliacao> lista = avaliacaoService.consultarAvaliacaoPorTurmaId(id);
		List<AvaliacaoDTO> listaDto = lista.stream().map(turma -> new AvaliacaoDTO(turma)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	*/
}
