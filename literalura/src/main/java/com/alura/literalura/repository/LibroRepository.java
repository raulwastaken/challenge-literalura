package com.alura.literalura.repository;

import com.alura.literalura.Model.Autor;
import com.alura.literalura.Model.Idiomas;
import com.alura.literalura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,String> {

    List<Libro> findByIdiomas(Idiomas idiomas);

    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autores a")
    List<Autor> todosLosAutores();

}
