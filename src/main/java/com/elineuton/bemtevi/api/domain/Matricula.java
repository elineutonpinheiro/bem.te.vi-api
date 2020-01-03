package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Matricula implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	@Getter
	private MatriculaPK id = new MatriculaPK();
	
	@Getter @Setter
	private LocalDate data;

	public Matricula(Turma turma, Aluno aluno, LocalDate data) {
		super();
		id.setTurma(turma);
		id.setAluno(aluno);
		this.data = data;
	}
	
	@JsonIgnore
	public Turma getTurma() {
		return id.getTurma();
	}
	
	@JsonIgnore
	public Aluno getAluno() {
		return id.getAluno();
	}
	

}
