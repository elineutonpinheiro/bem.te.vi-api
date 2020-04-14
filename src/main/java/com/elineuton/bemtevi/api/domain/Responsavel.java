package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.elineuton.bemtevi.api.domain.enums.TipoParentesco;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Responsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter	@Setter
	private Integer id;
	
	@Getter	@Setter
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Getter	@Setter
	private TipoParentesco parentesco;
	
	@JsonIgnore
	@Getter	@Setter
	private String email;

	@JsonIgnore
	@Getter	@Setter
	private String senha;
	
	@Getter	@Setter
	private Boolean ativo;
	
	@Getter	@Setter
	@Transient
	private Long qtdeMatriculas;

	public Responsavel(String nome, TipoParentesco parentesco, String email, String senha, Boolean ativo) {
		this.nome = nome;
		this.parentesco = parentesco;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
	}

	
}
