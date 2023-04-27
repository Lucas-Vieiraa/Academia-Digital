package me.dio.academia.academiadigital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_avaliacoes_fisicas")
public class AvaliacaoFisicaModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliacao;
    private LocalDateTime dataDaAvaliacao = LocalDateTime.now();
    private Double pesoAvaliacao;
    private Double alturaAvaliacao;
    @JoinColumn(name = "fk_aluno")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private AlunoModel alunoModel;
}
