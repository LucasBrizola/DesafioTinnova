package com.DesafioTinnova.DesafioTinnova.config;

import com.DesafioTinnova.DesafioTinnova.model.Marca;
import com.DesafioTinnova.DesafioTinnova.repository.MarcaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    CommandLineRunner initDatabase(MarcaRepository marcaRepository) {
        return args -> {
            String url = "https://private-anon-341ca770b0-carsapi1.apiary-mock.com/manufacturers";
            ResponseEntity<Object[]> responseEntity
            //List<Marca> marcas
                    = restTemplate.getForEntity(url, Object[].class);
            Object[] objects = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<Marca> marcas = Arrays.stream(objects)
                    .map(object -> mapper.convertValue(object, Marca.class))
                    .collect(Collectors.toList());
            for (Marca marca : marcas
            ) {
                marcaRepository.save(marca);
            }
        };
    }

    @Bean
    public static RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}