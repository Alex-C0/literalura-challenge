package com.literalurachallenge.literalurachallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalurachallenge.literalurachallenge.model.RespuestaGutendex;
import com.literalurachallenge.literalurachallenge.principal.Menu;
import com.literalurachallenge.literalurachallenge.repository.AutorRepository;
import com.literalurachallenge.literalurachallenge.repository.LibroRepository;
import com.literalurachallenge.literalurachallenge.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.Principal;

@SpringBootApplication
public class LiteralurachallengeApplication implements CommandLineRunner {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LiteralurachallengeApplication(LibroRepository libroRepository,
                                          AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }



    public static void main(String[] args) {
        SpringApplication.run(LiteralurachallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Menu menu = new Menu(
                new ConsumoApi(),
                libroRepository,
                autorRepository
        );

        menu.mostrarMenu();
    }

}
