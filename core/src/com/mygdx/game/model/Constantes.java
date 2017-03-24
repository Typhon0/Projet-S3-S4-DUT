package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;

/**
 * Cette classe sert à définir toutes les constantes qui sont utilisées dans le programme ainsi que des méthodes utilitaires.
 * Pour faire varier des données du jeu, il suffit de modifier les valeurs de ces constantes
 */

public class Constantes {
    /*

     */
    public static final int TAILLE_TUILE = Gdx.graphics.getHeight() / 10;
    public static final float DELTA_X = (float) Math.sqrt( 3 ) * TAILLE_TUILE / 2;
    public static final int PORT_TAUX_DE_CHANGE_MAXIMUM = 4;
    public static final int MONTANT_RESSOURCE_MAXIMUM = 10000; // montant maximum de ressources par type
    public static final int MONTANT_RESSOURCE_DEPART_BOIS = 100;
    public static final int MONTANT_RESSOURCE_DEPART_LAINE = 100;
    public static final int MONTANT_RESSOURCE_DEPART_BLE = 100;
    public static final int MONTANT_RESSOURCE_DEPART_ARGILE = 100;
    public static final int MONTANT_RESSOURCE_DEPART_MINERAI = 100;

    public static final int BOIS = 1;
    public static final int LAINE = 2;
    public static final int BLE = 3;
    public static final int ARGILE = 4;
    public static final int MINERAI = 5;

    public static final int FORET = 1;
    public static final int PRE = 2;
    public static final int CHAMP = 3;
    public static final int COLLINE = 4;
    public static final int MONTAGNE = 5;
    public static final int DESERT = 6;

    public static final int NUMERO_RESSOURCE_MIN = 1;
    public static final int NUMERO_RESSOURCE_MAX = 5;
    public static final int TAILLE_TABLEAU_RESSOURCE = NUMERO_RESSOURCE_MAX + 1; // sert pour la taille de tableau, on ne travaille pas avec l'indice 0 du talbeau



    public static String nomRessource(int ressource) {
        String nom;
        switch (ressource) {
            case BOIS:
                nom = "bois";  break;
            case LAINE:
                nom = "laine";   break;
            case BLE:
                nom = "ble"; break;
            case ARGILE:
                nom = "argile"; break;
            case MINERAI:
                nom = "minerai";  break;
            default :
                nom = null; // ne doit pas se produire
        }
        return nom;
    }

    /*
        Constantes pour les structures
     */
    public static final float STRUCTURE_DELTA_X = 16.0f; // largeur d'un SiteConstruction /2
    public static final float STRUCTURE_DELTA_Y = 16.0f; // hauteur d'un SiteConstruction /2

    public static final int MONTANT_VILLE_MAXIMUM = 4;
    public static final int MONTANT_COLONIE_MAXIMUM = 5;
    public static final int MONTANT_ROUTE_MAXIMUM = 15;

    public static final int VILLE = 1;
    public static final int COLONIE = 2;
    public static final int ROUTE = 3;

    public static final int POINTS_VICTOIRE_VILLE = 2;
    public static final int POINTS_VICTOIRE_COLONIE = 1;
    public static final int POINTS_VICTOIRE_ROUTE = 0;

    public static final int NUMERO_STRUCTURE_MIN = 1;
    public static final int NUMERO_STRUCTURE_MAX = 3;
    public static final int TAILLE_TABLEAU_STRUCTURE = NUMERO_STRUCTURE_MAX + 1; // sert pour la taille de tableau, on ne travaille pas avec l'indice 0 du talbeau

    public static String nomStructure(int strucure) {
        String nom;
        switch (strucure) {
            case VILLE:
                nom = "ville";  break;
            case COLONIE:
                nom = "colonie";   break;
            case ROUTE:
                nom = "route"; break;
            default :
                nom = null; // ne doit pas se produire
        }
        return nom;
    }

    public static final int ROUTE_ORIENTATION_MIN = 1;
    public static final int ROUTE_ORIENTATION_MAX = 4;
    public static final int ROUTE_ORIENTATION_AUCUNE = 0; // n'est pas une route
    public static final int ROUTE_ORIENTATION_HORIZONTALE = 1;
    public static final int ROUTE_ORIENTATION_VERTICALE = 2;
    public static final int ROUTE_ORIENTATION_CROISSANTE = 3;
    public static final int ROUTE_ORIENTATION_DECROISSANTE = 4;

    /*
        Constantes des coûts de construction
     */
    // indice 0 non utilisé, bois, laine, blé, argile, minerai
    public static final int[] getCoutConstructionVille() {
        int[] tableauCoutConstructionVille = new int[TAILLE_TABLEAU_RESSOURCE];
        tableauCoutConstructionVille[BOIS] = 0;
        tableauCoutConstructionVille[LAINE] = 0;
        tableauCoutConstructionVille[BLE] = 2;
        tableauCoutConstructionVille[ARGILE] = 0;
        tableauCoutConstructionVille[MINERAI] = 3;
        return tableauCoutConstructionVille;
    }

    public static final int[] getCoutConstructionColonie() {
        int[] tableauCoutConstructionColonie = new int[TAILLE_TABLEAU_RESSOURCE];
        tableauCoutConstructionColonie[BOIS] = 1;
        tableauCoutConstructionColonie[LAINE] = 1;
        tableauCoutConstructionColonie[BLE] = 1;
        tableauCoutConstructionColonie[ARGILE] = 1;
        tableauCoutConstructionColonie[MINERAI] = 0;
        return tableauCoutConstructionColonie;
    }

    public static final int[] getCoutConstructionRoute() {
        int[] tableauCoutConstructionRoute = new int[TAILLE_TABLEAU_RESSOURCE];
        tableauCoutConstructionRoute[BOIS] = 1;
        tableauCoutConstructionRoute[LAINE] = 0;
        tableauCoutConstructionRoute[BLE] = 0;
        tableauCoutConstructionRoute[ARGILE] = 1;
        tableauCoutConstructionRoute[MINERAI] = 0;
        return tableauCoutConstructionRoute;
    }

    public static final int[] getCoutCarteDeveloppement() {
        int[] tableauCoutCarteDeveloppement = new int[TAILLE_TABLEAU_RESSOURCE];
        tableauCoutCarteDeveloppement[BOIS] = 0;
        tableauCoutCarteDeveloppement[LAINE] = 1;
        tableauCoutCarteDeveloppement[BLE] = 1;
        tableauCoutCarteDeveloppement[ARGILE] = 0;
        tableauCoutCarteDeveloppement[MINERAI] = 1;
        return tableauCoutCarteDeveloppement;
    }

    /*
        Chemin d'accès des images
     */
    public static final String CHEMIN_ACCES_JETONS = "textures/jetons/";
    public static final String CHEMIN_ACCES_MER = "textures/mer/";
    public static final String CHEMIN_ACCES_PIONS = "textures/pions/";
    public static final String CHEMIN_ACCES_PORTS = "textures/ports/";
    public static final String CHEMIN_ACCES_RESSOURCES = "textures/ressources/";
    public static final String CHEMIN_ACCES_TUILES = "textures/tuiles/";
    public static final String CHEMIN_ACCES_UI = "ui/";
    public static final String CHEMIN_ACCES_VOLEUR = "textures/pions/";

    public static final int DISTANCE_TUILE_X = 32;
    public static final int DISTANCE_SITE_CONSTRUCTION_X = 16;

    public static final int COULEUR_JEU = -1;
    public static final int COULEUR_ROUGE = 0;
    public static final int COULEUR_BLEU = 1;
    public static final int COULEUR_VERT = 2;
    public static final int COULEUR_JAUNE = 3;

    public static String couleurJoueur(int couleur) {
        String nom;
        switch (couleur) {
            case COULEUR_ROUGE:
                nom = "Rouge";  break;
            case COULEUR_BLEU:
                nom = "Bleu";   break;
            case COULEUR_VERT:
                nom = "Vert"; break;
            case COULEUR_JAUNE:
                nom = "Jaune"; break;
            default :
                nom = null; // ne doit pas se produire
        }
        return nom;
    }

    public static final int POINTS_VICTOIRE_MAX = 10;

    public static final int JETON_2 = 2;
    public static final int JETON_3 = 3;
    public static final int JETON_4 = 4;
    public static final int JETON_5 = 5;
    public static final int JETON_6 = 6;
    public static final int JETON_8 = 8;
    public static final int JETON_9 = 9;
    public static final int JETON_10 = 10;
    public static final int JETON_11 = 11;
    public static final int JETON_12 = 12;
}
