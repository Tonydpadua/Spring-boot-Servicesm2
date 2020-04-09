package com.tonydpadua;

import com.tonydpadua.categoria.Categoria;
import com.tonydpadua.categoria.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;

@AllArgsConstructor
@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria c1 = new Categoria(null,"Informática");
        Categoria c2 = new Categoria(null,"Escritório");

        categoriaRepository.saveAll(Arrays.asList(c1,c2));
    }
}
