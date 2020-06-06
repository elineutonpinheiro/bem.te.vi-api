package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.elineuton.bemtevi.api.domain.Aluno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AlunoNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * @Getter @Setter private Integer alunoId;
	 * 
	 * @Getter @Setter private Set<String> pessoalAutorizado = new HashSet<>();
	 * 
	 * public AlunoNewDTO(Aluno aluno) { this.alunoId = aluno.getId(); }
	 */
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private LocalDate dataNascimento;
	
	@Getter @Setter
	private String nomeResponsavel;
	
	@Getter @Setter
	private String parentescoResponsavel;
	
	@Getter @Setter
	private boolean ativo;

	public AlunoNewDTO(Aluno aluno) {
		this.nome = aluno.getNome();
		this.dataNascimento = aluno.getDataNascimento();
		this.nomeResponsavel = aluno.getResponsavel().getNome();
		this.parentescoResponsavel = aluno.getResponsavel().getParentesco().getDescricao();
		this.ativo = aluno.isAtivo();
	}
	

}
