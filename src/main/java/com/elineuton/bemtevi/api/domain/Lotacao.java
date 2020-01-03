package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lotacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	@Getter
	private LotacaoPK id = new LotacaoPK();
	
	@Getter @Setter
	private LocalDate data;

	public Lotacao(Turma turma, Profissional profissional, LocalDate data) {
		super();
		id.setTurma(turma);
		id.setProfissional(profissional);
		this.data = data;
	}
	
	@JsonIgnore
	public Turma getTurma() {
		return id.getTurma();
	}
	
	@JsonIgnore
	public Profissional getProfissional() {
		return id.getProfissional();
	}
	
	
	
}
