package com.elineuton.bemtevi.api.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elineuton.bemtevi.api.dto.CodigoAcessoDTO;
import com.elineuton.bemtevi.api.security.JWTUtil;
import com.elineuton.bemtevi.api.security.Usuario;
import com.elineuton.bemtevi.api.services.AuthService;
import com.elineuton.bemtevi.api.services.UsuarioService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		Usuario user = UsuarioService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody CodigoAcessoDTO codigoAcessoDto) {
		service.enviarNovaSenha(codigoAcessoDto.getCodigoAcesso());
		return ResponseEntity.noContent().build();
	}

}
