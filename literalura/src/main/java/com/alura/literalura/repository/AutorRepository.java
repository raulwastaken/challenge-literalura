package com.alura.literalura.repository;

import com.alura.literalura.Model.Autor;
import com.alura.literalura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,String> {
    Optional<Autor> findByNombre(String nombre);
}
