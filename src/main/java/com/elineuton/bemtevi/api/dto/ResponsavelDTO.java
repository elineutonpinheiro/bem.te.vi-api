package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.bemtevi.api.domain.Responsavel;
import com.elineuton.bemtevi.api.domain.enums.TipoParentesco;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ResponsavelDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter	@Setter
	private Integer id;
	
	@Getter	@Setter
	private String nome;
	
	@Getter	@Setter
	private TipoParentesco parentesco;
	
	public ResponsavelDTO(Responsavel responsavel) {
		this.id = responsavel.getId();
		this.nome = responsavel.getNome();
		this.parentesco = responsavel.getParentesco();
	}
	
}
