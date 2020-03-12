/*package com.elineuton.bemtevi.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;
import com.elineuton.bemtevi.api.security.Usuario;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
	private ProfissionalRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Profissional profissional = repo.findByEmail(email);
		if (profissional == null) {
			throw new UsernameNotFoundException(email);
		}
		return new Usuario(profissional.getId(), profissional.getEmail(), profissional.getSenha(), profissional.getPerfis());
	}

}
*/