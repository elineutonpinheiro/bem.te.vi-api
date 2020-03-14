package com.elineuton.bemtevi.api.domain.enums;

import lombok.Getter;
import lombok.Setter;

public enum StatusAvaliacao {
	
	A_FAZER("A FAZER"),
	EM_ANDAMENTO("EM ANDAMENTO"),
	CONCLUIDA("CONCLUÍDA");
	
	@Getter @Setter
	private String descricao;
	
	StatusAvaliacao(String descricao) {
		this.descricao = descricao;
	}

}
