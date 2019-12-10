package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Unidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	//TODO IMPLEMENTAR COM EMBEDDED
	//@Getter @Setter
	//private String endereco;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String status;
	
	@ManyToOne
	@Getter @Setter
	private Instituicao instituicao;

}
