package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;

	@Getter	@Setter
	private String nome;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_unidade_id"))
	@Getter @Setter
	private Unidade unidade;

	@Getter	@Setter
	private String periodo;

	@Getter	@Setter
	private String sala;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_anoLetivo_id"))
	@Getter	@Setter
	private AnoLetivo anoLetivo;

	@OneToMany(mappedBy = "turma", fetch = FetchType.LAZY)
	@JsonIgnore
	@Getter @Setter
	private List<Atividade> atividades = new ArrayList<Atividade>();
	
	@Getter	@Setter
	private Boolean ativa;
	 
	public Turma(String nome, Unidade unidade, String periodo, String sala,
			AnoLetivo anoLetivo, Boolean ativa) {
		this.nome = nome;
		this.unidade = unidade;
		this.periodo = periodo;
		this.sala = sala;
		this.anoLetivo = anoLetivo;
		this.ativa = ativa;
	}

}
