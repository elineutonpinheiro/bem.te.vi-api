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
	
	@Getter @Setter
	private String sobrenome;
	
	public AlunoDTO(Aluno obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.sobrenome = obj.getSobrenome();
	}
	
}
