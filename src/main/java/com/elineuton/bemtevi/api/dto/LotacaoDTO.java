package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.elineuton.bemtevi.api.domain.Lotacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class LotacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Integer turmaId;
	
	@Getter @Setter
	private Integer profissionalId;
	
	@JsonIgnore
	@Getter @Setter
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;

	@JsonIgnore
	@Getter @Setter
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTermino;
	
	public LotacaoDTO(Lotacao lotacao) {
		this.turmaId = lotacao.getTurma().getId();
		this.profissionalId = lotacao.getProfissional().getId();
	}
	
	
	
}
