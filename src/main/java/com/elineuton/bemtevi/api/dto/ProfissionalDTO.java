package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.bemtevi.api.domain.Profissional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ProfissionalDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String cargo;
	
	@Getter @Setter
	private String telefone;
	
	//private List<TurmaDTO> turmaDto = new ArrayList<>();
	
	public ProfissionalDTO(Profissional obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cargo = obj.getCargo();
		this.telefone = obj.getTelefone();
	}
	
	
	
}
