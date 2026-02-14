package com.alura.literalura;

import com.alura.literalura.Principal.Principal;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
    @Autowired(required = true)

//    private LibroRepository repository;
//    private AutorRepository autorRepository;
//
    private final Principal principal;

    public LiteraluraApplication(Principal principal){
        this.principal = principal;
    }
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        Principal principal = new Principal(repository, autorRepository);
        principal.muestraElMenu();
    }
}
