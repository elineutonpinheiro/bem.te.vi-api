package com.elineuton.bemtevi.api.domain.enums;

import lombok.Getter;
import lombok.Setter;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	PROFISSIONAL(2, "ROLE_PROFISSIONAL"),
	RESPONSAVEL(3, "ROLE_RESPONSAVEL");
	
	@Getter @Setter
	private int cod;
	
	@Getter @Setter
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Perfil p : Perfil.values()) {
			if(cod.equals(p.getCod())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + cod);
	}

}
