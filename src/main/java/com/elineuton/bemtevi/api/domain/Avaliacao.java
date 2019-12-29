package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_aluno_id"))
	@Getter @Setter
	private Aluno aluno;
	
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_profissional_id"))
	@Getter @Setter
	private Profissional profissional;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHora;
	
	@Getter @Setter
	private String status;
	
	@OneToMany(fetch = FetchType.LAZY)
	@Getter @Setter
	@JoinTable(name="avaliacao_resposta",
	  joinColumns=@JoinColumn(name="avaliacao_id", foreignKey = @ForeignKey(name ="fk_avaliacao_id")),
	  inverseJoinColumns=@JoinColumn(name="resposta_id", foreignKey = @ForeignKey(name = "fk_resposta_id")))
	private List<Resposta> respostas = new ArrayList<Resposta>();

	public Avaliacao(Integer id, Aluno aluno, Profissional profissional, LocalDateTime dataHora, String status) {
		super();
		this.id = id;
		this.aluno = aluno;
		this.profissional = profissional;
		this.dataHora = dataHora;
		this.status = status;
	}

}
