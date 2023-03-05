package com.proyectos.blackjack;

public enum Cara {
    As(1),
    Dos(2),
    Tres(3),
    Cuatro(4),
    Cinco(5),
    Seis(6),
    Siete(7),
    Ocho(8),
    Nueve(9),
    Diez(10),
    Jota(10),
    Reina(10),
    Rey(10);

    private final int valor;

    Cara(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return this.valor;
    }

}
