package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Responsavel;
import com.elineuton.bemtevi.api.domain.Turma;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	
	@Query("select a from Aluno a join Matricula m on a.id = m.id.aluno and m.id.turma = :turma")
	List<Aluno> findByTurma(@Param("turma") Turma turma);
	
	List<Aluno> findByResponsavel(Responsavel responsavel);
	
	

}
