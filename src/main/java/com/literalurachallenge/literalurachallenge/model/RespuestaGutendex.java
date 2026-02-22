package com.literalurachallenge.literalurachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaGutendex {
    @JsonAlias("count")
    private Integer total;

    @JsonAlias("results")
    private List<DatosLibro> libros;

    public RespuestaGutendex() {}

    public Integer getTotal() {
        return total;
    }

    public List<DatosLibro> getLibros() {
        return libros;
    }
}
