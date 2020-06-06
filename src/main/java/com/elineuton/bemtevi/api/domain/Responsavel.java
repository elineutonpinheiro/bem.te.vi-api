package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.elineuton.bemtevi.api.domain.enums.Perfil;
import com.elineuton.bemtevi.api.domain.enums.TipoParentesco;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(of = "id")
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
	private Perfil perfil;
	
	@Getter	@Setter
	private boolean ativo;
	
	@Getter	@Setter
	@Transient
	private Long qtdeMatriculas;
	
	public Responsavel() {
		this.perfil = Perfil.RESPONSAVEL;
	}
	
	public Responsavel(String nome, TipoParentesco parentesco, String email, boolean ativo) {
		this.nome = nome;
		this.parentesco = parentesco;
		this.email = email;
		this.ativo = ativo;
		this.perfil = Perfil.RESPONSAVEL;
	}
	
}
