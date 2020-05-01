package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.elineuton.bemtevi.api.domain.Aluno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AlunoNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Integer alunoId;
	
	@Getter @Setter
	private Set<String> pessoalAutorizado = new HashSet<>();
	
	public AlunoNewDTO(Aluno aluno) {
		this.alunoId = aluno.getId();
	}

}
