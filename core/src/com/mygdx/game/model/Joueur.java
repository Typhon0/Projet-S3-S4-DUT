package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * <b> Joueur est la classe représentant le joueur d'une partie. Le joueur peut échanger des ressources et construire des structures</b>
 * <p>
 * Un joueur  est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le nombre de points victoire du joueur</li>
 * <li>Les pions à placer</li>
 * <li>Les ressources dont il dispose</li>
 * <li>Les structures qui ont été construites</li>
 * <li>La couleur du joueur</li>
 * </ul>
 * </p>
 *
 * @author Geris Nicolas
 * @version 1.0
 * @see PaquetRessources
 * @see Structure
 * @see SiteConstruction
 */

public class Joueur {

    /**
     * Le nombre de points victoire du joueur
     */
    private int points;

    /**
     * Le tableau contenant le nombre de pions restants du joueur
     */
    private int[] paquetStructures;

    /**
     * Paquet de ressources du joueur
     */
    private PaquetRessources ressources;

    /**
     * Liste des structures construites du joueur
     */
    private List<Structure> listeStructures;

    /**
     * Couleur (en entier) du joueur. Voir classe Constantes
     */
    private int couleur; // couleur du joueur

    /**
     * Constructeur du Joueur. On initialise son nom, ses points victorires à 0, ses ressources, son tableau de pions et sa liste de structures construites.
     * Le paquet de strucures (les pions) sont initialiés par le nombre de pions de départ
     *
     * @param couleur
     */
    public Joueur(int couleur) {
        this.points = 0;
        this.paquetStructures = new int[Constantes.TAILLE_TABLEAU_STRUCTURE];
        this.listeStructures = new ArrayList<Structure>();
        this.ressources = new PaquetRessources( couleur );
        this.couleur = couleur;
        remplirPaquetStructures();
    }

    // Ajoute 4 villes, 5 colonies et 15 routes

    /**
     * Affecte au joueur de pions : des villes, colonies et routes. par défaut ajoute 4 villes, 5 colonies et 15 routes
     *
     * @see Constantes
     */
    public void remplirPaquetStructures() {
        paquetStructures[Constantes.VILLE] = Constantes.MONTANT_VILLE_MAXIMUM;
        paquetStructures[Constantes.COLONIE] = Constantes.MONTANT_COLONIE_MAXIMUM;
        paquetStructures[Constantes.ROUTE] = Constantes.MONTANT_ROUTE_MAXIMUM;
    }

    /**
     * Incrémente le nombre de points victoire
     *
     * @param points Points victoire à ajouter
     */
    public void ajouterPointVictoire(int points) {
        this.points += points;
    }

    /**
     * Construire une ville sur le site de construction qui a été touché
     * On vérifie si le site de construction n'est pas déjà construit
     * On vérifie si le site de construction peut accueillir un batiment
     * On vérifie s'il reste des villes (pions) à placer
     * On récupère le tableau de cout de construction d'une ville
     * On vérifie si le joueur posssède assez de ressources pour construire
     * Achete le montant de la ville
     * Construit la structure à la position du site de construction
     * Affecte la texture à la structure en fonction du type de construction et de la couleur du joueur
     * Ajoute la ville à la liste des structures du joueur
     * Set true le boolean estConstruit au site de construction
     * Affecte la structure au site de construction
     *
     * @param sc Site de construction que le joueur a touché
     * @see Structure
     * @see SiteConstruction
     */
    public void construireVille(SiteConstruction sc) {
        if (!sc.isEstConstruit()) {
            if (sc.isEstBatiment()) {
                if (paquetStructures[Constantes.VILLE] > 0) {
                    int[] cout = Constantes.getCoutConstructionVille();
                    if (estAchetable( cout )) {
                        paquetStructures[Constantes.VILLE]--; // décrémente de 1 le nombre de villes restantes
                        acheter( cout ); // achète le cout de la ville
                        Structure s = new Structure( this, sc, new Vector2( sc.getPosition().x, sc.getPosition().y ), Constantes.VILLE ); // Création de la structure
                        s.affecterTexture(); // affecte la texture à la structure selon son type de structure
                        ajouterStructure( s ); // ajoute la structure à la liste de structures du joueur
                        sc.setEstConstruit( true ); //
                        sc.setStructure( s );
                        //System.out.println("Points : "+points);
                    } else
                        Partie.getHud().afficherMessage( "Erreur de construction de ville", "Vous n'avez pas assez de ressources pour construire une ville" );
                } else
                    Partie.getHud().afficherMessage( "Erreur de construction de ville", "Vous n'avez plus de pion de type Ville en stock" );
            } else
                Partie.getHud().afficherMessage( "Erreur de construction de ville", "Vous ne pouvez pas construire une ville sur un site de construction de route" );
        } else
            Partie.getHud().afficherMessage( "Erreur de construction de ville", "Il y a déjà une structure construite à cet emplacement" );
    }

    /**
     * Construire une colonie sur le site de construction qui a été touché
     * On vérifie si le site de construction n'est pas déjà construit
     * On vérifie si le site de construction peut accueillir un batiment
     * On vérifie s'il reste des colonies (pions) à placer
     * On récupère le tableau de cout de construction d'une colonie
     * On vérifie si le joueur posssède assez de ressources pour construire
     * Achete le montant de la colonie
     * Construit la structure à la position du site de construction
     * Affecte la texture à la structure en fonction du type de construction et de la couleur du joueur
     * Ajoute la ville à la liste des structures du joueur
     * Set true le boolean estConstruit au site de construction
     * Affecte la structure au site de construction
     *
     * @param sc Site de construction que le joueur a touché
     * @see Structure
     * @see SiteConstruction
     */
    public void construireColonie(SiteConstruction sc) {
        if (!sc.isEstConstruit()) {
            if (sc.isEstBatiment()) {
                if (paquetStructures[Constantes.COLONIE] > 0) {
                    int[] cout = Constantes.getCoutConstructionColonie();
                    if (estAchetable( cout )) {
                        paquetStructures[Constantes.COLONIE]--; // décrémente de 1 le nombre de villes restantes
                        acheter( cout ); // achète le cout de la ville
                        //System.out.println("Coordonnées de la strucure : "+new Vector2( sc.getTuile().x, sc.getTuile().y ).toString());
                        Structure s = new Structure( this, sc, new Vector2( sc.getPosition().x, sc.getPosition().y ), Constantes.COLONIE ); // Création de la structure
                        s.affecterTexture(); // affecte la texture à la structure selon son type de structure
                        ajouterStructure( s ); // ajoute la structure à la liste de structures du joueur
                        sc.setEstConstruit( true ); //
                        sc.setStructure( s );
                        //System.out.println("Points : "+points);
                    } else
                        Partie.getHud().afficherMessage( "Erreur de construction de colonie", "Vous n'avez pas assez de ressources pour construire une colonie" );
                } else
                    Partie.getHud().afficherMessage( "Erreur de construction de colonie", "Vous n'avez plus de pion de type Colonie en stock" );
            } else
                Partie.getHud().afficherMessage( "Erreur de construction de colonie", "Vous ne pouvez pas construire une colonie sur un site de construction de route" );
        } else
            Partie.getHud().afficherMessage( "Erreur de construction de colonie", "Il y a déjà une structure construite à cet emplacement" );
    }

    /**
     * Construire une route sur le site de construction qui a été touché
     * On vérifie si le site de construction n'est pas déjà construit
     * On vérifie si le site de construction peut accueillir un batiment
     * On vérifie s'il reste des routes (pions) à placer
     * On récupère le tableau de cout de construction d'une route
     * On vérifie si le joueur posssède assez de ressources pour construire
     * Achete le montant de la route
     * Construit la structure à la position du site de construction
     * Affecte la texture à la structure en fonction du type de construction et de la couleur du joueur
     * Ajoute la route à la liste des structures du joueur
     * Set true le boolean estConstruit au site de construction
     * Affecte la structure au site de construction
     *
     * @param sc Site de construction que le joueur a touché
     * @see Structure
     * @see SiteConstruction
     */
    public void construireRoute(SiteConstruction sc) {
        if (!sc.isEstConstruit()) {
            if (!sc.isEstBatiment()) {
                if (paquetStructures[Constantes.ROUTE] > 0) {
                    int[] cout = Constantes.getCoutConstructionRoute();
                    if (estAchetable( cout )) {
                        paquetStructures[Constantes.ROUTE]--; // décrémente de 1 le nombre de villes restantes
                        acheter( cout ); // achète le cout de la ville
                        //System.out.println("Coordonnées de la strucure : "+new Vector2( sc.getTuile().x, sc.getTuile().y ).toString());
                        Structure s = new Structure( this, sc, new Vector2( sc.getPosition().x, sc.getPosition().y ), Constantes.ROUTE ); // Création de la structure
                        s.affecterTexture(); // affecte la texture à la structure selon son type de structure
                        ajouterStructure( s ); // ajoute la structure à la liste de structures du joueur
                        sc.setEstConstruit( true ); //
                        sc.setStructure( s );
                        //System.out.println("Points : "+points);
                    } else
                        Partie.getHud().afficherMessage( "Erreur de construction de route", "Vous n'avez pas assez de ressources pour construire une route" );
                } else
                    Partie.getHud().afficherMessage( "Erreur de construction de route", "Vous n'avez plus de pion de type Route en stock" );
            } else
                Partie.getHud().afficherMessage( "Erreur de construction de route", "Vous ne pouvez pas construire une route sur un site de construction de batiment" );
        } else
            Partie.getHud().afficherMessage( "Erreur de construction de route", "Il y a déjà une structure construite à cet emplacement" );
    }

    /**
     * Vérifie si le tableau de cout de construction d'une structure est achetable ou non
     *
     * @param cout Tableau de cout de construction de la structure
     * @return boolean si c'est achetable ou non
     */
    public boolean estAchetable(int[] cout) {
        for (int i = Constantes.NUMERO_RESSOURCE_MIN; i <= Constantes.NUMERO_RESSOURCE_MAX; i++) {
            if (!ressources.estRetirable( i, cout[i] )) {
                return false;
            }
        }
        return true;
    }

    /**
     * Acheter une structure en augmentant les ressources du jeu et en diminuant les ressources du joueur
     *
     * @param cout Tableau de cout de construction de la structure
     */
    public void acheter(int[] cout) {
        for (int i = Constantes.NUMERO_RESSOURCE_MIN; i <= Constantes.NUMERO_RESSOURCE_MAX; i++) {
            // ajoute les ressources au plateau, retire les ressources du joueur, la ressource i,pour un cout de cout[i]
            PaquetRessources.recevoirRessource( Plateau.getRessources(), ressources, i, cout[i] );
        }
    }

    /**
     * Ajoute la stucture à la liste des structures si elle n'est pas déjà contenu
     *
     * @param s
     */
    public void ajouterStructure(Structure s) {
        if (!listeStructures.contains( s )) {
            listeStructures.add( s );
        }
    }

    /**
     * Renvoie les ressources en String
     *
     * @return texte des ressources
     */
    public String getRessourcesString() {
        String s = "";
        for (int i = Constantes.NUMERO_RESSOURCE_MIN; i <= Constantes.NUMERO_RESSOURCE_MAX; i++) {
            s += Constantes.nomRessource( i ) + " : " + ressources.getRessources()[i] + "\t";
        }
        return s;
    }

    /**
     * Affiche les ressources
     */
    public void afficherRessources() {
        System.out.println( getRessourcesString() );
    }

    /**
     * parcourt tous les sites de constructions des structures du joueur afin de détermine si le joueur peut échanger des ressources avec le plateau
     *
     * @return boolean si le joueur possède une structure dont le site de construction possède un port
     */
    public boolean possedePort() {
        for (Structure s : listeStructures) {
            if (s.getSc().possedePort()) {
                return true;
            }
        }
        return false;
    }

    public int getTauxDeChangeMinimum() {
        int taux = Constantes.PORT_TAUX_DE_CHANGE_MAXIMUM;
        for (Structure s : listeStructures) {
            if (s.getSc().possedePort()) {
                if (s.getSc().getPort().getTauxDeChange() < taux);
                taux = s.getSc().getPort().getTauxDeChange();
            }
        }
        return taux;
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

    public PaquetRessources getPaquetRessources() {
        return ressources;
    }

    public List<Structure> getListeStructures() {
        return listeStructures;
    }

    public int getCouleur() {
        return couleur;
    }
}