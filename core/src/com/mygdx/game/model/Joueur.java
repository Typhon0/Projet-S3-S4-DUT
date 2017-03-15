package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico on 14/03/2017.
 */

public class Joueur {

    private int points;
    private int[] paquetStructures;
    private PaquetRessources ressources;
    private List<Structure> listeStructures;
    private String nom;
    private Color couleur;

    public Joueur(String nom) {
        this.nom = nom;
        this.points = 0;
        this.paquetStructures = new int[Constantes.TAILLE_TABLEAU_STRUCTURE];
        this.listeStructures = new ArrayList<Structure>();
        this.ressources = new PaquetRessources(nom);

        remplirPaquetStructures();
    }

    // Ajoute 4 villes, 5 colonies et 15 routes
    public void remplirPaquetStructures() {
        paquetStructures[Constantes.VILLE] = Constantes.MONTANT_VILLE_MAXIMUM;
        paquetStructures[Constantes.COLONIE] = Constantes.MONTANT_COLONIE_MAXIMUM;
        paquetStructures[Constantes.ROUTE] = Constantes.MONTANT_ROUTE_MAXIMUM;
    }

    public void ajouterPointVictoire(int points){
        this.points += points;
    }

    public void construireVille(){
        if (paquetStructures[Constantes.VILLE] >0) {
            int[]cout = Constantes.getCoutConstructionVille();
            if (estAchetable(cout)) {
                paquetStructures[Constantes.VILLE]--; // décrémente de 1 le nombre de villes restantes
                acheter(cout); // achète le cout de la ville
                listeStructures.add(new Structure()); // crée la ville et l'ajoute à la liste des structures
                // A COMPLETER
            }
            else
                throw new RuntimeException("Erreur : Vous n'avez pas assez de ressource pour construire une ville");
        }
        else
            throw new RuntimeException("Erreur : Vous n'avez plus de ville à construire");
    }

    public void construireColonie(){
        if (paquetStructures[Constantes.COLONIE] >0) {
            paquetStructures[Constantes.COLONIE]--;
            // Créer l'objet et le placer dans "ListeStructures"
        } else {
            throw new RuntimeException("Erreur : Vous n'avez plus de colonie à construire");
        }
    }

    public void construireRoute(){
        if (paquetStructures[Constantes.ROUTE] >0) {
            paquetStructures[Constantes.ROUTE]--;
            // Créer l'objet et le placer dans "ListeStructures"
        } else {
            throw new RuntimeException("Erreur : Vous n'avez plus de colonie à construire");
        }
    }

    public boolean estAchetable(int[] cout) {
        //boolean estAchetable = true;
        for (int i=Constantes.NUMERO_RESSOURCE_MIN ; i<=Constantes.NUMERO_RESSOURCE_MAX ; i++) {
            // Si l'un des ressources est manquante on arrête le parcours et ren
            if (!ressources.estRetirable(i,cout[i])) {
                System.out.println("Il manque des ressources : quitte la méthode estAchetable()");
                return false;
            }
        }
        //return estAchetable;
        return true;
    }

    // Acheter une structure en augmentant les ressources du jeu et en diminuant les ressources du joueur
    public void acheter(int[]cout) {
        for (int i=Constantes.NUMERO_RESSOURCE_MIN ; i<=Constantes.NUMERO_RESSOURCE_MAX ; i++) {
            // ajoute les ressources au plateau, retire les ressources du joueur, la ressource i,pour un cout de cout[i]
            PaquetRessources.echange(Plateau.getRessources(),ressources,i,cout[i]);
        }
    }

    // Renvoie les ressources en String
    public String getRessourcesString() {
        String s ="";
        for (int i=Constantes.NUMERO_RESSOURCE_MIN ; i<=Constantes.NUMERO_RESSOURCE_MAX ; i++) {
            s+=Constantes.nomRessource(i)+" : "+ressources.getRessources()[i]+"\n";
        }
        return s;
    }

    // affiche les ressources
    public void afficherRessources() {
        System.out.println(getRessourcesString());
    }


    // Getters et Setters

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPaquetStructures(int[] paquetStructures) {
        this.paquetStructures = paquetStructures;
    }

    public void setRessources(PaquetRessources ressources) {
        this.ressources = ressources;
    }

    public void setListeStructures(List<Structure> listeStructures) {
        this.listeStructures = listeStructures;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public int getPoints() {
        return points;
    }

    public int[] getPaquetStructures() {
        return paquetStructures;
    }

    public PaquetRessources getRessources() {
        return ressources;
    }

    public List<Structure> getListeStructures() {
        return listeStructures;
    }

    public Color getCouleur() {
        return couleur;
    }
}