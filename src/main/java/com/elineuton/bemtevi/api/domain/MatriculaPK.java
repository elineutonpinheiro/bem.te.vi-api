package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode(of = {"turma", "aluno"})
public class MatriculaPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "turma_id"))
	@Getter @Setter
	private Turma turma;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "aluno_id"))
	@Getter @Setter
	private Aluno aluno;

}
