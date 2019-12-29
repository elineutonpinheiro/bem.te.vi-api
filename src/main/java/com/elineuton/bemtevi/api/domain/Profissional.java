package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String cargo;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String senha;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	@Getter @Setter
	  @JoinTable(name="profissional_turma",
	  joinColumns=@JoinColumn(name="profissional_id", foreignKey = @ForeignKey(name = "fk_profissional_id")),
	  inverseJoinColumns=@JoinColumn(name="turma_id", foreignKey = @ForeignKey(name = "fk_turma_id"))) 
	private Set<Turma> turmas = new HashSet<Turma>();

	public Profissional(Integer id, String nome, String cargo, String telefone, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
	}
	

}
