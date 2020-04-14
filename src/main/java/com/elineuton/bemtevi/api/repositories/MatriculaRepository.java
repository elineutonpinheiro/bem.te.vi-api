package com.elineuton.bemtevi.api.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Matricula;
import com.elineuton.bemtevi.api.domain.Responsavel;
import com.elineuton.bemtevi.api.domain.Turma;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

	List<Matricula> findByAluno(Aluno aluno);
	
	@Query("select count(m) from Matricula m where m.turma = :turma")
	Long countMatriculasPorTurma(@Param("turma") Turma turma);
	
	@Query("select count(m) from Matricula m where m.aluno.responsavel = :responsavel")
	Long countMatriculasPorResponsavel(@Param("responsavel") Responsavel responsavel);
	
	/*
	 * @Query("select m from Matricula m join Aluno a on m.aluno.id = a.id and a.responsavel = :responsavel and m.dataTermino >= :dataAtual"
	 * ) List<Matricula> findByResponsavel(@Param("responsavel") Responsavel
	 * responsavel, @Param("dataAtual") LocalDate dataAtual);
	 */
	
	@Query("select m from Matricula m join Aluno a on m.aluno.id = a.id and a.responsavel = :responsavel and m.dataTermino = null")
	List<Matricula> findByResponsavel(@Param("responsavel") Responsavel responsavel);
	
}
