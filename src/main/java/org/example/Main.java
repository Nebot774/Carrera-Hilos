package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);//creamso el scanner


        System.out.print("Introduze el numero de corredores de la carrera: ");
        int numCorredores = teclado.nextInt();

        //lista donde se almacenan los corredores
        Corredor[] corredores = new Corredor[numCorredores];

        //bucle donde pedimos datos a erl usuario y creamos objetos corredores
        for (int i = 0; i < numCorredores; i++) {

            System.out.println("Datos para el corredor " + (i + 1));

            System.out.print("Inmtroduze el nombre : ");
            String nombre = teclado.nextLine();

            System.out.print("Inmtroduze el Símbolo: ");
            char simbolo = teclado.next().charAt(0);

            System.out.print("Introduzca la Velocidad base (del 1 al 5): ");
            int velocidadBase = teclado.nextInt();

            System.out.print("Introduzca el Turbo (del 1 al 5): ");
            int turbo = teclado.nextInt();

            System.out.print("Evasión (1-5): ");
            int evitarxoque= teclado.nextInt();

            corredores[i] = new Corredor(nombre, simbolo, velocidadBase, turbo,  evitarxoque);
        }

        System.out.println("¡Buum,Inicio de carrera!");

        for (Corredor corredor : corredores) { // Iniciar corredores
            corredor.start();
        }

        try {

            //esperamos a que todos los corredores terminen para seguir
            for (Corredor corredor : corredores) {
                corredor.join();

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Fin de la Carrera");
        Corredor ganador = corredores[0]; // Encontrar ganador

        //bulce reccorremos los  corredores
        for (Corredor corredor : corredores) {
            //buscamos el correcord con la mejor posicion
            if (corredor.getPosición() > ganador.getPosición()) {
                ganador = corredor;
            }

        }

        System.out.println("El ganador de la carrera es " + ganador.getNombre()); // Mostrar ganador
    }
}