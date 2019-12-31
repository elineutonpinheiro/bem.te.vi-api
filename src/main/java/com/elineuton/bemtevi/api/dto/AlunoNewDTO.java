package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AlunoNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String sobrenome;
	
	@Getter @Setter
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String dataNascimento;
	
	@Getter @Setter
	private Integer turmaId;
	
	@Getter @Setter
	private List<String> pessoalAutorizado = new ArrayList<>();
	
	public AlunoNewDTO(Aluno obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.sobrenome = obj.getSobrenome();
		this.dataNascimento = obj.getDataNascimento();
		
		Iterator<String> pessoalAutorizado = obj.getPessoalAutorizado().iterator();
        while (pessoalAutorizado.hasNext()){
               String it = pessoalAutorizado.next();
               this.pessoalAutorizado.add(it);
        }
		
		
		
	}
	
}
