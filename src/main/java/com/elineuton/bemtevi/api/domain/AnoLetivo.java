package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AnoLetivo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicial;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFinal;

}
