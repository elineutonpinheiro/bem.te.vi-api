package com.elineuton.bemtevi.api.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
	private List<Atividade> atividades;

}
