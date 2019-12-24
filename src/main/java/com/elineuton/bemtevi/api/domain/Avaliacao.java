package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Avaliacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@OneToOne
	@Getter @Setter
	private Aluno aluno;
	
	@OneToOne
	@Getter @Setter
	private Profissional avaliador;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHora;
	
	@Getter @Setter
	private String status;
	
	@OneToMany
	@Getter @Setter
	private List<Resposta> respostas = new ArrayList<Resposta>();

	public Avaliacao(Integer id, Aluno aluno, Profissional avaliador, LocalDateTime dataHora, String status) {
		super();
		this.id = id;
		this.aluno = aluno;
		this.avaliador = avaliador;
		this.dataHora = dataHora;
		this.status = status;
	}

}
