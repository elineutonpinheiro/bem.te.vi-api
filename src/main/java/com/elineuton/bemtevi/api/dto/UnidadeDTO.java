package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import com.elineuton.bemtevi.api.domain.Endereco;
import com.elineuton.bemtevi.api.domain.Unidade;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UnidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private Endereco endereco;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String status;
	
	@Getter @Setter
	private Integer instituicaoId;
	
	public UnidadeDTO(Unidade obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.endereco = obj.getEndereco();
		this.telefone = obj.getTelefone();
		this.email = obj.getEmail();
		this.status = obj.getStatus();
		this.instituicaoId = obj.getInstituicao().getId();
	}

}
