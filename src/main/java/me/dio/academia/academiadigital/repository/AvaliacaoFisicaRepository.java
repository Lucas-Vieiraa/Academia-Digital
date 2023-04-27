package me.dio.academia.academiadigital.repository;

import me.dio.academia.academiadigital.model.AvaliacaoFisicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisicaModel,Long> {
}
