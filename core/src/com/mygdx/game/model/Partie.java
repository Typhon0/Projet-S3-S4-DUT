package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

/**
 * Created by XXIII on 21/03/2017.
 */

public class Partie {
    private int indiceJoueurActif;
    private Joueur[] joueurs;
    private Joueur joueurActif;
    private Plateau plateau;
    private De de1,de2;

    public Partie() {
        creerPlateau();
        creerJoueurs();
        creerDes();
    }

    public void creerJoueurs() {
        Joueur j1 = new Joueur("joueur 1", Color.RED);
        Joueur j2 = new Joueur("joueur 2", Color.BLUE);
        Joueur j3 = new Joueur("joueur 3", Color.GREEN);
        Joueur j4 = new Joueur("joueur 4", Color.YELLOW);
        joueurs = new Joueur[]{j1,j2,j3,j4};
    }

    public void creerDes() {
        de1 = new De();
        de2 = new De();
    }

    public void creerPlateau() {
        plateau = new Plateau();
        plateau.generer();
    }



    // Getters & Setters

    public int getIndiceJoueurActif() {
        return indiceJoueurActif;
    }

    public void setIndiceJoueurActif(int indiceJoueurActif) {
        this.indiceJoueurActif = indiceJoueurActif;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    public Joueur getJoueurActif() {
        return joueurActif;
    }

    public void setJoueurActif(Joueur joueurActif) {
        this.joueurActif = joueurActif;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public De getDe1() {
        return de1;
    }

    public void setDe1(De de1) {
        this.de1 = de1;
    }

    public De getDe2() {
        return de2;
    }

    public void setDe2(De de2) {
        this.de2 = de2;
    }
}
