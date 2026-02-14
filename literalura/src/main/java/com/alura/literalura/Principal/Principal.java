package com.alura.literalura.Principal;

import com.alura.literalura.Model.*;
import com.alura.literalura.Service.ConsumoAPI;
import com.alura.literalura.Service.ConvierteDatos;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    LibroRepository repositorio;
    AutorRepository autorRepository;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarTodosLosLibros();
                    break;
                case 3:
                    listarTodosLosAutores();
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
    private Autor obtenerOCrearAutor(DatosAutores datosAutores) {
        return autorRepository.findByNombre(datosAutores.nombre())
                .orElseGet(() -> autorRepository.save(new Autor(datosAutores)));
    }

    private Libro guardarLibro(DatosLibro datosLibro){

        Set<Autor> autorSet = datosLibro.autores().stream()
                .map(a -> obtenerOCrearAutor(a))
                .collect(Collectors.toSet());
        Libro libro = new Libro(datosLibro,autorSet);
        repositorio.save(libro);
        return libro;
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingresa el idioma por el cual quieres buscar libros: " + '\n' +
                "es - Español" + '\n' +
                "en - Ingles" + '\n' +
                "fr - Frances" + '\n' +
                "pt - Portugues");
        String idiomaSeleccionado = teclado.nextLine();
        List<Libro> libroList = repositorio.findByIdiomas(Idiomas.fromString(idiomaSeleccionado));
        libroList.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingresa el año en el que quieres buscar autores vivos");
        Integer ano = teclado.nextInt();
        List<Autor> autorList = repositorio.todosLosAutores();
        List<Autor> autoresVivos = autorList.stream()
                .filter(a-> a.getAnoDeNacimiento() <= ano && a.getAnoDeFallecimiento()>= ano)
                .collect(Collectors.toUnmodifiableList());
        autoresVivos.forEach(System.out::println);
    }

    private void listarTodosLosAutores() {
        List<Autor> autorList = repositorio.todosLosAutores();
        autorList.forEach(System.out::println);
    }

    private void listarTodosLosLibros() {
        List<Libro> todosLosLibros = repositorio.findAll();
        todosLosLibros.forEach(System.out::println);
    }

    private Optional<DatosLibro> getDatosLibro(){
        System.out.println("Ingresa el nombre del libro que deseas buscar");
        String nombreLibro = teclado.nextLine();
        String url = "https://gutendex.com/books/?search=" + nombreLibro.toLowerCase().replace(" ","%20");
        String json = consumoAPI.obtenerDatos(url);
        DatosCount datos = convierteDatos.obtenerDatos(json, DatosCount.class);
        if (datos.libros().isEmpty()) {
            return Optional.empty();
        }
        //DatosLibro datosLibro = datos.libros().getFirst();
        return Optional.of(datos.libros().getFirst());

    }

    private void buscarLibrosPorTitulo() {
        //DatosLibro datosLibro = getDatosLibro();
        Optional<DatosLibro> optionalDatosLibro = getDatosLibro();
//        Libro libro = new Libro(datosLibro);
//        repositorio.save(libro);
//        System.out.println(libro);
        if (optionalDatosLibro.isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }
        Libro libro =  guardarLibro(optionalDatosLibro.get());
        System.out.println(libro);
    }
}