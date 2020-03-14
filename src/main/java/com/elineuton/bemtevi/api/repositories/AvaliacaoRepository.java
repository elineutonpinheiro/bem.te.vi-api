package com.elineuton.bemtevi.api.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
	
	List<Avaliacao> findByAluno(Aluno aluno);
	
	Avaliacao findByAlunoAndData(Aluno aluno, LocalDate data);
	
}

/* CONSULTAS QUE NÃO SERÃO USADAS

List<Avaliacao> findByProfissional(Profissional profissional);
	
	@Query("select a from Avaliacao a join Matricula m"
			+ " on a.aluno = m.aluno"
			+ " join Lotacao l"
			+ " on a.profissional = l.profissional"
			+ " where m.turma = l.turma"
			+ " and m.turma = :turma")
	List<Avaliacao> findByTurma(@Param("turma") Turma turma);
	
	@Query("select a from Avaliacao a join Matricula m"
			+ " on a.aluno = m.id.aluno"
			+ " join Lotacao l"
			+ " on a.profissional = l.profissional"
			+ " where m.id.turma = l.turma"
			+ " and m.id.turma = :turma"
			+ " and a.profissional = :profissional")
	List<Avaliacao> findByTurmaEProfissional(@Param("turma") Turma turma, @Param("profissional") Profissional profissional);

 */