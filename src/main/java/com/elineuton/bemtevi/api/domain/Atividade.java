package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Atividade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHora;
	
	@ManyToOne
	@Getter @Setter
	@JoinColumn(name = "turma_id", foreignKey = @ForeignKey(name = "fk_turma_id"), nullable = false)
	private Turma turma;

}
