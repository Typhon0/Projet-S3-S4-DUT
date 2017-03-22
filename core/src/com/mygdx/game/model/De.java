package com.mygdx.game.model;

import java.util.Random;

/**
 * <b> De est la classe représentant le dé qui sert à définir la tuile qui doivent produire au début du tour en fonction de leur jeton</b>
 * <p>
 *     Un De  est caractérisé par les informations suivantes :
 *     <ul>
 *         <li>le générateur d'entier aléatoire</li>
 *         <li>la valeur du dé </li>
 *         <li>la valeur minimale possible du dé</li>
 *         <li>La valeur maximale possible du dé</li>
 *     </ul>
 * </p>
 * @see Random
 * @author Geris Nicolas
 * @version 1.0
 */

public class De {

    /**
     * Valeur minimum du dé
     */
    public static final int MIN = 1;

    /**
     * Valeur maximum du dé
     */
    public static final int MAX = 6;

    /**
     * Générateur de nombre aléatoire
     */
    private Random r;

    /**
     * Valeur du dé une fois lancé
     */
    private int valeur;

    /**
     * Constructeur qui initialise le générateur de nombre entier aléatoire et la valeur du dé à 0.
     * @see Random
     */
    public De() {
        r = new Random();
        valeur = 0;
    }

    /**
     * Génère un nombre aléatoire compris entre la valeur minimale et maximale (compris) et affecte cette valeur à l'attribut valeur
     * @see De#valeur
     * @see Random#nextInt(int)
     * @see De#MIN
     * @see De#MAX
     */
    public void lancer() {
        valeur = MIN + r.nextInt(MAX - MIN + 1);
    }

    /**
     * toString de la classe qui affiche la valeur du dé
     * @return la valeur du dé sous forme de texte
     */
    public String toString() {
        return "Valeur du dé : "+valeur;
    }

    public int getValeur() {
        return valeur;
    }
}
