package com.example.project;

import java.util.Random;

public class HashLinearProbing {
    private final int hsize; // tamaño total de la tabla hash
    private final Persona[] buckets; // array que representa la tabla hash
    // * Es necesario redefinir AVAILABLE para trabajar con Persona
    private static final String AVAILABLE = String.valueOf(Integer.MIN_VALUE); // constante que se le asigna a una celda de la tabla hash para indicar que NO está ocupada
    private int size; // cantidad actual de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.buckets = new Persona[hsize];
        this.hsize = hsize;
        this.size = 0;
    }

    // Devuelve un valor entre 0 y hash-1, inclusive
    public int hashing(String key) {
        int input = 0;

        for(char ch: key.toCharArray()) { 
            input ^= ch; 
        }

        int hash = input % hsize;
        return hash;
    }

    public boolean isFull() {        
        return size == hsize;
    }

    // key->Persona viene a ser un valor que se intertará como valor, cuya clave en la tabla hash será hashing(key)->[0,hsize-1]
    public void insertHash(Persona key) {
        if (isFull()) {
            System.out.println("Tabla hash esta llena!");
            return;
        }

        int hash = hashing(key.getDNI());

        // En Lineal Probing, en el peor de los casos se realizan 'hsize' saltitos
        for (int i = 0; i < hsize; i++) {
            // Ser verifica que dicha celda no posea un valor o este marcada como 'disponible' (Después de la Eliminación) para mantener consistencia
            if (buckets[hash] == null || buckets[hash].getDNI().equals(AVAILABLE)) {
                buckets[hash] = key;
                size++;
                return;
            }

            // Saltitos Circulares
            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            // Si alguna celda contiene un valor o está marcado como 'disponible' (Después de Eliminación), entonces NO está Vacía
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }

    // key->Persona cumple el rol de valor en la tabla hash, hashing(key)->'hash' cumple el rol de clave
    public void deleteHash(String key) {
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        // En Lineal Probing, se realizan como máximo 'hsize' saltitos al momento de verificar valores
        for (int i = 0; i < hsize; i++) {
            // Verificamos que buckets[hash] no sea nulo para evitar el NullPointerException y que el valor cuya posición es buckets[hashing(key)] coincida con key
            if (buckets[hash] != null && buckets[hash].getDNI().equals(key)) {
                // Lo marcamos como 'disponible' para no perder consistencia en la búsqueda y para que pueda ser asignado en la inserción
                buckets[hash].setDNI(AVAILABLE);
                size--;
                return;
            }

            // Saltitos Circulares
            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        // Exceder los 'hsize' saltitos indica que no se encontró la clave hashing(key)->'hash'
        System.out.println("Clave " + key + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            // puede que la celda esté marcada como 'disponible' (Sin Valor)
            if (buckets[i] == null || buckets[i] == AVAILABLE) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + buckets[i].toString());
            }
        }
    }

    public int findHash(int key) {
        Integer wrappedInt = key;
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return -1;
        }

        // En Linear Probing se realizan a lo mucho 'hsize' saltitos para la búsqueda
        for (int i = 0; i < hsize; i++) {
            // Se utiliza un bloque try-catch para evitar el NullPointerException de alguna de las iteraciones
            try {
                if (buckets[hash].equals(wrappedInt)) {
                    // buckets[hash] = AVAILABLE;
                    return hash;
                }
            } catch (Exception E) { }

            // Saltitos Circulares
            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        // Después de los 'hsize' saltitos, se da por No Encontrada key
        System.out.println("Clave " + key + " no encontrada!");
        return -1;
    }    

    public static void main (String[] args){
        HashLinearProbing tb = new HashLinearProbing(10);

        Random rd = new Random(10);

        for(int i = 0; i < 5; i++){
            tb.insertHash(rd.nextInt(100));
        }

        tb.displayHashtable();        
    }
}
