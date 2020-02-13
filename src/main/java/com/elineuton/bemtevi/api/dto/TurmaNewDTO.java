package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TurmaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	
}
