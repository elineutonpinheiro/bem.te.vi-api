package com.elineuton.bemtevi.api.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.domain.Instituicao;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoResource {
	
	@GetMapping
	public List<Instituicao> listar() {
		Instituicao i1 = new Instituicao(1,"Vovô Francisca");
		Instituicao i2 = new Instituicao(2,"Vovô Ataíde");
		
		List<Instituicao> lista = new ArrayList<>();
		lista.add(i1);
		lista.add(i2);
		
		return lista;
	}

}
