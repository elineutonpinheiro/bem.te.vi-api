package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.elineuton.bemtevi.api.domain.Avaliacao;
import com.elineuton.bemtevi.api.domain.enums.StatusAvaliacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AvaliacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private Integer alunoId;
	
	@Getter @Setter
	private Integer profissionalId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Getter @Setter
	private LocalDate data;
	
	@Getter @Setter
	private StatusAvaliacao status;
	
	@Getter @Setter
	private String cafeDaManha;

	@Getter @Setter
	private String lancheDaManha;

	@Getter	@Setter
	private String almoco;

	@Getter @Setter
	private String lancheDaTarde;

	@Getter	@Setter
	private Integer banho;

	@Getter	@Setter
	private Integer fralda;

	@Getter	@Setter
	private Integer escovacao;

	@Getter	@Setter
	private boolean dormiu;

	@Getter	@Setter
	private String estadoDoSono;

	@Getter	@Setter
	private boolean febre;

	@Getter	@Setter
	private String urina;

	@Getter	@Setter
	private String evacuacao;

	@Getter	@Setter
	private String interacao;

	@Getter	@Setter
	private String participacao;

	@Getter	@Setter
	private String observacao; 
	
	public AvaliacaoDTO(Avaliacao avaliacao) {
		this.id = avaliacao.getId();
		this.alunoId = avaliacao.getAluno().getId();
		this.profissionalId = avaliacao.getProfissional().getId();
		this.data = avaliacao.getData();
		this.status = avaliacao.getStatus();
		this.cafeDaManha = avaliacao.getQuestionario().getCafeDaManha();
		this.lancheDaManha = avaliacao.getQuestionario().getLancheDaManha();
		this.almoco = avaliacao.getQuestionario().getAlmoco();
		this.lancheDaTarde = avaliacao.getQuestionario().getLancheDaTarde();
		this.banho = avaliacao.getQuestionario().getBanho();
		this.fralda = avaliacao.getQuestionario().getFralda();
		this.escovacao = avaliacao.getQuestionario().getEscovacao();
		this.dormiu = avaliacao.getQuestionario().isDormiu();
		this.estadoDoSono = avaliacao.getQuestionario().getEstadoDoSono();
		this.febre = avaliacao.getQuestionario().isFebre();
		this.urina = avaliacao.getQuestionario().getUrina();
		this.evacuacao = avaliacao.getQuestionario().getEvacuacao();
		this.interacao = avaliacao.getQuestionario().getInteracao();
		this.participacao = avaliacao.getQuestionario().getParticipacao();
		this.observacao = avaliacao.getQuestionario().getObservacao();
	}
	
}
