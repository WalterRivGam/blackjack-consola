package com.proyectos.blackjack;

public class Carta {
    private int valor;
    private final Cara cara;
    private final Palo palo;
    
    public Carta(Cara cara, Palo palo){
        this.valor = cara.getValor();
        this.cara = cara;
        this.palo = palo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public Cara getCara() {
        return cara;
    }

    public Palo getPalo() {
        return palo;
    }

    @Override
    public String toString() {
        return String.format("%s de %s", cara, palo);
    } 
       
}
