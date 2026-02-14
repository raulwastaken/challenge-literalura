package com.alura.literalura.Model;

import jakarta.persistence.Id;

public enum Idiomas {
    ESPAÑOL("es"),
    INGLES("en"),
    FARNCES("fr"),
    PORTUGUES("pt");


    private String abreviacion;

    Idiomas(String abreviacion){
        this.abreviacion =abreviacion;
    }

    public static Idiomas fromString(String text){
        for(Idiomas idioma : Idiomas.values()){
            if (idioma.abreviacion.equalsIgnoreCase(text)){
                return idioma;
            }
        } throw new IllegalArgumentException("Ninguna idioma encontrado: " + text);

    }
}

