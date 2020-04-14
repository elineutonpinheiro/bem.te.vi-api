package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {

	List<Unidade> findByInstituicao(Instituicao instituicao);
	
	/*
	 * @Query("select (t.unidade.nome) from Turma t join Lotacao 'l on t.id = l.turma.id and l.id.profissional = :profissional"
	 * ) List<String> findNomeUnidadeByProfissional(@Param("profissional")
	 * Profissional profissional);
	 */
}
