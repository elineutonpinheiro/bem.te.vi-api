package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lotacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_profissional_id"))
	private Profissional profissional;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_turma_id"))
	private Turma turma;
	
	@Getter @Setter
	private LocalDate dataInicio;
	
	@Getter @Setter
	private LocalDate dataTermino;

	public Lotacao(Profissional profissional, Turma turma, LocalDate dataInicio, LocalDate dataTermino) {
		this.profissional = profissional;
		this.turma = turma;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
	}
	
}
