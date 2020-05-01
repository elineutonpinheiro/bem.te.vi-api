package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.bemtevi.api.domain.Matricula;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class MatriculaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Integer idAluno;
	
	@Getter @Setter
	private String nomeAluno;
	
	@Getter @Setter
	private Integer idTurma;
	
	@Getter @Setter
	private String nomeTurma;
	
	@Getter @Setter
	private String salaTurma;
	
	@Getter @Setter
	private String unidade;
	
	@Getter @Setter
	private String instituicao;

	public MatriculaNewDTO(Matricula matricula) {
		this.idAluno = matricula.getAluno().getId();
		this.nomeAluno = matricula.getAluno().getNome();
		this.idTurma = matricula.getTurma().getId();
		this.nomeTurma = matricula.getTurma().getNome();
		this.salaTurma = matricula.getTurma().getSala();
		this.unidade = matricula.getTurma().getUnidade().getNome();
		this.instituicao = matricula.getTurma().getUnidade().getInstituicao().getNome();
	}
	

}
