package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.elineuton.bemtevi.api.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
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

	@Getter	@Setter
	private String telefone;

	@JsonIgnore
	@Getter	@Setter
	private String codigoAcesso;

	@JsonIgnore
	@Getter	@Setter
	private String senha;
	
	@Getter	@Setter
	private Boolean ativo;
	
	private Integer perfil;

	public Profissional(String nome, String cargo, String telefone, 
			String codigoAcesso, String senha, Boolean ativo, Perfil perfil) {
		this.nome = nome;
		this.cargo = cargo;
		this.telefone = telefone;
		this.codigoAcesso = codigoAcesso;
		this.senha = senha;
		this.ativo = ativo;
		this.perfil = perfil.getCod();
	}
	
	public Perfil getPerfil() {
		return Perfil.toEnum(perfil);
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil.getCod();
	}
	
}
