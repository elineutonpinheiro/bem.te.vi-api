package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {

	List<Unidade> findByInstituicao(Instituicao instituicao);
	
}
