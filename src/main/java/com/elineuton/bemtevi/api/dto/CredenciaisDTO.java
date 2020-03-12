package com.elineuton.bemtevi.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CredenciaisDTO {

	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String senha;
	
}
