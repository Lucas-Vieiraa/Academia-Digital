package me.dio.academia.academiadigital.service;

import me.dio.academia.academiadigital.dto.requestDto.AlunoRequestDto;
import me.dio.academia.academiadigital.dto.responseDto.AlunoResponseDto;
import me.dio.academia.academiadigital.model.AlunoModel;
import me.dio.academia.academiadigital.repository.AlunoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private AlunoRepository alunoRepository;
    private ModelMapper modelMapper;
    @Autowired
    public AlunoService(AlunoRepository alunoRepository, ModelMapper modelMapper) {
        this.alunoRepository = alunoRepository;
        this.modelMapper = modelMapper;
    }
    /*
     * Anotação transactional garante que a operação de serviço feche corretamente a
     * transação quando ela executar, (obs:readOnly informa que é apenas uma função
     * de leitura e deixa a transação mais rápida)
     */

    @Transactional(readOnly = true)
    public List<AlunoResponseDto> findAll(){
        List<AlunoModel> alunoModel = alunoRepository.findAll();
        return alunoModel.stream()
                .map(aluno -> modelMapper.map(aluno,AlunoResponseDto.class))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public AlunoResponseDto findById(Long idAluno) {
        AlunoModel alunoModel = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe o aluno com id : " + idAluno));
        return modelMapper.map(alunoModel, AlunoResponseDto.class);
    }
    @Transactional
    public void deleteById(Long idAluno) {
        AlunoModel alunoModel = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe o aluno com id : " + idAluno));
        alunoRepository.delete(alunoModel);
    }
    @Transactional
    public AlunoResponseDto updateById(Long idAluno, AlunoRequestDto alunoRequestDto) {
        if (alunoRepository.existsById(idAluno)){
            AlunoModel alunoModel = modelMapper.map(alunoRequestDto,AlunoModel.class);
            alunoModel.setIdAluno(idAluno);
            return modelMapper.map(alunoRepository.save(alunoModel), AlunoResponseDto.class);
        }
        throw new ResourceNotFoundException("Não existe aluno com id " + idAluno);
    }
    @Transactional
    public AlunoResponseDto save(AlunoRequestDto alunoRequestDto){
        AlunoModel alunoModel = alunoRepository.save(modelMapper.map(alunoRequestDto,AlunoModel.class));
        return modelMapper.map(alunoModel, AlunoResponseDto.class);
    }
}
