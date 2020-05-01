package com.elineuton.bemtevi.api.domain.enums;

import lombok.Getter;
import lombok.Setter;

public enum TipoParentesco {
	
	PAI("PAI"),
	MAE("MÃE"),
	TIO("TIO(A)"),
	AVO("AVÔ/AVÓ");
	
	@Getter @Setter
	private String descricao;
	
	TipoParentesco(String descricao) {
		this.descricao = descricao;
	}

}
