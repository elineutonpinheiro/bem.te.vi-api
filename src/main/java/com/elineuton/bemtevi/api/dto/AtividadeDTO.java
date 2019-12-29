package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Id;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AtividadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHora;

	public AtividadeDTO(Atividade obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.dataHora = obj.getDataHora();
	}
	
}
