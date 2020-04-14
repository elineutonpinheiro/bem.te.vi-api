package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Turma;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
	
	Profissional findByEmail(String email);
	
	@Query("select distinct (l.profissional) from Lotacao l where l.turma.unidade.instituicao = :instituicao")
	List<Profissional> findByInstituicao(@Param("instituicao") Instituicao instituicao);
	

}
