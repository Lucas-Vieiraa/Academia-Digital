package me.dio.academia.academiadigital.controller;

import me.dio.academia.academiadigital.dto.requestDto.MatriculaRequestDto;
import me.dio.academia.academiadigital.dto.responseDto.MatriculaResponseDto;
import me.dio.academia.academiadigital.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;


    @GetMapping("/matricula/all")
    public ResponseEntity<List<MatriculaResponseDto>> getAllMatricula(){
        List<MatriculaResponseDto> matriculaResponseDto = matriculaService.findAll();
        return ResponseEntity.ok().body(matriculaResponseDto);
    }
    @GetMapping("/matricula/{idMatricula}")
    public ResponseEntity<MatriculaResponseDto> getAlunoById(@PathVariable Long idMatricula){
        MatriculaResponseDto matriculaResponseDto = matriculaService.findById(idMatricula);
        return ResponseEntity.ok().body(matriculaResponseDto);
    }
    @DeleteMapping("/matricula/{idMatricula}")
    public ResponseEntity<Void> DeleteAlunoById(@PathVariable Long idMatricula){
        matriculaService.deleteById(idMatricula);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/matricula")
    public ResponseEntity<MatriculaResponseDto> saveAluno(@RequestBody MatriculaRequestDto matriculaRequestDto){
        MatriculaResponseDto matriculaResponseDto = matriculaService.save(matriculaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaResponseDto);
    }
    @PutMapping("/matricula/{idMatricula}")
    public ResponseEntity<MatriculaResponseDto> updateAlunoById(@PathVariable Long idMatricula,@RequestBody MatriculaRequestDto matriculaRequestDto){
        MatriculaResponseDto  matriculaResponseDto = matriculaService.updateById(idMatricula, matriculaRequestDto);
        return ResponseEntity.ok().body(matriculaResponseDto);
    }
}
