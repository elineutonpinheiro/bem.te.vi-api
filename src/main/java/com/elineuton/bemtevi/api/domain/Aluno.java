package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String sobrenome;
	
	@Getter @Setter
	private String dataNascimento;
	
	@OneToOne
	@Getter @Setter
	private Turma turma;
	
	@Getter @Setter
	@ElementCollection
	@CollectionTable(name = "aluno_pessoal_autorizado", joinColumns = @JoinColumn(name = "aluno_id"))
	@Column(name = "pessoal_autorizado")
	private Set<String> pessoalAutorizado = new HashSet<>();

	public Aluno(Integer id, String nome, String sobrenome, String dataNascimento, Turma turma) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.turma = turma;
	}
	

}
