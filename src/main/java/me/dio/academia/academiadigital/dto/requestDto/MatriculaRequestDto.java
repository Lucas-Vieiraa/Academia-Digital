package me.dio.academia.academiadigital.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idAluno;


}
