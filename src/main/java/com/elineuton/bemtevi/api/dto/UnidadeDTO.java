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
	private boolean ativa;
	
	@Getter @Setter
	private Integer instituicaoId;
	
	public UnidadeDTO(Unidade unidade) {
		this.id = unidade.getId();
		this.nome = unidade.getNome();
		this.endereco = unidade.getEndereco();
		this.telefone = unidade.getTelefone();
		this.email = unidade.getEmail();
		this.ativa = unidade.getAtiva();
		this.instituicaoId = unidade.getInstituicao().getId();
	}

}
