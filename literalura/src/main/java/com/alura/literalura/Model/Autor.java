package com.alura.literalura.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Integer anoDeNacimiento;
    private Integer anoDeFallecimiento;
    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){};
    public List<String> getListaDeLibros(){
        List<String> titulos = getLibros().stream()
                .map(l -> l.getTitulo())
                .collect(Collectors.toList());
        return titulos;
    }

    @Override
    public String toString() {
        return "Autor: " + getNombre() + '\n' +
                "Fecha de nacimiento: " + getAnoDeNacimiento() + '\n' +
                "Fecha de fallecimiento: " + getAnoDeFallecimiento() + '\n' +
                "Libros: " + getListaDeLibros() + '\n' ;
    }

    public Autor(DatosAutores datosAutores) {
        this.nombre = datosAutores.nombre();
        this.anoDeNacimiento = datosAutores.anoDeNacimiento();
        this.anoDeFallecimiento = datosAutores.anoDeFallecimiento();
        //this.libros = libros;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoDeNacimiento() {
        return anoDeNacimiento;
    }

    public void setAnoDeNacimiento(Integer anoDeNacimiento) {
        this.anoDeNacimiento = anoDeNacimiento;
    }

    public Integer getAnoDeFallecimiento() {
        return anoDeFallecimiento;
    }

    public void setAnoDeFallecimiento(Integer anoDeFallecimiento) {
        this.anoDeFallecimiento = anoDeFallecimiento;
    }
}
