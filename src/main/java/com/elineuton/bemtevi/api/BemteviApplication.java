package com.elineuton.bemtevi.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Endereco;
import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.repositories.AnoLetivoRepository;
import com.elineuton.bemtevi.api.repositories.AtividadeRepository;
import com.elineuton.bemtevi.api.repositories.InstituicaoRepository;
import com.elineuton.bemtevi.api.repositories.TurmaRepository;
import com.elineuton.bemtevi.api.repositories.UnidadeRepository;

@SpringBootApplication
public class BemteviApplication implements CommandLineRunner{

	@Autowired
	InstituicaoRepository instituicaoRepository;
	
	@Autowired
	UnidadeRepository unidadeRepository;
	
	@Autowired
	AnoLetivoRepository anoLetivoRepository;
	
	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	AtividadeRepository atividadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BemteviApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// Instancia os objetos quando o programa inicia
		
		Instituicao i1 = new Instituicao(null, "Vovó Francisca");
		Instituicao i2 = new Instituicao(null, "Vovó Ataíde");
		
		Endereco e1 = new Endereco("Av.", "127","A","Alvorada","Boa Vista","Roraima","69317234");
		
		Unidade u1 = new Unidade(null, "Unidade 1", e1, "36237090","unidade1@vovofrancisca.com","ativa", i1);
		Unidade u2 = new Unidade(null, "Unidade 2", e1, "36231121","unidade2@vovofrancisca.com","inativa", i1);
		Unidade u3 = new Unidade(null, "Unidade 1", e1, "36250010","unidade1@vovoataide.com","ativa", i2);
		
		AnoLetivo al1 = new AnoLetivo(null, "2020", LocalDate.now(), LocalDate.now().plusYears(1));
		
		List<Atividade> la1 = new ArrayList<>(); 
		
		Turma t1 = new Turma(null, "Infantil I", u1, "Integral", "101", "ativa", al1, la1);
		
		Atividade a1 = new Atividade(null, "Trabalhando a coordenação motora", LocalDateTime.now());
		
		t1.getAtividades().addAll(Arrays.asList(a1));
		
	
		instituicaoRepository.saveAll(Arrays.asList(i1, i2));
		unidadeRepository.saveAll(Arrays.asList(u1, u2, u3));
		anoLetivoRepository.saveAll(Arrays.asList(al1));
		atividadeRepository.saveAll(la1);
		turmaRepository.saveAll(Arrays.asList(t1));
		atividadeRepository.saveAll(Arrays.asList(a1));
		
	}
	
	

}
