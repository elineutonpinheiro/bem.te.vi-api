package com.elineuton.bemtevi.api.repositories.atividade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.repositories.filter.AtividadeFilter;
import com.elineuton.bemtevi.api.services.TurmaService;

public class AtividadeRepositoryImpl implements AtividadeRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	TurmaService turmaService;
	
	@Override
	public List<Atividade> filtrar(Turma turma, AtividadeFilter atividadeFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Atividade> criteria = builder.createQuery(Atividade.class);
		Root<Atividade> root = criteria.from(Atividade.class);
		
		//Criar as restrições
		Predicate[] predicates = criarRestricoes(turma, atividadeFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Atividade> query = manager.createQuery(criteria);
		return query.getResultList();
		
	}
	
	private Predicate[] criarRestricoes(Turma turma, AtividadeFilter atividadeFilter, CriteriaBuilder builder,
			Root<Atividade> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		Turma t = turmaService.consultarPorId(turma.getId());
		
		predicates.add(builder.equal(root.get("turma"), t));
		
		if(!StringUtils.isEmpty(atividadeFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get("descricao")), "%" + atividadeFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if(atividadeFilter.getDataCriacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataCriacao"), atividadeFilter.getDataCriacaoDe()));
		}
		
		if(atividadeFilter.getDataCriacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataCriacao"), atividadeFilter.getDataCriacaoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
