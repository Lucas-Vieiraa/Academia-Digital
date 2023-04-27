package me.dio.academia.academiadigital.repository;

import me.dio.academia.academiadigital.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel,Long> {
}
