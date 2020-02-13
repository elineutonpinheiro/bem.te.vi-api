package com.elineuton.bemtevi.api.repositories.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

public class AtividadeFilter {
	
	@Getter @Setter
	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Getter @Setter
	private LocalDate dataCriacaoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Getter @Setter
	private LocalDate dataCriacaoAte;

	public AtividadeFilter(String descricao, LocalDate dataCriacaoDe, LocalDate dataCriacaoAte) {
		this.descricao = descricao;
		this.dataCriacaoDe = dataCriacaoDe;
		this.dataCriacaoAte = dataCriacaoAte;
	}
	
	

}
