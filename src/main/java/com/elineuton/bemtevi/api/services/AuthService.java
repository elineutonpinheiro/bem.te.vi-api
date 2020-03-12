package com.elineuton.bemtevi.api.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;
import com.elineuton.bemtevi.api.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	ProfissionalRepository profissionalRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	private Random rand = new Random();
	
	public void enviarNovaSenha(String email) {
		
		Profissional profissional = profissionalRepository.findByEmail(email);
		if (profissional == null) {
			throw new ObjectNotFoundException("Email inválido");
		}
		
		String novaSenha = novaSenha();
		profissional.setSenha(passwordEncoder.encode(novaSenha));
		
		profissionalRepository.save(profissional);
		System.out.println("Sua nova senha é: " + novaSenha);
		
	}

	private String novaSenha() {
		char[] vet = new char[8];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int op = rand.nextInt(3);
		
		if (op == 0) {
			return (char) (rand.nextInt(10) + 48); 
		}
		
		if (op == 1) {
			return (char) (rand.nextInt(26) + 65); 
		}
		
		if (op == 2) {
			return (char) (rand.nextInt(26) + 97); 
		}
		
		return 0;
	}
}
