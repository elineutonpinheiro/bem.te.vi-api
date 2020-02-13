package com.elineuton.bemtevi.api.repositories.atividade;

import java.util.List;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.repositories.filter.AtividadeFilter;

public interface AtividadeRepositoryQuery {
	
	public List<Atividade> filtrar(Turma turma, AtividadeFilter atividadeFilter);

}
