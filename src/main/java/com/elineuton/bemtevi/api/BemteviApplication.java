package com.elineuton.bemtevi.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.repositories.InstituicaoRepository;

@SpringBootApplication
public class BemteviApplication implements CommandLineRunner{

	@Autowired
	InstituicaoRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(BemteviApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// Instancia os objetos quando o programa inicia
		
		Instituicao i1 = new Instituicao(null, "Vovó Francisca");
		Instituicao i2 = new Instituicao(null, "Vovó Ataíde");
		
		repo.saveAll(Arrays.asList(i1, i2));
		
	}
	
	

}
