package com.elineuton.bemtevi.api.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private String email;
}
