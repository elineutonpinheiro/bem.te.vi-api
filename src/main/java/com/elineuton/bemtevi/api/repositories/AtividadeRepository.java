package com.elineuton.bemtevi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elineuton.bemtevi.api.domain.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {

}
