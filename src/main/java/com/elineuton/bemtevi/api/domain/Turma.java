package com.elineuton.bemtevi.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@ManyToOne
	@Getter @Setter
	private Unidade unidade;
	
	@Getter @Setter
	private String periodo;
	
	@Getter @Setter
	private String sala;
	
	@Getter @Setter
	private String status;
	
	@ManyToOne
	@Getter @Setter
	private AnoLetivo anoLetivo;
	
	@OneToMany
	@Getter @Setter
	private List<Atividade> atividades = new ArrayList<Atividade>();

	public Turma(Integer id, String nome, Unidade unidade, String periodo, String sala, String status,
			AnoLetivo anoLetivo) {
		super();
		this.id = id;
		this.nome = nome;
		this.unidade = unidade;
		this.periodo = periodo;
		this.sala = sala;
		this.status = status;
		this.anoLetivo = anoLetivo;
	}

}
