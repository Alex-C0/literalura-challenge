package com.literalurachallenge.literalurachallenge.repository;

import com.literalurachallenge.literalurachallenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
    List<Autor> findByAnoDeNacimientoLessThanEqualAndAnoDeFallecimientoIsNullOrAnoDeFallecimientoGreaterThanEqual(int ano1, int ano2);
    List<Autor> findAll();
}
