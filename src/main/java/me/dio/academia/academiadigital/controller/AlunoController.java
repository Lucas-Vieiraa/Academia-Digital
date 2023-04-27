package me.dio.academia.academiadigital.controller;

import me.dio.academia.academiadigital.dto.requestDto.AlunoRequestDto;
import me.dio.academia.academiadigital.dto.responseDto.AlunoResponseDto;
import me.dio.academia.academiadigital.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/aluno/all")
    public ResponseEntity<List<AlunoResponseDto>> getAllAluno(){
        List<AlunoResponseDto> alunoResponseDto = alunoService.findAll();
        return ResponseEntity.ok().body(alunoResponseDto);
    }
    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<AlunoResponseDto> getAlunoById(@PathVariable Long idAluno){
        AlunoResponseDto alunoResponseDto = alunoService.findById(idAluno);
        return ResponseEntity.ok().body(alunoResponseDto);
    }
    @DeleteMapping ("/aluno/{idAluno}")
    public ResponseEntity<Void> DeleteAlunoById(@PathVariable Long idAluno){
        alunoService.deleteById(idAluno);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/aluno")
    public ResponseEntity<AlunoResponseDto> saveAluno(@RequestBody AlunoRequestDto alunoRequestDto){
        AlunoResponseDto alunoResponseDto = alunoService.save(alunoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoResponseDto);
    }
    @PutMapping("/aluno/{idAluno}")
    public ResponseEntity<AlunoResponseDto> updateAlunoById(@PathVariable Long idAluno,@RequestBody AlunoRequestDto alunoRequestDto ){
            AlunoResponseDto alunoResponseDto = alunoService.updateById(idAluno,alunoRequestDto);
            return ResponseEntity.ok().body(alunoResponseDto);
    }
}
