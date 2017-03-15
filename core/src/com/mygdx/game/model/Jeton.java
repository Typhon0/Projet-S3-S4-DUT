package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by XXIII on 23/01/2017.
 */

public class Jeton {

    private Integer valeur; // valeur du jeton
    private Tuile tuile; // la tuile associée à chaque jeton
    private Texture textureJeton;

    public Jeton(Integer valeur) {
        affecterValeur(valeur);
    }

    // Affecte une tuile au jeton
    public void affecterTuile(Tuile h) {
        this.setTuile(h);
    }

    // Affecte une valeur au jeton
    public void affecterValeur(Integer valeur){
        this.setValeur(valeur);
        affecterTexture();
    }

    // Affecte la texture correspondante à la valeur du jeton
    public void affecterTexture() {
        if (valeur == 2)
            textureJeton = Plateau.getJeton2();
        else if (valeur == 3)
            textureJeton = Plateau.getJeton3();
        else if (valeur == 4)
            textureJeton = Plateau.getJeton4();
        else if (valeur == 5)
            textureJeton = Plateau.getJeton5();
        else if (valeur == 6)
            textureJeton = Plateau.getJeton6();
        else if (valeur == 8)
            textureJeton = Plateau.getJeton8();
        else if (valeur == 9)
            textureJeton = Plateau.getJeton9();
        else if (valeur == 10)
            textureJeton = Plateau.getJeton10();
        else if (valeur == 11)
            textureJeton = Plateau.getJeton11();
        else if (valeur == 12)
            textureJeton = Plateau.getJeton12();
        else {
            textureJeton = null;
            System.err.println("Valeur du jeton incorrecte");
        }
    }

    public void produire() {

    }

    public String toString() {
        return valeur.toString();
    }


    // Getters & Setters

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public Texture getTextureJeton() {
        return textureJeton;
    }

    public Integer getValeur() {
        return valeur;
    }

    public Tuile getTuile() {
        return tuile;
    }
}