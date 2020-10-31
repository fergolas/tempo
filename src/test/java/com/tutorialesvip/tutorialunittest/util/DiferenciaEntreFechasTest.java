package com.tutorialesvip.tutorialunittest.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class DiferenciaEntreFechasTest {
    
    @Autowired  //Esto es para que spring lo inyecte directamente
    DiferenciaEntreFechas diferenciaEntreFechas;   // creamos una objeto instanciando a DEF

//    @Autowired
//    DiferenciaEntreFechas diferenciaEntreFechas;

    @Test
    void calculateYearsOfIndependency() {
        diferenciaEntreFechas = new DiferenciaEntreFechas(); //cuando manda error nullpointerexeption debemos inicializar el objeto

        LocalDate today = LocalDate.now();   //variable con la fecha actual tipo date
        String fechaIndependencia = "27/02/1844";    //variable fecha tipo string simulando la entrada
        
        //Objeto tipo periodo que tiene el resultado, hace el uso de la funcion/metodo que queremos probar, pasar la fecha que queremos evalual
        Period resultado = diferenciaEntreFechas.calculateYearsOfIndependency(fechaIndependencia);
        //System.out.println(resultado.getMonths());
        //System.out.println(resultado.getDays());
        //System.out.println(resultado.getYears());

        Assertions.assertEquals(8, resultado.getMonths());
        Assertions.assertEquals(3, resultado.getDays());
        Assertions.assertEquals(176, resultado.getYears());

        /*
        int numero1 = 5;
        int numero2 = 5;
        Assertions.assertEquals(10, numero1 + numero2);
        */
        
        /*
        diferenciaEntreFechas = new DiferenciaEntreFechas();
        String fechaIndependencia = "27/02/1844";

        Period resultado = diferenciaEntreFechas.calculateYearsOfIndependency(fechaIndependencia);

        Assertions.assertEquals(2,resultado.getMonths() );
        Assertions.assertEquals(29,resultado.getDays() );
        Assertions.assertEquals(176,resultado.getYears() );
        */
    }
}