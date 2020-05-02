package com.elineuton.bemtevi.api.services;

import java.time.LocalDate;
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
	
	@Autowired
	InstituicaoRepository instituicaoRepository;
	
	@Autowired
	UnidadeRepository unidadeRepository;
	
	@Autowired
	AnoLetivoRepository anoLetivoRepository;
	
	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	AtividadeRepository atividadeRepository;
	
	@Autowired
	ProfissionalRepository profissionalRepository;
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	LotacaoRepository lotacaoRepository;
	
	@Autowired
	MatriculaRepository matriculaRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEnconder;
	
	@Autowired
	ResponsavelRepository responsavelRepository;

	public void instantiateTestDatabase() {
		/*
		 * //Formatação de Data e Hora de Criação de Atividade e Avaliação
		 * //LocalDateTime agora = LocalDateTime.now(); //DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); //String agoraFormatado =
		 * agora.format(formatter); //LocalDateTime parsedDateTime =
		 * LocalDateTime.parse(agoraFormatado, formatter);
		 * 
		 * //Formatação de Data de Ano Letivo LocalDate date = LocalDate.now();
		 * DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 * String text = date.format(formatterDate); LocalDate parsedDate =
		 * LocalDate.parse(text, formatterDate);
		 * 
		 * // Instancia os objetos quando o programa inicia
		 * 
		 * Instituicao i1 = new Instituicao("Vovó Francisca", true); Instituicao i2 =
		 * new Instituicao("Vovó Ataíde", true);
		 * 
		 * Endereco e1 = new Endereco("Rua Euclides Gomes da Silva",
		 * "127","A","Alvorada","Boa Vista","Roraima","69317234"); Endereco e2 = new
		 * Endereco("Av. dos Garimpeiros",
		 * "1010","A","Pintolândia","Boa Vista","Roraima","69300205");
		 * 
		 * Unidade u1 = new Unidade("Unidade Alvorada", e1,
		 * "36237090","unidade1@vovofrancisca.com", true, i1); Unidade u2 = new
		 * Unidade("Unidade Pintolândia", e2, "36231121","unidade2@vovofrancisca.com",
		 * true, i2);
		 * 
		 * //Salva Instituição e Unidade instituicaoRepository.saveAll(Arrays.asList(i1,
		 * i2)); unidadeRepository.saveAll(Arrays.asList(u1, u2));
		 * 
		 * AnoLetivo al1 = new AnoLetivo("2020", date, parsedDate.plusYears(1));
		 * 
		 * anoLetivoRepository.saveAll(Arrays.asList(al1));
		 * 
		 * Turma t1 = new Turma("Infantil I", u1, "Integral", "101", al1, true); Turma
		 * t2 = new Turma("Infantil II", u2, "Integral", "201", al1, true); Turma t3 =
		 * new Turma("Fundamental III", u2, "Integral", "301", al1, true);
		 * 
		 * //Salva Turmas turmaRepository.saveAll(Arrays.asList(t1,t2)); t3 =
		 * turmaRepository.save(t3);
		 * 
		 * Atividade a1 = new Atividade("Trabalhando a coordenação motora",
		 * LocalDate.now(), t3); Atividade a2 = new Atividade("Abracadabra zzz",
		 * LocalDate.now().plusMonths(1), t2);
		 * 
		 * //Salva atividades atividadeRepository.saveAll(Arrays.asList(a1,a2));
		 * 
		 * Profissional p1 = new Profissional("Claudia Santos", "Cuidadora",
		 * "claudiasantos@gmail.com", passwordEnconder.encode("123456"), true);
		 * Profissional p2 = new Profissional("Valter Dias", "Cuidador",
		 * "valterdias@gmail.com", passwordEnconder.encode("100100"), true);
		 * //p2.addPerfil(Perfil.ADMIN);
		 * 
		 * Lotacao lot1 = new Lotacao(t1, p1, LocalDate.now(),
		 * LocalDate.now().plusYears(1)); Lotacao lot2 = new Lotacao(t2, p2,
		 * LocalDate.now(), LocalDate.now().plusYears(1)); Lotacao lot3 = new
		 * Lotacao(t3, p2, LocalDate.now(), LocalDate.now().plusYears(1));
		 * 
		 * Responsavel resp1 = new Responsavel("Felipe Melim", TipoParentesco.PAI,
		 * "felipemelim@gmail.com", passwordEnconder.encode("456789"), true);
		 * Responsavel resp2 = new Responsavel("Livia Sousa", TipoParentesco.AVO,
		 * "liviasousa@gmail.com", passwordEnconder.encode("987654"), true);
		 * 
		 * responsavelRepository.saveAll(Arrays.asList(resp1, resp2));
		 * 
		 * Aluno alu1 = new Aluno("Paulo da Costa Melim", "12/12/2019", resp1, true);
		 * Aluno alu2 = new Aluno("Isis Maria Melim", "12/12/2019", resp1, true); Aluno
		 * alu3 = new Aluno("Tainara Kelly Sousa", "12/12/2019", resp2, true);
		 * 
		 * //alu1.getPessoalAutorizado().addAll(Arrays.asList("Francisco Elineuton",
		 * "Thiago Ventura"));
		 * 
		 * //Salva Profissional e Aluno profissionalRepository.saveAll(Arrays.asList(p1,
		 * p2)); alunoRepository.saveAll(Arrays.asList(alu1, alu2, alu3));
		 * 
		 * //Salva Lotações lotacaoRepository.saveAll(Arrays.asList(lot1, lot2, lot3));
		 * 
		 * //Um aluno não pode estar matriculado em mais de uma turma no mesmo ano
		 * Matricula mat1 = new Matricula(alu1, t1, null); Matricula mat2 = new
		 * Matricula(alu2, t2, null); Matricula mat3 = new Matricula(alu3, t3, null);
		 * 
		 * //Salva Matrículas matriculaRepository.saveAll(Arrays.asList(mat1, mat2,
		 * mat3));
		 */		
	}
}
