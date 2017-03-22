package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico on 14/03/2017.
 */

public class Joueur {

    private int points; // Points victoiress
    private int[] paquetStructures; // Tableau contenant le nombre de structures restantes
    private PaquetRessources ressources; // paquet de ressources
    private List<Structure> listeStructures; // liste des structures placées
    private String nom; // nom du joueur
    private int couleur; // couleur du joueur

    public Joueur(String nom, int couleur) {
        this.nom = nom;
        this.points = 0;
        this.paquetStructures = new int[Constantes.TAILLE_TABLEAU_STRUCTURE];
        this.listeStructures = new ArrayList<Structure>();
        this.ressources = new PaquetRessources(nom);
        this.couleur = couleur;
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

    public void construireVille(SiteConstruction sc){
        if (!sc.isEstConstruit()) {
            if (sc.isEstBatiment()) {
                if (paquetStructures[Constantes.VILLE] >0) {
                    int[]cout = Constantes.getCoutConstructionVille();
                    if (estAchetable(cout)) {
                        paquetStructures[Constantes.VILLE]--; // décrémente de 1 le nombre de villes restantes
                        acheter( cout ); // achète le cout de la ville
                        //System.out.println("Coordonnées de la strucure : "+new Vector2( sc.getPosition().x, sc.getPosition().y ).toString());
                        Structure s = new Structure( this,sc, new Vector2( sc.getPosition().x, sc.getPosition().y), Constantes.VILLE ); // Création de la structure
                        s.affecterTexture(); // affecte la texture à la structure selon son type de structure
                        ajouterStructure( s ); // ajoute la structure à la liste de structures du joueur
                        sc.setEstConstruit( true ); //
                        sc.setStructure( s );
                        System.out.println("Points : "+points);
                    }
                    else
                        System.err.println("Erreur : Vous n'avez pas assez de ressource pour construire une ville");
                }
                else
                    System.err.println("Erreur : Vous n'avez plus de ville à construire");
            }
            else
                System.err.println("Erreur : Vous ne pouvez pas construire une ville à cet emplacement");
        }
        else
            System.err.println("Erreur : il y a déjà une structure construite à cet emplacement");
    }

    public void construireColonie(SiteConstruction sc){
        if (!sc.isEstConstruit()) {
            if (sc.isEstBatiment()) {
                if (paquetStructures[Constantes.COLONIE] >0) {
                    int[]cout = Constantes.getCoutConstructionColonie();
                    if (estAchetable(cout)) {
                        paquetStructures[Constantes.COLONIE]--; // décrémente de 1 le nombre de villes restantes
                        acheter( cout ); // achète le cout de la ville
                        //System.out.println("Coordonnées de la strucure : "+new Vector2( sc.getPosition().x, sc.getPosition().y ).toString());
                        Structure s = new Structure( this,sc, new Vector2( sc.getPosition().x, sc.getPosition().y), Constantes.COLONIE ); // Création de la structure
                        s.affecterTexture(); // affecte la texture à la structure selon son type de structure
                        ajouterStructure( s ); // ajoute la structure à la liste de structures du joueur
                        sc.setEstConstruit( true ); //
                        sc.setStructure( s );
                        System.out.println("Points : "+points);
                    }
                    else
                        System.err.println("Erreur : Vous n'avez pas assez de ressource pour construire une colonie");
                }
                else
                    System.err.println("Erreur : Vous n'avez plus de colonie à construire");
            }
            else
                System.err.println("Erreur : Vous ne pouvez pas construire une colonie à cet emplacement");
        }
        else
            System.err.println("Erreur : il y a déjà une structure construite à cet emplacement");
    }

    public void construireRoute(SiteConstruction sc){
        if (!sc.isEstConstruit()) {
            if (!sc.isEstBatiment()) {
                if (paquetStructures[Constantes.ROUTE] >0) {
                    int[]cout = Constantes.getCoutConstructionRoute();
                    if (estAchetable(cout)) {
                        paquetStructures[Constantes.ROUTE]--; // décrémente de 1 le nombre de villes restantes
                        acheter( cout ); // achète le cout de la ville
                        //System.out.println("Coordonnées de la strucure : "+new Vector2( sc.getPosition().x, sc.getPosition().y ).toString());
                        Structure s = new Structure( this,sc, new Vector2( sc.getPosition().x, sc.getPosition().y), Constantes.ROUTE ); // Création de la structure
                        s.affecterTexture(); // affecte la texture à la structure selon son type de structure
                        ajouterStructure( s ); // ajoute la structure à la liste de structures du joueur
                        sc.setEstConstruit( true ); //
                        sc.setStructure( s );
                        System.out.println("Points : "+points);
                    }
                    else
                        System.err.println("Erreur : Vous n'avez pas assez de ressource pour construire une route");
                }
                else
                    System.err.println("Erreur : Vous n'avez plus de route à construire");
            }
            else
                System.err.println("Erreur : Vous ne pouvez pas construire une route à cet emplacement");
        }
        else
            System.err.println("Erreur : il y a déjà une structure construite à cet emplacement");
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
            PaquetRessources.recevoirRessource(Plateau.getRessources(),ressources,i,cout[i]);
        }
    }

    public void ajouterStructure(Structure s) {
        if (!listeStructures.contains( s )) {
            listeStructures.add( s );
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

    public void setCouleur(int couleur) {
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

    public int getCouleur() {
        return couleur;
    }
}