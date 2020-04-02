package com.elineuton.bemtevi.api.resources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Avaliacao;
import com.elineuton.bemtevi.api.domain.Matricula;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.dto.AlunoDTO;
import com.elineuton.bemtevi.api.dto.AlunoNewDTO;
import com.elineuton.bemtevi.api.dto.AvaliacaoDTO;
import com.elineuton.bemtevi.api.dto.MatriculaDTO;
import com.elineuton.bemtevi.api.dto.TurmaDTO;
import com.elineuton.bemtevi.api.services.AlunoService;
import com.elineuton.bemtevi.api.services.AvaliacaoService;
import com.elineuton.bemtevi.api.services.MatriculaService;
import com.elineuton.bemtevi.api.services.TurmaService;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService service;
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private AlunoService alunoService;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> listar(){
		List<Aluno> lista = service.listar();
		List<AlunoDTO> listaDto = lista.stream().map(aluno -> new AlunoDTO(aluno))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	//@PreAuthorize("hasAnyRole('RESPONSAVEL', 'PROFISSIONAL')")
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> consultarPorId(@PathVariable Integer id) {
		Aluno aluno = service.consultarPorId(id);
		return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
	}
	
	/* =====  O ALUNO É INSERIDO NO ATO DA MATRÍCULA =====
	 * 
	 * @PostMapping public ResponseEntity<Aluno> inserir(@Valid @RequestBody
	 * AlunoDTO alunoDto) { Aluno aluno = service.fromDTO(alunoDto);
	 * 
	 * aluno = service.inserir(aluno);
	 * 
	 * //Mapear o recurso -> aluno + id
	 * 
	 * URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
	 * .buildAndExpand(aluno.getId()).toUri();
	 * 
	 * return ResponseEntity.created(uri).body(aluno); }
	 */
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizar(@Valid @RequestBody Aluno aluno, @PathVariable Integer id) {
		aluno = service.atualizar(aluno, id);
		return ResponseEntity.ok(aluno);
	}
	
	//@PreAuthorize("hasAnyRole('PROFISSIONAL')")
	@PutMapping("/{id}/presenca")
	public void atualizarPresenca(@PathVariable Integer id, @RequestBody LocalDate dataPresenca) {
		service.atualizarPresenca(id, dataPresenca);
	}
	
	//@PreAuthorize("hasAnyRole('PROFISSIONAL', 'ADMIN')")
	@PostMapping("/{id}/pessoalAutorizado")
	public ResponseEntity<Aluno> inserir(@Valid @RequestBody AlunoNewDTO alunoNewDTO) {
		Aluno aluno = service.fromDTO(alunoNewDTO);
		aluno = service.inserir(aluno);
		return ResponseEntity.ok().body(aluno);
	}
	
	/*
	@PutMapping("/{id}/pessoalAutorizado")
	public void atualizarPessoalAutorizado(@PathVariable Integer id, @RequestBody Set<String> pessoalAutorizado) {
		service.atualizarPessoalAutorizado(id, pessoalAutorizado);
	}
	*/
	
	//@PreAuthorize("hasAnyRole('RESPONSAVEL', 'ADMIN')")
	@DeleteMapping("/{id}/pessoalAutorizado")
	public void removerPessoalAutorizado(@PathVariable Integer id, @RequestBody String pessoaAutorizada) {
		service.removerPessoalAutorizado(id, pessoaAutorizada);
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('RESPONSAVEL')")
	
	@GetMapping("/{id}/avaliacoes")
	public ResponseEntity<AvaliacaoDTO> consultarAvaliacaoPorAlunoIdEData(@PathVariable Integer id, @RequestParam(value="data") String data) {
		LocalDate date = LocalDate.parse(data);
		//String formato = "dd/MM/yyyy";
		//DateTimeFormatter format = DateTimeFormatter.ofPattern(formato);
		//LocalDate date = LocalDate.parse(data, format);
		//System.out.println("DATAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + date);
		Avaliacao avaliacao = avaliacaoService.buscarAvaliacaoAndamento(id, date);
		

		AvaliacaoDTO avaliacaoDTO = avaliacaoService.convertToDTO(avaliacao);
		//List<AvaliacaoDTO> listaDto = lista.stream().map(aluno -> new AvaliacaoDTO(aluno)).collect(Collectors.toList());
		return ResponseEntity.ok(avaliacaoDTO);
	}
	
	/*
	@GetMapping("/{id}/avaliacoes")
	public ResponseEntity<List<AvaliacaoDTO>> consultarAvaliacaoPorAlunoId(@PathVariable Integer id) {
		List<Avaliacao> lista = avaliacaoService.consultarAvaliacaoPorAlunoId(id);
		List<AvaliacaoDTO> listaDto = lista.stream().map(aluno -> new AvaliacaoDTO(aluno)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	*/
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}/matriculas")
	public ResponseEntity<List<MatriculaDTO>> consultaMatriculasPorAlunolId(@PathVariable Integer id) {
		List<Matricula> lista = matriculaService.consultarMatriculaPorAlunoId(id);
		List<MatriculaDTO> listaDto = lista.stream().map(aluno -> new MatriculaDTO(aluno)).collect(Collectors.toList());
		return ResponseEntity.ok(listaDto);
	}
	
	@GetMapping("/{id}/turma")
	public ResponseEntity<TurmaDTO> consultaTurmaPorAlunolId(@PathVariable Integer id) {
		Turma turma = turmaService.consultaTurmaPorAlunoId(id);
		return ResponseEntity.ok(service.convertToDTO(turma));
	}
	
}
