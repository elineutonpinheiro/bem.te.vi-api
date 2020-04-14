package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.elineuton.bemtevi.api.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(of = "id")
public class Profissional implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;

	@Getter	@Setter
	private String nome;

	@Getter	@Setter
	private String cargo;

	@JsonIgnore
	@Getter	@Setter
	private String email;

	@JsonIgnore
	@Getter	@Setter
	private String senha;
	
	@Getter	@Setter
	private Boolean ativo;
	
	/*
	 * @Getter @Setter
	 * 
	 * @Transient private List<String> unidades;
	 * 
	 * @Getter @Setter
	 * 
	 * @Transient private List<String> turmas;
	 */
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Profissional() {
		addPerfil(Perfil.PROFISSIONAL);
	}
	
	public Profissional(String nome, String cargo, String email, String senha, Boolean ativo) {
		this.nome = nome;
		this.cargo = cargo;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		addPerfil(Perfil.PROFISSIONAL);
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
}
