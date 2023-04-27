package me.dio.academia.academiadigital.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double pesoAvaliacao;
    private Double alturaAvaliacao;
    private Long idAluno;
}
