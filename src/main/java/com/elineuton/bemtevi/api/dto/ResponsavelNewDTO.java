package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.bemtevi.api.domain.Responsavel;
import com.elineuton.bemtevi.api.domain.enums.TipoParentesco;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ResponsavelNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private TipoParentesco parentesco;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String senha;
	
	@Getter	@Setter
	private Boolean ativo;
	
	public ResponsavelNewDTO(Responsavel responsavel) {
		this.id = responsavel.getId();
		this.nome = responsavel.getNome();
		this.parentesco = responsavel.getParentesco();
		this.email = responsavel.getEmail();
		this.senha = responsavel.getSenha();
		this.ativo = responsavel.getAtivo();
	}
	
}
