package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestExercise1 {

  @Test
  public void case_1() {

    Exercise1 obj = new Exercise1();

    obj.tb = new HashLinearProbing(100);

    obj.insertarPersona(new Persona("12345678", "Jorge Chamby"));
    obj.insertarPersona(new Persona("12345679", "Juan Perez"));

    String result = obj.encontrarPersona("12345678");
    
    String expected = "Jorge Chamby";

    assertEquals(expected, result, "La salida esperada es: " + expected + " pero fue retornado: " + result);
  }

  @Test
  public void case_2() {

    Exercise1 obj = new Exercise1();

    obj.tb = new HashLinearProbing(100);

    obj.insertarPersona(new Persona("12345678", "Jorge Chamby"));
    obj.insertarPersona(new Persona("12345679", "Juan Perez"));
    obj.eliminarPersona("12345678");

    String result = obj.encontrarPersona("12345678");
    
    String expected = null;

    assertEquals(expected, result, "La salida esperada es: " + expected + " pero fue retornado: " + result);
  }

  @Test
  public void case_3() {

    Exercise1 obj = new Exercise1();

    obj.tb = new HashLinearProbing(100);

    obj.insertarPersona(new Persona("12345678", "Jorge Chamby"));
    obj.eliminarPersona("12345678");

    String result = obj.encontrarPersona("12345678");
    
    String expected = null;

    assertEquals(expected, result, "La salida esperada es: " + expected + " pero fue retornado: " + result);
  }

  @Test
  public void case_4() {

    Exercise1 obj = new Exercise1();

    obj.tb = new HashLinearProbing(100);

    obj.insertarPersona(new Persona("12345678", "Jorge Chamby"));
    obj.eliminarPersona("12345678");
    obj.insertarPersona(new Persona("29123456", "Raul Gonzalez"));
    obj.insertarPersona(new Persona("17893665", "Cristiano Ronaldo"));
    obj.insertarPersona(new Persona("17865665", "Lionel Messi"));
    obj.insertarPersona(new Persona("14563665", "Luka Modric"));
    obj.insertarPersona(new Persona("93254565", "Lucas Moura"));
    obj.insertarPersona(new Persona("87521665", "James Rodriguez"));
    obj.eliminarPersona("14563665");
    obj.eliminarPersona("93254565");

    String result = obj.encontrarPersona("17893665");
    
    String expected = "Cristiano Ronaldo";

    assertEquals(expected, result, "La salida esperada es: " + expected + " pero fue retornado: " + result);
  }

}
