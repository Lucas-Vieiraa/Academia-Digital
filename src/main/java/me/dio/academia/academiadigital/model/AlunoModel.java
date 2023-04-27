package me.dio.academia.academiadigital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_alunos")
public class AlunoModel  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;
    private String nomeAluno;
    private String bairroAluno;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate dataDeNascimentoAluno;
    @Column(unique = true)
    private String cpfAluno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alunoModel", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AvaliacaoFisicaModel> avaliacoes = new ArrayList<>();

}
