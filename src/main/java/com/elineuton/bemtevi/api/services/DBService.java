package com.elineuton.bemtevi.api.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Endereco;
import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Lotacao;
import com.elineuton.bemtevi.api.domain.Matricula;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Responsavel;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.domain.enums.Perfil;
import com.elineuton.bemtevi.api.domain.enums.TipoParentesco;
import com.elineuton.bemtevi.api.repositories.AlunoRepository;
import com.elineuton.bemtevi.api.repositories.AnoLetivoRepository;
import com.elineuton.bemtevi.api.repositories.AtividadeRepository;
import com.elineuton.bemtevi.api.repositories.AvaliacaoRepository;
import com.elineuton.bemtevi.api.repositories.InstituicaoRepository;
import com.elineuton.bemtevi.api.repositories.LotacaoRepository;
import com.elineuton.bemtevi.api.repositories.MatriculaRepository;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;
import com.elineuton.bemtevi.api.repositories.ResponsavelRepository;
import com.elineuton.bemtevi.api.repositories.TurmaRepository;
import com.elineuton.bemtevi.api.repositories.UnidadeRepository;

@Service
public class DBService {

	public void instantiateTestDatabase() {
		
		 
		 		
	}
}
