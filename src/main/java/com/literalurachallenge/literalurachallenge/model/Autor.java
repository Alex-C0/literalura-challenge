package com.literalurachallenge.literalurachallenge.model;

import com.literalurachallenge.literalurachallenge.repository.LibroRepository;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int anoDeNacimiento;
    private int anoDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros;

    public Autor(String nombre, int anoDeNacimiento, int anoDeFallecimiento) {
        this.nombre = nombre;
        this.anoDeNacimiento = anoDeNacimiento;
        this.anoDeFallecimiento = anoDeFallecimiento;



    }

    public Autor(){}

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
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", anoDeNacimiento=" + anoDeNacimiento +
                ", anoDeFallecimiento=" + anoDeFallecimiento +
                '}';
    }
}
