package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.time.LocalDate;

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
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacao;
	
	@Getter @Setter
	private Integer turmaId;

	public AtividadeDTO(Atividade atividade) {
		this.id = atividade.getId();
		this.descricao = atividade.getDescricao();
		this.dataCriacao = atividade.getDataCriacao();
		this.turmaId = atividade.getTurma().getId();
	}
	
}
