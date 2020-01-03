package com.elineuton.bemtevi.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profissional implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String cargo;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String senha;
	
	/*
	 * @JsonIgnore
	 * @OneToMany(mappedBy = "id.profissional")
	 * @Getter @Setter private Set<Lotacao> lotacoes = new HashSet<>();
	 */

	public Profissional(Integer id, String nome, String cargo, String telefone, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
	}
	
	/*
	 * @JsonIgnore public List<Turma> getTurmas() { List<Turma> lista = new
	 * ArrayList<>(); for(Lotacao x: lotacoes) { lista.add(x.getTurma()); } return
	 * lista; }
	 */

}
