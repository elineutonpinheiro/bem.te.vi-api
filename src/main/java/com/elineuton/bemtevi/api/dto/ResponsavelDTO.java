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
	private String parentesco;
	
	@Getter	@Setter
	private Boolean ativo;
	
	@Getter @Setter
	private Long qtdeMatriculas;
	
	public ResponsavelDTO(Responsavel responsavel) {
		this.id = responsavel.getId();
		this.nome = responsavel.getNome();
		this.parentesco = this.gerarStringParentesco(responsavel.getParentesco());
		this.ativo = responsavel.getAtivo();
		this.qtdeMatriculas = responsavel.getQtdeMatriculas();
	}
	
	private String gerarStringParentesco(TipoParentesco tipoParentesco) {
		switch (tipoParentesco) {
		case PAI:
			return "Pai";
		case MAE:
			return "Mãe";
		case TIO:
			return "Tio(a)";
		default:
			return "Avô/Avó";
		}
	}

	
}
