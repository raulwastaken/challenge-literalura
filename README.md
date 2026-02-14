# Literalura 📚

Proyecto en Java Spring Boot que consume la API Gutendex y guarda libros y autores en PostgreSQL.

## Caracteristicas:
La App permite guardar datos de libros junto con datos de su autor, considerando..
-Relación ManyToMany entre libros y autores, dado que un libro podría ser colaboración entre varios autores. Y un autor puede haber escrito varios libros.
-La App no permite guardar libros ni autores duplicados.
 
## Tecnologías
- Java 25
- Spring Boot
- JPA
- PostgreSQL

## Cómo ejecutar
1. Crear base de datos en PostgreSQL
2. Configurar application.properties
3. Ejecutar:

mvn spring-boot:run
