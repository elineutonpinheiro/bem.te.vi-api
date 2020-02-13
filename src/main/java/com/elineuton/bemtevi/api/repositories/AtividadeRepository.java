package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.repositories.atividade.AtividadeRepositoryQuery;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer>, AtividadeRepositoryQuery {

	List<Atividade> findByTurma(Turma turma);
	
	//List<Atividade> findByTurmaAndDescricaoContaining(Turma turma, String descricao);
	
	//List<Atividade> findByTurmaAndDescricaoContaining(Turma turma, String Descricao);
	
}
