package com.elineuton.bemtevi.api.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.elineuton.bemtevi.api.domain.enums.Perfil;
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
	QuestaoRepository questaoRepository;
	
	@Autowired
	RespostaRepository respostaRepository;
	
	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	LotacaoRepository lotacaoRepository;
	
	@Autowired
	MatriculaRepository matriculaRepository;

	public void instantiateTestDatabase() {
		
		//Formatação de Data e Hora de Criação de Atividade e Avaliação
		//LocalDateTime agora = LocalDateTime.now();
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    //String agoraFormatado = agora.format(formatter);
	    //LocalDateTime parsedDateTime = LocalDateTime.parse(agoraFormatado, formatter);
	    
	    //Formatação de Data de Ano Letivo
	    LocalDate date = LocalDate.now();
	    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String text = date.format(formatterDate);
	    LocalDate parsedDate = LocalDate.parse(text, formatterDate);
		
		// Instancia os objetos quando o programa inicia
	    
		Instituicao i1 = new Instituicao("Vovó Francisca", true);
		Instituicao i2 = new Instituicao("Vovó Ataíde", true);
		
		Endereco e1 = new Endereco("Av.", "127","A","Alvorada","Boa Vista","Roraima","69317234");
		
		Unidade u1 = new Unidade("Unidade 1", e1, "36237090","unidade1@vovofrancisca.com", i1, true);
		Unidade u2 = new Unidade("Unidade 2", e1, "36231121","unidade2@vovofrancisca.com", i1, true);
		Unidade u3 = new Unidade("Unidade 1", e1, "36250010","unidade1@vovoataide.com", i2, true);
		
		//Salva Instituição e Unidade
		instituicaoRepository.saveAll(Arrays.asList(i1, i2));
		unidadeRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		AnoLetivo al1 = new AnoLetivo("2020", date, parsedDate.plusYears(1));
		
		anoLetivoRepository.saveAll(Arrays.asList(al1));
		
		Turma t1 = new Turma("Infantil I", u1, "Integral", "101", al1, true);
		Turma t2 = new Turma("Infantil II", u1, "Integral", "101", al1, true);
		Turma t3 = new Turma("Infantil III", u1, "Integral", "101", al1, true);
		
		//Salva Turmas 
		turmaRepository.saveAll(Arrays.asList(t1,t2));
		t3 = turmaRepository.save(t3);
		
		Atividade a1 = new Atividade("Trabalhando a coordenação motora", LocalDate.now(), t3);
		Atividade a2 = new Atividade("Abracadabra zzz", LocalDate.now().plusMonths(1), t2);
	
		//Salva atividades
		atividadeRepository.saveAll(Arrays.asList(a1,a2));
		
		Profissional p1 = new Profissional("Claudia Santos", "Cuidadora", "(95) 99110-1010", "claudiasantos@bemtevi.com", "123", true, Perfil.PROFISSIONAL);
		Profissional p2 = new Profissional("Yenniver Muniz", "Cuidadora", "(95) 99000-2020", "yennivermuniz@bemtevi.com", "100", true, Perfil.PROFISSIONAL);
		
		Lotacao lot1 = new Lotacao(p1, t1, LocalDate.now(), LocalDate.now().plusYears(1));
		Lotacao lot2 = new Lotacao(p1, t2, LocalDate.now(), LocalDate.now().plusYears(1));
		Lotacao lot3 = new Lotacao(p2, t3, LocalDate.now(), LocalDate.now().plusYears(1));
		Lotacao lot4 = new Lotacao(p1, t3, LocalDate.now(), LocalDate.now().plusYears(1));
		
		Aluno alu1 = new Aluno("Paulo da Costa Luz", "12/12/2019", true);
		Aluno alu2 = new Aluno("Isis da Conceição", "12/12/2019", true);
		
		alu1.getPessoalAutorizado().addAll(Arrays.asList("Francisco Elineuton", "Thiago Ventura"));
		
		//Salva Profissional e Aluno
		profissionalRepository.saveAll(Arrays.asList(p1, p2));
		alunoRepository.saveAll(Arrays.asList(alu1, alu2));
		
		//Salva Lotações
		lotacaoRepository.saveAll(Arrays.asList(lot1, lot2, lot3, lot4));
		
		//Um aluno não pode estar matriculado em mais de uma turma no mesmo ano
		Matricula mat1 = new Matricula(alu1, t1, LocalDate.of(2010, 10, 01));
		Matricula mat2 = new Matricula(alu2, t2, LocalDate.of(2010, 10, 01));
		Matricula mat3 = new Matricula(alu2, t3, LocalDate.of(2011, 10, 01));
		
		//Salva Matrículas
		matriculaRepository.saveAll(Arrays.asList(mat1, mat2, mat3));
		
		Questao q1 = new Questao("Café da Manhã");
		Questao q2 = new Questao("Lanche da Manhã");
		
		Resposta r1 = new Resposta(q1, "Pouco");
		Resposta r2 = new Resposta(q2, "Não aceitou");
		
		Avaliacao av1 = new Avaliacao(alu1, p1, parsedDate, "Suspensa");
		Avaliacao av2 = new Avaliacao(alu2, p1, parsedDate, "Finalizada");
		Avaliacao av3 = new Avaliacao(alu2, p2, parsedDate, "Finalizada");
		Avaliacao av4 = new Avaliacao(alu2, p2, parsedDate, "Finalizada");
		
		av1.getRespostas().addAll(Arrays.asList(r1, r2));

		//Salva Questão, Resposta e Avaliação	
		questaoRepository.saveAll(Arrays.asList(q1, q2));
		respostaRepository.saveAll(Arrays.asList(r1, r2));
		avaliacaoRepository.saveAll(Arrays.asList(av1, av2, av3, av4));
		
	}
}
