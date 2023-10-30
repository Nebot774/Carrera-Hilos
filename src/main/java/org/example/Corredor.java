package org.example;

import java.util.Random;

public class Corredor extends Thread { // Clase que extiende de Thread
    private String nombre;
    private char simbolo;
    private int velocidad;
    private int turbo;
    private int evitarxoque;
    private int posicion;


    // Constructor con restricciones
    public Corredor(String nombre, char simbolo, int velocidad, int turbo, int evitarxoque) {
        if (validarValor(velocidad) && validarValor(turbo) && validarValor(evitarxoque)) {
            if ((velocidad + turbo + evitarxoque) <= 10) {
                this.nombre = nombre;
                this.simbolo = simbolo;
                this.velocidad = velocidad;
                this.turbo = turbo;
                this.evitarxoque = evitarxoque;
                this.posicion = 0;
            } else {
                throw new IllegalArgumentException("La suma de 'velocidad', 'turbo' y 'evitarxoque' no puede ser superior a 10.");
            }
        } else {
            throw new IllegalArgumentException("Los valores de 'velocidad', 'turbo' y 'evitarxoque' deben estar entre 1 y 5.");
        }
    }

    // Método de validación
    private boolean validarValor(int valor) {
        return valor >= 1 && valor <= 5;
    }

    //getters que necesitamos
    public int getPosición() {
        return posicion;
    }
    public String getNombre() {
        return nombre;
    }


    @Override
    public void run() {
        Random aleatorio = new Random();
        while (posicion < 100) {
            int avance = calculoAvance(aleatorio);
            if (avance > 0) {
                posicion += avance;
            }

            // Limpia la terminal o consola (si la admite)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(nombre + " (" + simbolo + "): " + resultadoCarrera());

            try {
                Thread.sleep(1000); // Simula un segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int calculoAvance(Random rand) { // Método que calcula el avance

        int avance = rand.nextInt(velocidad + 1);

        //
        if (rand.nextInt(5) < turbo) {
            avance *= 2;
        }

        if (rand.nextInt(5) >= evitarxoque) {
            avance = 0;
        }

        return avance;

    }

    private String resultadoCarrera() { // Método que devuelve la carrera
        StringBuilder carrera = new StringBuilder();

        for (int i = 0; i < 100; i++) {

            if (i == posicion) {

                carrera.append(simbolo);

            } else {

                carrera.append("_");

            }
        }

        return carrera.toString();

    }


}

