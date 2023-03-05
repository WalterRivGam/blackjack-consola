package com.proyectos.blackjack;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;

public class Baraja {
    private LinkedList<Carta> baraja = new LinkedList<>();
    
    public Baraja() {
        for(Cara cara: Cara.values()){
            for(Palo palo:Palo.values()){
                baraja.push(new Carta(cara, palo));
            }
        }
        Collections.shuffle(baraja);
    }
    
    // Saca la carta que está encima de la baraja
    public Carta pop() {
        return baraja.pop();
    }
    
    // revuelve la baraja
    public void mezclar() {
        Collections.shuffle(baraja);
    }
}
