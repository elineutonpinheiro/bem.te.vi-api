package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AnoLetivo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicial;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFinal;
	
	@OneToMany(mappedBy = "anoLetivo", fetch = FetchType.LAZY)
	@Getter @Setter
	@JsonIgnore
	private Set<Turma> turma = new HashSet<>();

	public AnoLetivo(Integer id, String descricao, LocalDate dataInicial, LocalDate dataFinal) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

}
