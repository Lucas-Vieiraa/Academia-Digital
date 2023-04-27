package me.dio.academia.academiadigital.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.academia.academiadigital.model.AlunoModel;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime dataDaAvaliacao;
    private Double pesoAvaliacao;
    private Double alturaAvaliacao;
    private AlunoModel alunoModel;
}
