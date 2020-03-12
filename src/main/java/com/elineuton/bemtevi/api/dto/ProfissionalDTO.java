package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.bemtevi.api.domain.Profissional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ProfissionalDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String cargo;
	
	@Getter @Setter
	private String telefone;
	
	@Getter	@Setter
	private Boolean ativo;
	
	public ProfissionalDTO(Profissional profissional) {
		this.id = profissional.getId();
		this.nome = profissional.getNome();
		this.cargo = profissional.getCargo();
		this.ativo = profissional.getAtivo();
	}
	
}
