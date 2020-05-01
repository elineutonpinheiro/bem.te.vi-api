package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
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
@EqualsAndHashCode(of="id")
public class Unidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	@Embedded
	private Endereco endereco;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private boolean ativa;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_instituicao_id"))
	@Getter @Setter
	private Instituicao instituicao;

	public Unidade(String nome, Endereco endereco, String telefone, String email, 
			boolean ativa, Instituicao instituicao) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.ativa = ativa;
		this.instituicao = instituicao;
	}
	
	

}
