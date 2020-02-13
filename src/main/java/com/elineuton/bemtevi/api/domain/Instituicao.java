package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of="id")
public class Instituicao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter 
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@OneToMany(mappedBy = "instituicao", fetch = FetchType.LAZY)
	private Set<Unidade> unidades = new HashSet<>();
	
	@Getter @Setter
	private Boolean ativa;

	public Instituicao(String nome, Boolean ativa) {
		this.nome = nome;
		this.ativa = ativa;
	}

}
