package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
	
	Responsavel findByEmail(String email);
	
	@Query("select distinct (m.aluno.responsavel) from Matricula m where m.turma.unidade.instituicao = :instituicao")
	List<Responsavel> findByInstituicao(@Param("instituicao") Instituicao instituicao);
	

}
