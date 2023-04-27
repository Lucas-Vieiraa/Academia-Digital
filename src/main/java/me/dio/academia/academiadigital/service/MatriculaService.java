package me.dio.academia.academiadigital.service;

import me.dio.academia.academiadigital.dto.requestDto.MatriculaRequestDto;
import me.dio.academia.academiadigital.dto.responseDto.MatriculaResponseDto;
import me.dio.academia.academiadigital.model.AlunoModel;
import me.dio.academia.academiadigital.model.MatriculaModel;
import me.dio.academia.academiadigital.repository.AlunoRepository;
import me.dio.academia.academiadigital.repository.MatriculaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService {
    private MatriculaRepository matriculaRepository;
    private AlunoRepository alunoRepository;
    private ModelMapper modelMapper;
    @Autowired
    public MatriculaService(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, ModelMapper modelMapper) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.modelMapper = modelMapper;
    }
    /*
     * Anotação transactional garante que a operação de serviço feche corretamente a
     * transação quando ela executar, (obs:readOnly informa que é apenas uma função
     * de leitura e deixa a transação mais rápida)
     */

    @Transactional(readOnly = true)
    public List<MatriculaResponseDto> findAll(){
        List<MatriculaModel> matriculaModel = matriculaRepository.findAll();
        return matriculaModel.stream()
                .map(matricula -> modelMapper.map(matricula,MatriculaResponseDto.class))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public MatriculaResponseDto findById(Long idMatricula) {
        MatriculaModel matriculaModel = matriculaRepository.findById(idMatricula)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe matricula com id : " + idMatricula));
        return modelMapper.map(matriculaModel, MatriculaResponseDto.class);
    }
    @Transactional
    public void deleteById(Long idMatricula) {
        MatriculaModel matriculaModel = matriculaRepository.findById(idMatricula)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe matricula com id : " + idMatricula));
        matriculaRepository.delete(matriculaModel);
    }
    @Transactional
    public MatriculaResponseDto updateById(Long idAvaliacao, MatriculaRequestDto matriculaRequestDto) {
        if (matriculaRepository.existsById(idAvaliacao)){
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            AlunoModel alunoModel = alunoRepository.findById(matriculaRequestDto.getIdAluno())
                    .orElseThrow(() -> new ResourceNotFoundException("Não existe aluno com id " + matriculaRequestDto.getIdAluno()));
            MatriculaModel matriculaModel = modelMapper.map(matriculaRequestDto,MatriculaModel.class);
            matriculaModel.setAlunoModel(alunoModel);
            return modelMapper.map(matriculaRepository.save(matriculaModel), MatriculaResponseDto.class);
        }
        throw new ResourceNotFoundException("Não existe Matricula com id : " + idAvaliacao);
    }
    @Transactional
    public MatriculaResponseDto save(MatriculaRequestDto matriculaRequestDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AlunoModel alunoModel = alunoRepository.findById(matriculaRequestDto.getIdAluno())
                .orElseThrow(() -> new ResourceNotFoundException("Não existe aluno com id : " + matriculaRequestDto.getIdAluno()));
        MatriculaModel matriculaModel = modelMapper.map(matriculaRequestDto,MatriculaModel.class);
        matriculaModel.setAlunoModel(alunoModel);
        return modelMapper.map(matriculaRepository.save(matriculaModel), MatriculaResponseDto.class);
    }
}
