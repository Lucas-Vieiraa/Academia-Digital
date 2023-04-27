package me.dio.academia.academiadigital.service;

import me.dio.academia.academiadigital.dto.requestDto.AvaliacaoFisicaRequestDto;
import me.dio.academia.academiadigital.dto.responseDto.AvaliacaoFisicaResponseDto;
import me.dio.academia.academiadigital.model.AlunoModel;
import me.dio.academia.academiadigital.model.AvaliacaoFisicaModel;
import me.dio.academia.academiadigital.repository.AlunoRepository;
import me.dio.academia.academiadigital.repository.AvaliacaoFisicaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoFisicaService {
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;
    private AlunoRepository alunoRepository;
    private ModelMapper modelMapper;
    @Autowired
    public AvaliacaoFisicaService(AvaliacaoFisicaRepository avaliacaoFisicaRepository, AlunoRepository alunoRepository, ModelMapper modelMapper) {
        this.avaliacaoFisicaRepository = avaliacaoFisicaRepository;
        this.alunoRepository = alunoRepository;
        this.modelMapper = modelMapper;
    }
    /*
     * Anotação transactional garante que a operação de serviço feche corretamente a
     * transação quando ela executar, (obs:readOnly informa que é apenas uma função
     * de leitura e deixa a transação mais rápida)
     */

    @Transactional(readOnly = true)
    public List<AvaliacaoFisicaResponseDto> findAll(){
        List<AvaliacaoFisicaModel> avaliacaoFisicaModel = avaliacaoFisicaRepository.findAll();
        return avaliacaoFisicaModel.stream()
                .map(avaliacaoFisica -> modelMapper.map(avaliacaoFisica,AvaliacaoFisicaResponseDto.class))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public AvaliacaoFisicaResponseDto findById(Long idAvaliacao) {
        AvaliacaoFisicaModel avaliacaoFisicaModel = avaliacaoFisicaRepository.findById(idAvaliacao)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe o avaliação com id : " + idAvaliacao));
        return modelMapper.map(avaliacaoFisicaModel, AvaliacaoFisicaResponseDto.class);
    }
    @Transactional
    public void deleteById(Long idAvaliacao) {
        AvaliacaoFisicaModel avaliacaoFisicaModel = avaliacaoFisicaRepository.findById(idAvaliacao)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe a avaliação com id : " + idAvaliacao));
        avaliacaoFisicaRepository.delete(avaliacaoFisicaModel);
    }
    @Transactional
    public AvaliacaoFisicaResponseDto updateById(Long idAvaliacao, AvaliacaoFisicaRequestDto avaliacaoFisicaRequestDto) {
        if (avaliacaoFisicaRepository.existsById(idAvaliacao)){
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            AlunoModel alunoModel = alunoRepository.findById(avaliacaoFisicaRequestDto.getIdAluno())
                    .orElseThrow(() -> new ResourceNotFoundException("Não existe aluno com id " + avaliacaoFisicaRequestDto.getIdAluno()));
            AvaliacaoFisicaModel avaliacaoFisicaModel = modelMapper.map(avaliacaoFisicaRequestDto,AvaliacaoFisicaModel.class);
            avaliacaoFisicaModel.setAlunoModel(alunoModel);
            return modelMapper.map(avaliacaoFisicaRepository.save(avaliacaoFisicaModel), AvaliacaoFisicaResponseDto.class);
        }
        throw new ResourceNotFoundException("Não existe avaliação com id " + idAvaliacao);
    }
    @Transactional
    public AvaliacaoFisicaResponseDto save(AvaliacaoFisicaRequestDto avaliacaoFisicaRequestDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AlunoModel alunoModel = alunoRepository.findById(avaliacaoFisicaRequestDto.getIdAluno())
                .orElseThrow(() -> new ResourceNotFoundException("Não existe aluno com id " + avaliacaoFisicaRequestDto.getIdAluno()));
        AvaliacaoFisicaModel avaliacaoFisicaModel = modelMapper.map(avaliacaoFisicaRequestDto,AvaliacaoFisicaModel.class);
        avaliacaoFisicaModel.setAlunoModel(alunoModel);
        return modelMapper.map(avaliacaoFisicaRepository.save(avaliacaoFisicaModel), AvaliacaoFisicaResponseDto.class);
    }
}
