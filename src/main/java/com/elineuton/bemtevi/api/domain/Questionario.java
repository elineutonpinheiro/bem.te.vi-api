package com.elineuton.bemtevi.api.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Questionario {
	
	private String cafeDaManha;
	
	private String lancheDaManha;
	
	private String almoco;
	
	private String lancheDaTarde;
	
	private Integer banho;
	
	private Integer fralda;
	
	private Integer escovacao;
	
	private boolean dormiu;
	
	private String estadoDoSono;
	
	private boolean febre;
	
	private String urina;
	
	private String evacuacao;
	
	private String interacao;
	
	private String participacao;
	
	private String observacao;

}
