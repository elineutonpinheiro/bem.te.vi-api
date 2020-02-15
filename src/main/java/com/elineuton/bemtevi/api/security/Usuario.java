package com.elineuton.bemtevi.api.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.elineuton.bemtevi.api.domain.enums.Perfil;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	private String codigoAcesso;
	
	private String senha;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public Usuario(Integer id, String codigoAcesso, String senha, Set<Perfil> perfis) {
		this.id = id;
		this.codigoAcesso = codigoAcesso;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return codigoAcesso;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
