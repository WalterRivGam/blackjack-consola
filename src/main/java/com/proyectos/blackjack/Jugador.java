package com.proyectos.blackjack;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Jugador {

    private LinkedList<Carta> cartas;
    private Baraja baraja;
    private int monto; // dinero restante del jugador
    private int apuesta;

    public Jugador(Baraja baraja, int monto) {
        this.cartas = new LinkedList<>();
        this.baraja = baraja;
        this.monto = monto;
    }

    public void setBaraja(Baraja baraja) {
        this.baraja = baraja;
    }

    public void setCartas(LinkedList<Carta> cartas) {
        this.cartas = cartas;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public LinkedList<Carta> getCartas() {
        return cartas;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Carta sacarCarta() {
        Carta carta = baraja.pop();
        cartas.add(carta);
        return carta;
    }

    // Muestra las cartas del jugador y el total que suman sus cartas
    public void mostrar() {
        for (Carta carta : cartas) {
            System.out.println("> " + carta);
        }
        System.out.printf("> Total: %s%n", calcularTotal());
    }

    // retorna true si el jugador tiene tieneBlackjack o false si no lo tiene
    public boolean tieneBlackjack() {
        if (cartas.size() == 2) {
            if (cartas.get(0).getCara().equals(Cara.As)
                    && cartas.get(1).getValor() == 10) {
                return true;
            }
            if (cartas.get(1).getCara().equals(Cara.As)
                    && cartas.get(0).getValor() == 10) {
                return true;
            }
        }
        return false;
    }

    // Calcula el total del valor de las cartas
    public int calcularTotal() {
        int total = 0;

        // Sumo las cartas que no son has porque tienen valor fijo
        for (Carta carta : cartas) {
            if (!carta.getCara().equals(Cara.As)) {
                total += carta.getValor();
            }
        }

        // cuento número de hases y coloco el valor de cada has en 1
        int nroHases = 0;
        for (Carta carta : cartas) {
            if (carta.getCara().equals(Cara.As)) {
                carta.setValor(1);
                nroHases++;
            }
        }

        // si al cambiar un has de valor 1 a valor de 11 no se sobrepasa de 21, realizo el cambio
        // sino calculo el total considerando todos los hases con valor de 1
        if (total + nroHases + 10 <= 21) {
            for (Carta carta : cartas) {
                if (carta.getCara().equals(Cara.As)) {
                    carta.setValor(11);
                    total += nroHases + 10;
                    break;
                }
            }
        } else {
            total += nroHases;
        }

        return total;
    }

    // Solicita al jugador ingresar la apuesta. Verifica que la apuesta sea un
    // número entero y esté en el rango correcto. Sino vuelve a solicitar que
    // ingrese la apuesta
    public void ingresarApuesta() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Ingrese apuesta: ");
            try {
                this.setApuesta(scan.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero!");
                scan.next();
                this.setApuesta(-1);
                continue;
            }
            if (this.getApuesta() <= 0) {
                System.out.println("Apuesta debe ser positiva");
            } else if (this.getApuesta() > this.getMonto()) {
                System.out.println("Estas apostando mas de lo que tienes!");
            }
        } while (this.getApuesta() <= 0 || this.getApuesta() > this.getMonto());

    }

}
