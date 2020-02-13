package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.elineuton.bemtevi.api.domain.Avaliacao;
import com.elineuton.bemtevi.api.domain.Resposta;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AvaliacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private Integer alunoId;
	
	@Getter @Setter
	private Integer profissionalId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Getter @Setter
	private LocalDate data;
	
	@Getter @Setter
	private String status;
	
	@Getter @Setter
	private List<Resposta> respostas = new ArrayList<Resposta>();
	
	public AvaliacaoDTO(Avaliacao avaliacao) {
		this.id = avaliacao.getId();
		this.alunoId = avaliacao.getAluno().getId();
		this.profissionalId = avaliacao.getProfissional().getId();
		this.data = avaliacao.getData();
		this.status = avaliacao.getStatus();
		this.respostas = avaliacao.getRespostas();
	}
	
}
