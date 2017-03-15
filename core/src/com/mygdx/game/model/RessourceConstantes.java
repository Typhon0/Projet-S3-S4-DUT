package com.mygdx.game.model;

/**
 * Created by XXIII on 15/03/2017.
 */

public class RessourceConstantes {
    public static final int MONTANT_RESSOURCE_MAXIMUM = 19; // montant maximum de ressources par type

    public static final int BOIS = 1;
    public static final int LAINE = 2;
    public static final int BLE = 3;
    public static final int ARGILE = 4;
    public static final int MINERAI = 5;

    public static final int NUMERO_RESSOURCE_MIN = 1;
    public static final int NUMERO_RESSOURCE_MAX = 5;
    public static final int TAILLE_TABLEAU = NUMERO_RESSOURCE_MAX + 1; // sert pour la taille de tableau, on ne travaille pas avec l'indice 0 du talbeau

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

}
