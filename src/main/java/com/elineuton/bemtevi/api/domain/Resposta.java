/*
 * package com.elineuton.bemtevi.api.domain;
 * 
 * import java.io.Serializable;
 * 
 * import javax.persistence.Entity; import javax.persistence.ForeignKey; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.OneToOne;
 * 
 * import lombok.EqualsAndHashCode; import lombok.Getter; import
 * lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Entity
 * 
 * @NoArgsConstructor
 * 
 * @EqualsAndHashCode(of = "id") public class Resposta implements Serializable {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * 
 * @Getter private Integer id;
 * 
 * @OneToOne
 * 
 * @JoinColumn(foreignKey = @ForeignKey(name = "fk_questao_id"))
 * 
 * @Getter @Setter private Questao questao;
 * 
 * @Getter @Setter private String resposta;
 * 
 * public Resposta(Questao questao, String resposta) { this.questao = questao;
 * this.resposta = resposta; }
 * 
 * }
 */