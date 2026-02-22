package com.literalurachallenge.literalurachallenge.repository;

import com.literalurachallenge.literalurachallenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByIdioma(String idioma);

    Libro findFirstByTitulo(String titulo);
}
