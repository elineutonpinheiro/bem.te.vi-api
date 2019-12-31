package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lotacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Getter
	private LotacaoPK id = new LotacaoPK();
	
	@Getter @Setter
	private LocalDate dataLotacao;

	public Lotacao(Turma turma, Profissional profissional, LocalDate dataLotacao) {
		super();
		id.setTurma(turma);
		id.setProfissional(profissional);
		this.dataLotacao = dataLotacao;
	}
	
	public Turma getTurma() {
		return id.getTurma();
	}
	
	public Profissional getProfissional() {
		return id.getProfissional();
	}
	
	
	
}
