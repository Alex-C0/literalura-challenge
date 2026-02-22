package com.literalurachallenge.literalurachallenge.model;


import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private int numeroDescargas;



    public Libro(){}

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public Autor getAutor() {
        return autor;
    }
    public String getIdioma() {
        return idioma;
    }


    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public Libro(String titulo, Autor autor, String idioma, int numeroDescargas){
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDescargas = numeroDescargas;




    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" +(autor != null ? autor.getNombre() : "N/A")+
                ", idioma='" + idioma + '\'' +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }
}
