package com.tutorialesvip.tutorialunittest.controller;

import java.util.Optional;

import com.tutorialesvip.tutorialunittest.models.Country;
import com.tutorialesvip.tutorialunittest.models.CountryResponse;
import com.tutorialesvip.tutorialunittest.repositories.CountryRepository;
import com.tutorialesvip.tutorialunittest.util.DiferenciaEntreFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
public class IndependencyControllerTest {
    //para hacer la prueba lo primero que necesitamos son los objetos
    @Autowired
    CountryResponse countryResponse;
    @Autowired
    Optional<Country> country;
    
    //En este necesitamos mockito, mock va a devolver un objeto falsa de este tipo:
    CountryRepository countryRepositoryMock = Mockito.mock(CountryRepository.class);
    
    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas = new DiferenciaEntreFechas();

    @Autowired   //nos falta este objteto que es el que vamos a probar
    IndependencyController independencyController = new IndependencyController(countryRepositoryMock, diferenciaEntreFechas);
    
    @BeforeEach  //significa antes de cada uno de los test
    void SetUp(){
        //System.out.println("Antes de la prueba"); verificar cuandoe ejecuta
        //Respuesta para el mockito
        Country mockCountry = new Country();
        mockCountry.setIsoCode("DO");
        mockCountry.setCountryIdependenceDate("27/02/1844");
        mockCountry.setCountryId((long) 1);
        mockCountry.setCountryName("Republica Dominicana");
        mockCountry.setCountryCapital("Santo Domingo");

        //Mockito cuando llames a este objeto con este metodo con este atributo(valor)
        //entonces que nos retorne los datos del mockCountry
        Mockito.when(countryRepositoryMock.findCountryByIsoCode("DO")).thenReturn(mockCountry);
    }

    @Test
    void getCountryDetailsTestValidCountryCode(){
        //System.out.println("Dentro de la prueba"); verificar cuandoe ejecuta
        //Variabla a la que se asignara la respuesta del objeto, tipo optional
        ResponseEntity<CountryResponse> respuestaServicio;

        //Hacemos la prueba del objeto que vamos a probar en el test
        respuestaServicio = independencyController.getCountryDetails("DO");

        //imprimir para verificar
        System.out.println(respuestaServicio);

        Assertions.assertEquals("Republica Dominicana", respuestaServicio.getBody().getCountryName());
    }
    @Test
    void getCountryDetailsTestInValidCountryCode(){
        //System.out.println("Dentro de la prueba"); verificar cuandoe ejecuta
        //Variabla a la que se asignara la respuesta del objeto, tipo optional
        ResponseEntity<CountryResponse> respuestaServicio;

        //Hacemos la prueba del objeto que vamos a probar en el test
        respuestaServicio = independencyController.getCountryDetails("IT");

        //imprimir para verificar
        System.out.println("Test Null OK");

        Assertions.assertNull(null, respuestaServicio.getBody().getCountryName());
    }

    /*
    @AfterEach   //despues de cada uno de los test
    void tearDown(){
        System.out.println("Despues");
       
    }
    */


}