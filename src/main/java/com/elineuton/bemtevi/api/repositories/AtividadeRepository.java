package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Turma;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {

	List<Atividade> findByTurma(Turma turma);
	
}
