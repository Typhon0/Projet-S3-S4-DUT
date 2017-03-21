package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

/**
 * Created by XXIII on 21/03/2017.
 */

public class Partie {
    private ArrayList<Joueur> listeJoueurs;
    private Joueur joueurActif;
    private Plateau plateau;
    private De de1,de2;

    public Partie() {
        creerJoueurs();
    }

    public void creerJoueurs() {
        Joueur j1 = new Joueur("joueur 1", Color.RED);
        Joueur j2 = new Joueur("joueur 2", Color.BLUE);
        Joueur j3 = new Joueur("joueur 3", Color.GREEN);
        Joueur j4 = new Joueur("joueur 4", Color.YELLOW);
    }
}
