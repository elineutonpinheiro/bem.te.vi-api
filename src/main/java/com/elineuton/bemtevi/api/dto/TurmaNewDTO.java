package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TurmaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String periodo;
	
	@Getter @Setter
	private String sala;
	
	@Getter @Setter
	private boolean ativa;
	
	@Getter @Setter
	private Integer unidadeId;
	
	@Getter @Setter
	private Integer anoLetivoId;

	public TurmaNewDTO(String nome, String periodo, String sala, boolean ativa, Integer unidadeId,
			Integer anoLetivoId) {
		this.nome = nome;
		this.periodo = periodo;
		this.sala = sala;
		this.ativa = ativa;
		this.unidadeId = unidadeId;
		this.anoLetivoId = anoLetivoId;
	}
	
	
	
}
