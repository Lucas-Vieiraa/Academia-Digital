package me.dio.academia.academiadigital.controller;

import me.dio.academia.academiadigital.dto.requestDto.AvaliacaoFisicaRequestDto;
import me.dio.academia.academiadigital.dto.responseDto.AvaliacaoFisicaResponseDto;
import me.dio.academia.academiadigital.service.AvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AvaliacaoFisicaController {
    @Autowired
    private AvaliacaoFisicaService avaliacaoFisicaService;

    @GetMapping("/avaliacao/all")
    public ResponseEntity<List<AvaliacaoFisicaResponseDto>> getAllAvaliacao(){
        List<AvaliacaoFisicaResponseDto> avaliacaoFisicaResponseDto = avaliacaoFisicaService.findAll();
        return ResponseEntity.ok().body(avaliacaoFisicaResponseDto);
    }
    @GetMapping("/avaliacao/{idAvaliacao}")
    public ResponseEntity<AvaliacaoFisicaResponseDto> getAlunoById(@PathVariable Long idAvaliacao){
      AvaliacaoFisicaResponseDto avaliacaoFisicaResponseDto = avaliacaoFisicaService.findById(idAvaliacao);
        return ResponseEntity.ok().body(avaliacaoFisicaResponseDto);
    }
    @DeleteMapping("/avaliacao/{idAvaliacao}")
    public ResponseEntity<Void> DeleteAlunoById(@PathVariable Long idAvaliacao){
        avaliacaoFisicaService.deleteById(idAvaliacao);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/avaliacao")
    public ResponseEntity<AvaliacaoFisicaResponseDto> saveAluno(@RequestBody AvaliacaoFisicaRequestDto avaliacaoFisicaRequestDto){
        AvaliacaoFisicaResponseDto avaliacaoFisicaResponseDto = avaliacaoFisicaService.save(avaliacaoFisicaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoFisicaResponseDto);
    }
    @PutMapping("/avaliacao/{idAvaliacao}")
    public ResponseEntity<AvaliacaoFisicaResponseDto> updateAlunoById(@PathVariable Long idAvaliacao, @RequestBody AvaliacaoFisicaRequestDto avaliacaoFisicaRequestDto){
        AvaliacaoFisicaResponseDto avaliacaoFisicaResponseDto = avaliacaoFisicaService.updateById(idAvaliacao, avaliacaoFisicaRequestDto);
        return ResponseEntity.ok().body(avaliacaoFisicaResponseDto);
    }
}
