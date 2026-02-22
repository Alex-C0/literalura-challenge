package com.literalurachallenge.literalurachallenge.principal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalurachallenge.literalurachallenge.model.*;
import com.literalurachallenge.literalurachallenge.repository.AutorRepository;
import com.literalurachallenge.literalurachallenge.repository.LibroRepository;
import com.literalurachallenge.literalurachallenge.service.ConsumoApi;
import com.literalurachallenge.literalurachallenge.service.ConvierteDatos;
import com.literalurachallenge.literalurachallenge.service.IConvierteDatos;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;


public class Menu {

    private Scanner scanner = new Scanner(System.in);

    private final String URL_BASE = "https://gutendex.com/books/";
    private final ConsumoApi consumoApi;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final IConvierteDatos conversor = new ConvierteDatos();

    public Menu(ConsumoApi consumoApi,
                LibroRepository libroRepository,
                AutorRepository autorRepository) {
        this.consumoApi = consumoApi;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }



    public void mostrarMenu(){

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija una opción:
                    
                    1- Buscar libro por Título
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado ano
                    5- Listar libros por idioma
                    0- Salir
                    
                 
                    
                    """;
            System.out.println(menu);
            System.out.println("Opción: ");

            String entrada = scanner.nextLine();

            scanner.nextLine();

            try{
                opcion = Integer.parseInt(entrada);

            }catch (NumberFormatException e){
                System.out.println("Entrada inválida. Debe ingresar un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;

                    case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }



    }




    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el título del libro a buscar:");
        String tituloBuscado = scanner.nextLine();

        try {

            String url = URL_BASE + "?search=" +
                    URLEncoder.encode(tituloBuscado, StandardCharsets.UTF_8);


            String json = consumoApi.obtenerDatos(url);


            RespuestaGutendex respuesta = conversor.obtenerDatos(json, RespuestaGutendex.class);

            if (respuesta.getLibros().isEmpty()) {
                System.out.println("No se encontraron libros con ese título.");
                return;
            }


            DatosLibro datos = respuesta.getLibros().get(0);

            var libroExistente =
                    libroRepository.findByTituloIgnoreCase(datos.getTitulo());

            if (libroExistente.isPresent()) {
                System.out.println("⚠️ El libro ya existe en la base de datos:");
                System.out.println(libroExistente.get());
                return;
            }


            Autor autorGuardado;
            if (datos.getAutores() != null && !datos.getAutores().isEmpty()) {
                DatosAutor datosAutor = datos.getAutores().get(0);

                autorGuardado = autorRepository.findByNombre(datosAutor.getNombre())
                        .orElseGet(() -> autorRepository.save(
                            new Autor(
                                    datosAutor.getNombre(),
                                    datosAutor.getAnoDeNacimiento(),
                                    datosAutor.getAnoDeFallecimiento()
                            )
                        ));


            } else {

                autorGuardado = autorRepository.save(
                        new Autor("Desconocido", 0, 0)
                );
            }


            Libro libro = new Libro(
                    datos.getTitulo(),
                    autorGuardado,
                    (datos.getIdiomas() != null && !datos.getIdiomas().isEmpty()) ? datos.getIdiomas().get(0) : "Desconocido",
                    datos.getDescargas() != null ? datos.getDescargas() : 0
            );
            libroRepository.save(libro);


            System.out.println("Libro guardado:");
            System.out.println(libro);

        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar el libro: " + e.getMessage());

        }
    }

    private void listarLibrosRegistrados() {
        System.out.println("=== Listado de libros registrados ===");

        var libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        for (Libro libro : libros) {
            System.out.println(libro);
        }


    }

    private void listarAutoresRegistrados() {
        System.out.println("=== Listado de autores registrados ===");

        var autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados aún.");
            return;
        }

        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año a consultar:");
        int ano = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autoresVivos = autorRepository
                .findByAnoDeNacimientoLessThanEqualAndAnoDeFallecimientoIsNullOrAnoDeFallecimientoGreaterThanEqual(ano, ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + ano);
            return;
        }

        System.out.println("Autores vivos en el año " + ano + ":");
        autoresVivos.forEach(a -> System.out.println(a));
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma a consultar (por ejemplo: en, fr, es):");
        String idioma = scanner.nextLine();

        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idioma);
            return;
        }

        System.out.println("Libros en idioma " + idioma + ":");
        librosPorIdioma.forEach(libro -> System.out.println(libro));
    }






}
