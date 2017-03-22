package com.mygdx.game.model;

/**
 * Created by XXIII on 21/03/2017.
 */

public class Partie {
    private int indiceJoueurActif;
    private Joueur[] joueurs;
    private Joueur joueurActif;
    private int typeStructure; // type de structure sélectionnée par le joueur
    private Plateau plateau;
    private De de1,de2;

    public Partie() {
        creerPlateau();
        creerJoueurs();
        creerDes();
        joueurActif = joueurs[0];
        donnerRessourcesDepart();
    }

    public void creerJoueurs() {
        Joueur j1 = new Joueur("joueur 1", Constantes.COULEUR_ROUGE );
        Joueur j2 = new Joueur("joueur 2", Constantes.COULEUR_BLEU );
        Joueur j3 = new Joueur("joueur 3", Constantes.COULEUR_VERT );
        Joueur j4 = new Joueur("joueur 4", Constantes.COULEUR_JAUNE );
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

    public void donnerRessourcesDepart() {
        /*
        for (int i=0 ; i<joueurs.length ; i++) {
            PaquetRessources.recevoirRessource( plateau.getRessources(),joueurs[i].getRessources(), );
        }
        */
        PaquetRessources.recevoirRessource( joueurs[0].getRessources(),plateau.getRessources(),Constantes.ARGILE,10 );
        PaquetRessources.recevoirRessource( joueurs[0].getRessources(),plateau.getRessources(),Constantes.BLE,10 );
        PaquetRessources.recevoirRessource( joueurs[0].getRessources(),plateau.getRessources(),Constantes.MINERAI,10 );
        PaquetRessources.recevoirRessource( joueurs[0].getRessources(),plateau.getRessources(),Constantes.BOIS,10 );
        PaquetRessources.recevoirRessource( joueurs[0].getRessources(),plateau.getRessources(),Constantes.LAINE,10 );

    }

    public void afficherPointsVictoire() {
        for (int i=0 ; i<joueurs.length ; i++) {
            System.out.println("Joueur "+i+" "+joueurs[i].getPoints());
        }
    }
/*
    public static void majPoints() {
        afficherPointsVictoire();
    }
*/
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

    public int getTypeStructure() {
        return typeStructure;
    }

    public void setTypeStructure(int typeStructure) {
        this.typeStructure = typeStructure;
    }
}
