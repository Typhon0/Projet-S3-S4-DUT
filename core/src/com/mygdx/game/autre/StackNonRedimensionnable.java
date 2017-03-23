package com.mygdx.game.autre;

import java.util.Random;

/**
 * StackNonRedimensionnable représente un paquet (bucket) non redimensionnalbe donc le fonctionnement est similaire à celui d'une pile. On peut également mélanger le StackNonRedimensionnable.
 *
 * @param <E> Type générique du paquet
 */

public class StackNonRedimensionnable<E> {

    private E[] items;  // tableau d'éléments
    private int n;           // nombre d'éléments dans le paquet

    public StackNonRedimensionnable(int capacity) {
        n = 0;
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == items.length;
    }

    public void push(E item) {
        items[n++] = item;
    }

    public E pop() {
        return items[--n];
    }

    public void shuffle() {
        int index;
        E tmp;
        Random random = new Random();
        for (int i = n - 1; i > 0; i--) {
            index = random.nextInt( i + 1 );
            tmp = items[index];
            items[index] = items[i];
            items[i] = tmp;
        }
    }

    public String toString() {
        String string = "Nombre d'éléménts : " + "\n";
        for (E s : items) {
            string += s + ", ";
        }
        return string;
    }

    // Getters
    public E[] getItems() {
        return items;
    }

    public int getN() {
        return n;
    }
}
