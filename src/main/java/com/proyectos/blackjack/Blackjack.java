package com.proyectos.blackjack;

import java.util.Scanner;

public class Blackjack {

    private Baraja baraja;
    private Jugador jugador;
    private Jugador crupier;

    public Blackjack(Jugador jugador, Baraja baraja) {
        this.jugador = jugador;
        this.baraja = baraja;
        this.crupier = new Jugador(baraja, 1);
        nuevoJuego();
    }

    // Crea un nuevo juego de Blackjack
    public void nuevoJuego() {
        Scanner scan = new Scanner(System.in);
        jugador.sacarCarta(); // Jugador saca su primera carta
        jugador.sacarCarta(); // Jugador saca su segunda carta
        System.out.println("Cartas del jugador:");
        jugador.mostrar();

        if (jugador.tieneBlackjack()) {
            System.out.println("BLACKJACK!!!");
        }

        crupier.sacarCarta(); // Crupier saca su primera carta
        crupier.sacarCarta(); // Crupier saca su segunda carta
        System.out.println("Primera carta del crupier:");
        System.out.println("> " + crupier.getCartas().get(0));
        System.out.println("> Valor: " + crupier.getCartas().get(0).getValor());

        if (crupier.tieneBlackjack()) {
            System.out.println("Segunda carta del crupier:");
            System.out.println("> " + crupier.getCartas().get(1));
            System.out.println("> Valor: " + crupier.getCartas().get(0).getValor());
            System.out.println("BLACKJACK!!!");
        }

        if (jugador.tieneBlackjack() && !crupier.tieneBlackjack()) {
            System.out.println("GANASTE!!!");
            jugador.setMonto(jugador.getMonto() + jugador.getApuesta());
            System.out.println("Dinero: " + jugador.getMonto());
            return;
        }
        if (!jugador.tieneBlackjack() && crupier.tieneBlackjack()) {
            System.out.println("GANA LA CASA!!!");
            jugador.setMonto(jugador.getMonto() - jugador.getApuesta());
            System.out.println("Dinero: " + jugador.getMonto());
            return;
        }
        if (jugador.tieneBlackjack() && crupier.tieneBlackjack()) {
            System.out.println("EMPATE!!!");
            System.out.println("Dinero: " + jugador.getMonto());
            return;
        }

        boolean sacarCarta = true;
        do {
            System.out.println("Desea sacar otra carta? (s/n)");
            String sacar = scan.next();
            if (sacar.toLowerCase().equals("s")) {
                sacarCarta = true;
                jugador.sacarCarta();
                System.out.println("Cartas del jugador:");
                jugador.mostrar();
                if (jugador.calcularTotal() > 21) {
                    System.out.println("Te pasaste de 21");
                    System.out.println("Gana la casa!");
                    jugador.setMonto(jugador.getMonto() - jugador.getApuesta());
                    System.out.println("Dinero: " + jugador.getMonto());
                    sacarCarta = false;
                } else if (jugador.calcularTotal() == 21) {
                    finalizarJuego();
                    sacarCarta = false;
                }
            } else if (sacar.toLowerCase().equals("n")) {
                sacarCarta = false;
                finalizarJuego();
            } else {
                System.out.println("Debe ingresar 's' o 'n'");
            }
        } while (sacarCarta);

    }

    public void finalizarJuego() {
        System.out.println("Cartas del crupier:");
        crupier.mostrar();
        Carta carta;
        while (crupier.calcularTotal() < 17) {
            carta = crupier.sacarCarta();
            System.out.printf("El crupier sacó la carta: %s%n", carta);
        }
        if (crupier.calcularTotal() > 21) {
            mostrarTotales();
            System.out.println("El crupier se pasó de 21!");
            System.out.println("Gana el jugador!");
            System.out.println("Dinero: " + jugador.getMonto());
        } else if (jugador.calcularTotal() > crupier.calcularTotal()) {
            mostrarTotales();
            System.out.println("Gana el jugador!");
            jugador.setMonto(jugador.getMonto() + jugador.getApuesta());
            System.out.println("Dinero: " + jugador.getMonto());
        } else if (jugador.calcularTotal() < crupier.calcularTotal()) {
            mostrarTotales();
            System.out.println("Gana la casa!");
            jugador.setMonto(jugador.getMonto() - jugador.getApuesta());
            System.out.println("Dinero: " + jugador.getMonto());
        } else if (jugador.calcularTotal() == crupier.calcularTotal()) {
            mostrarTotales();
            System.out.println("Empate!");
            System.out.println("Dinero: " + jugador.getMonto());
        }
    }

    void mostrarTotales() {
        System.out.println("Total jugador: " + jugador.calcularTotal());
        System.out.println("Total crupier: " + crupier.calcularTotal());
    }

}
