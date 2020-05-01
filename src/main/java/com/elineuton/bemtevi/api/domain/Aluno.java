package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private String dataNascimento;
	
	@Getter @Setter
	private LocalDate dataPresenca;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_responsavel_id"))
	@Getter @Setter
	private Responsavel responsavel;

	/*
	 * @ElementCollection
	 * 
	 * @CollectionTable(name = "aluno_pessoal_autorizado", joinColumns
	 * = @JoinColumn(name = "aluno_id", foreignKey = @ForeignKey(name =
	 * "fk_aluno_id")))
	 * 
	 * @Column(name = "pessoal_autorizado")
	 * 
	 * @Getter @Setter private Set<String> pessoalAutorizado = new HashSet<>();
	 */
	
	@Getter @Setter
	private Boolean ativo;

	public Aluno(String nome, String dataNascimento, Responsavel responsavel, Boolean ativo) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.responsavel = responsavel;
		this.ativo = ativo;
	}

}
