package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.bemtevi.api.domain.Aluno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AlunoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	public AlunoDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
	}
	
}
