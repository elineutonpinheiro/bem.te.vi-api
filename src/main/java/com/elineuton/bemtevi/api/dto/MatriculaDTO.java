package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Matricula;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class MatriculaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Aluno aluno;
	
	@Getter @Setter
	private Integer turmaId;
	
	@Getter @Setter
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTermino;

	public MatriculaDTO(Matricula matricula) {
		this.aluno = matricula.getAluno();
		this.turmaId = matricula.getTurma().getId();
		this.dataTermino = matricula.getDataTermino();
	}

}
