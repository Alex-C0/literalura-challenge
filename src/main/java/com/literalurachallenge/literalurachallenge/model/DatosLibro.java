package com.literalurachallenge.literalurachallenge.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {

    @JsonAlias("id")
    private Long id;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer descargas;

    @JsonAlias("authors")
    private List<DatosAutor> autores;

    public List<DatosAutor> getAutores() {
        return autores;
    }

    public DatosLibro() {}

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    @Override
    public String toString() {
        return "Libro " +

                ", titulo='" + titulo + "\n" +
                ", idiomas=" + idiomas +
                ", descargas=" + descargas +
                ", autores=" + autores;
    }
}
