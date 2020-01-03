package com.elineuton.bemtevi.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elineuton.bemtevi.api.domain.Aluno;
import com.elineuton.bemtevi.api.domain.AnoLetivo;
import com.elineuton.bemtevi.api.domain.Atividade;
import com.elineuton.bemtevi.api.domain.Avaliacao;
import com.elineuton.bemtevi.api.domain.Endereco;
import com.elineuton.bemtevi.api.domain.Instituicao;
import com.elineuton.bemtevi.api.domain.Lotacao;
import com.elineuton.bemtevi.api.domain.Matricula;
import com.elineuton.bemtevi.api.domain.Profissional;
import com.elineuton.bemtevi.api.domain.Questao;
import com.elineuton.bemtevi.api.domain.Resposta;
import com.elineuton.bemtevi.api.domain.Turma;
import com.elineuton.bemtevi.api.domain.Unidade;
import com.elineuton.bemtevi.api.repositories.AlunoRepository;
import com.elineuton.bemtevi.api.repositories.AnoLetivoRepository;
import com.elineuton.bemtevi.api.repositories.AtividadeRepository;
import com.elineuton.bemtevi.api.repositories.AvaliacaoRepository;
import com.elineuton.bemtevi.api.repositories.InstituicaoRepository;
import com.elineuton.bemtevi.api.repositories.LotacaoRepository;
import com.elineuton.bemtevi.api.repositories.MatriculaRepository;
import com.elineuton.bemtevi.api.repositories.ProfissionalRepository;
import com.elineuton.bemtevi.api.repositories.QuestaoRepository;
import com.elineuton.bemtevi.api.repositories.RespostaRepository;
import com.elineuton.bemtevi.api.repositories.TurmaRepository;
import com.elineuton.bemtevi.api.repositories.UnidadeRepository;

@SpringBootApplication
public class BemteviApplication implements CommandLineRunner{

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
	QuestaoRepository questaoRepository;
	
	@Autowired
	RespostaRepository respostaRepository;
	
	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	LotacaoRepository lotacaoRepository;
	
	@Autowired
	MatriculaRepository matriculaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BemteviApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Formatação de Data e Hora de Criação de Atividade e Avaliação
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    String agoraFormatado = agora.format(formatter);
	    LocalDateTime parsedDateTime = LocalDateTime.parse(agoraFormatado, formatter);
	    
	    //Formatação de Data de Ano Letivo
	    LocalDate date = LocalDate.now();
	    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String text = date.format(formatterDate);
	    LocalDate parsedDate = LocalDate.parse(text, formatterDate);
	    
		
		// Instancia os objetos quando o programa inicia
		
		Instituicao i1 = new Instituicao(null, "Vovó Francisca");
		Instituicao i2 = new Instituicao(null, "Vovó Ataíde");
		
		Endereco e1 = new Endereco("Av.", "127","A","Alvorada","Boa Vista","Roraima","69317234");
		
		Unidade u1 = new Unidade(null, "Unidade 1", e1, "36237090","unidade1@vovofrancisca.com","ativa", i1);
		Unidade u2 = new Unidade(null, "Unidade 2", e1, "36231121","unidade2@vovofrancisca.com","inativa", i1);
		Unidade u3 = new Unidade(null, "Unidade 1", e1, "36250010","unidade1@vovoataide.com","ativa", i2);
		
		//-------------------------------------------------------------------------------
		//Salva Instituição e Unidade
		
		instituicaoRepository.saveAll(Arrays.asList(i1, i2));
		unidadeRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		//-------------------------------------------------------------------------------
		
		AnoLetivo al1 = new AnoLetivo(null, "2020", date, parsedDate.plusYears(1));
		
		anoLetivoRepository.saveAll(Arrays.asList(al1));
		
		Turma t1 = new Turma(null, "Infantil I", u1, "Integral", "101", "ativa", al1);
		Turma t2 = new Turma(null, "Infantil II", u1, "Integral", "101", "ativa", al1);
		Turma t3 = new Turma(null, "Infantil III", u1, "Integral", "101", "ativa", al1);
		
		//Salva Turma 1 e 2 
		turmaRepository.saveAll(Arrays.asList(t1,t2));
		
		
		t3 = turmaRepository.save(t3);
		
		Atividade a1 = new Atividade(null, "Trabalhando a coordenação motora", parsedDateTime, t3);
		
		//-------------------------------------------------------------------------------
		//Salva Ano Letivo, Turma e Atividade
	
		atividadeRepository.saveAll(Arrays.asList(a1));
		
		
		//-------------------------------------------------------------------------------
		
		Profissional p1 = new Profissional(null, "Claudia Santos", "Cuidadora", "(95) 99110-1010", "claudiasantos@bemtevi.com", "123");
		Profissional p2 = new Profissional(null, "Yenniver Muniz", "Cuidadora", "(95) 99000-2020", "yennivermuniz@bemtevi.com", "100");
		
		//p1.getTurmas().addAll(Arrays.asList(t1,t3));
		//p2.getTurmas().addAll(Arrays.asList(t2));
		
		Lotacao lot1 = new Lotacao(t1, p2, LocalDate.now());
		Lotacao lot2 = new Lotacao(t2, p1, LocalDate.now());
		Lotacao lot3 = new Lotacao(t3, p2, LocalDate.now());
		
		/*
		 * t1.getLotacoes().addAll(Arrays.asList(lot1));
		 * t2.getLotacoes().addAll(Arrays.asList(lot2));
		 * 
		 * p1.getLotacoes().addAll(Arrays.asList(lot1));
		 * p2.getLotacoes().addAll(Arrays.asList(lot2));
		 */
		
		/* TURMA CONHECENDO SEUS PROFISSIONAIS
		 * t1.getProfissionais().addAll(Arrays.asList(p1));
		 * t3.getProfissionais().addAll(Arrays.asList(p1));
		 */
		
		Aluno alu1 = new Aluno(null, "Paulo", "da Costa Luz", "12/12/2019");
		Aluno alu2 = new Aluno(null, "Isis", "da Conceição", "12/12/2019");
		
		alu1.getPessoalAutorizado().addAll(Arrays.asList("Francisco Elineuton", "Thiago Ventura"));
		
		//-------------------------------------------------------------------------------
		//Salva Profissional e Aluno
		profissionalRepository.saveAll(Arrays.asList(p1, p2));
		alunoRepository.saveAll(Arrays.asList(alu1, alu2));
		
		lotacaoRepository.saveAll(Arrays.asList(lot1, lot2, lot3));
		
		//Um aluno não pode estar matriculado em mais de uma turma no mesmo ano
		
		Matricula mat1 = new Matricula(t1, alu1, LocalDate.of(2010, 10, 01));
		Matricula mat2 = new Matricula(t2, alu2, LocalDate.of(2010, 10, 01));
		Matricula mat3 = new Matricula(t3, alu2, LocalDate.of(2011, 10, 01));
		
		matriculaRepository.saveAll(Arrays.asList(mat1, mat2, mat3));
		
		//-------------------------------------------------------------------------------
		
		Questao q1 = new Questao(null, "Café da Manhã");
		Questao q2 = new Questao(null, "Lanche da Manhã");
		
		Resposta r1 = new Resposta(null, q1, "Pouco");
		Resposta r2 = new Resposta(null, q2, "Não aceitou");
		
		Avaliacao av1 = new Avaliacao(null, alu1, p1, parsedDateTime, "Suspensa");
		
		av1.getRespostas().addAll(Arrays.asList(r1, r2));
		
		//-------------------------------------------------------------------------------
		//Salva Questão, Resposta e Avaliação
	
		questaoRepository.saveAll(Arrays.asList(q1, q2));
		respostaRepository.saveAll(Arrays.asList(r1, r2));
		avaliacaoRepository.saveAll(Arrays.asList(av1));
		//-------------------------------------------------------------------------------
	}
	
	

}
