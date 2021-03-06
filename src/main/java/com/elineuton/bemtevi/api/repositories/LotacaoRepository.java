package com.elineuton.bemtevi.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Lotacao;
import com.elineuton.bemtevi.api.domain.Profissional;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Integer> {
	
	List<Lotacao> findByProfissional(Profissional profissional);
	
}
