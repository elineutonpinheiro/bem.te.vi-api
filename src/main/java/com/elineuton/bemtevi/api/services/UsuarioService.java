package com.elineuton.bemtevi.api.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.elineuton.bemtevi.api.security.Usuario;

public class UsuarioService {
	
	public static Usuario authenticated() {
		try {
			return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
