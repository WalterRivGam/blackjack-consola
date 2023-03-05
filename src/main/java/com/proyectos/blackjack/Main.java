package com.proyectos.blackjack;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Baraja baraja = new Baraja();
        Jugador jugador = new Jugador(baraja, 500);
        Scanner scan = new Scanner(System.in);
        boolean jugarOtraVez = true;

        System.out.println("******************************");
        System.out.println("**********BLACK JACK**********");
        System.out.println("******************************");

        do {
            System.out.printf("Dinero: %d%n", jugador.getMonto());
            jugador.ingresarApuesta();
            System.out.println("Apuesta: " + jugador.getApuesta());

            new Blackjack(jugador, baraja);

            if (jugador.getMonto() == 0) {
                System.out.println("Te quedaste sin dinero");
                System.out.println("******************************");
                System.out.println("*********FIN DEL JUEGO********");
                System.out.println("******************************");
                return;
            }

            while (true) {
                System.out.print("Jugar nuevamente? (s/n): ");
                String jugarNuevamente = scan.next();
                if (jugarNuevamente.toLowerCase().equals("n")) {
                    System.out.println("******************************");
                    System.out.println("*********FIN DEL JUEGO********");
                    System.out.println("******************************");
                    jugarOtraVez = false;
                    break;
                } else if (jugarNuevamente.toLowerCase().equals("s")) {
                    jugarOtraVez = true;
                    baraja = new Baraja();
                    jugador.setBaraja(baraja);
                    jugador.setCartas(new LinkedList<>());
                    break;
                } else {
                    System.out.println("Debe ingresar 's' o 'n'");
                }
            }

        } while (jugarOtraVez);
    }
}
