package me.dio.academia.academiadigital.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeAluno;
    private String bairroAluno;
    private LocalDate dataDeNascimentoAluno;

}
