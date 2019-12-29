package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

	
	  @Query("select t from Turma t where :profissional member t.profissionais") 
	  List<Turma> findByProfissional(@Param("profissional") Profissional profissional);
	  
	
}
