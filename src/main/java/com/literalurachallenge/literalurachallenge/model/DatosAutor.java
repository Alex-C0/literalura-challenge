package com.literalurachallenge.literalurachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosAutor {

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private int anoDeNacimiento;

    @JsonAlias("death_year")
    private int anoDeFallecimiento;

    public DatosAutor() {}

    public String getNombre() {
        return nombre;
    }

    public int getAnoDeNacimiento() {
        return anoDeNacimiento;
    }

    public int getAnoDeFallecimiento() {
        return anoDeFallecimiento;
    }

    @Override
    public String toString() {
        return "DatosAutor{" +
                "nombre='" + nombre + '\'' +
                ", anoDeNacimiento=" + anoDeNacimiento +
                ", anoDeFallecimiento=" + anoDeFallecimiento +
                '}';
    }
}
