package me.dio.academia.academiadigital.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequestDto  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cpfAluno;
    private String nomeAluno;
    private String bairroAluno;
    private LocalDate dataDeNascimentoAluno;
}
