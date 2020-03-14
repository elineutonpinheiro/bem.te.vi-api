package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.elineuton.bemtevi.api.domain.enums.StatusAvaliacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(of = "id")
public class Avaliacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_aluno_id"))
	@Getter @Setter
	private Aluno aluno;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_profissional_id"))
	@Getter @Setter
	private Profissional profissional;
	
	@Getter @Setter
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@Getter @Setter
	@Enumerated(EnumType.STRING)
	private StatusAvaliacao status;
	
	@Getter @Setter
	@Embedded
	private Questionario questionario;
	
	public Avaliacao() {
		this.data = LocalDate.now();
		this.status = StatusAvaliacao.EM_ANDAMENTO;
	}

	public Avaliacao(Integer id, Aluno aluno, Profissional profissional, LocalDate data, StatusAvaliacao status, Questionario questionario) {
		this.id = id;
		this.aluno = aluno;
		this.profissional = profissional;
		this.data = data;
		this.status = status;
		this.questionario = questionario;
	}

}
