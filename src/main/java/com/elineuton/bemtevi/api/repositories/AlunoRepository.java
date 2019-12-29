package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Turma;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	
	List<Aluno> findByTurma(Turma turma);
	

}
