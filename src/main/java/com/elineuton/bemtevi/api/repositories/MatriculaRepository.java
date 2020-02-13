package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

	List<Matricula> findByAluno(Aluno aluno);
	
}
