package com.alura.literalura.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private Integer numeroDeDescargas;

    public List<String> getListaDeAutores(){
        List<String> autores = getAutores().stream()
                .map(a-> a.getNombre())
                .collect(Collectors.toList());
        return autores;
    }

    public Libro(DatosLibro datosLibro, Set<Autor> autorSet) {
        this.titulo = datosLibro.titulo();
        //this.autores = datosLibro.autores().stream().map(a-> new Autor(a)).collect(Collectors.toList());
        this.autores = autorSet;
        this.idiomas = Idiomas.fromString(datosLibro.idiomas().getFirst());
        this.numeroDeDescargas = datosLibro.descargas();
    }

    public Libro(){};
    @Override
    public String toString() {
        return "----- LIBRO -----" + '\n' +
                "Titulo: " + getTitulo() + '\n' +
                "Autor: " + getListaDeAutores() + '\n' +
                "Idioma: " + getIdiomas() + '\n' +
                "Numero de descargas: " + getNumeroDeDescargas() + '\n' ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
