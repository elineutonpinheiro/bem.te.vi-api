package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.bemtevi.api.domain.Turma;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TurmaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String sala;
	
	@Getter @Setter
	private Long qtdeMatriculas;
	
	public TurmaDTO(Turma turma) {
		this.id = turma.getId();
		this.nome = turma.getNome();
		this.sala = turma.getSala();
		this.qtdeMatriculas = turma.getQtdeMatriculas();
	}
	
}
