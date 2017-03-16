package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

/**
 * Created by Nico on 14/03/2017.
 */

// Un structure correspond à soit à une ville, colonie ou route
public class Structure {

    private Joueur joueur;
    private Vector2 position;

    //private float largeur; // largeur de l'image qu'il faut afficher pour la structure
    //private float hauteur; // hauteur de l'image qu'il faut afficher pour la structure
    private int typeStructure ; // type de la structure
    private int orientation; // orientation de la route
    private int valeurPointVictoire; // Valeur en point victoire de la structure
    private Texture texture;

    public Structure(Joueur joueur, Vector2 position, int typeStructure) {
        this.joueur = joueur;
        this.position = position;
        this.typeStructure = typeStructure;
        //this.texture = texture; charger la textrure par après

        affecterValeurPointVictoire();
        //ajouterPointVictoire(joueur);
    }

    // Affecte une valeur de point victoire selon le type de structure
    public void affecterValeurPointVictoire() {
        if (typeStructure >= Constantes.NUMERO_STRUCTURE_MIN && typeStructure <= Constantes.NUMERO_STRUCTURE_MAX) {
            switch (typeStructure) {
                case Constantes.VILLE :
                    valeurPointVictoire = Constantes.POINTS_VICTOIRE_VILLE; break;
                case Constantes.COLONIE :
                    valeurPointVictoire = Constantes.POINTS_VICTOIRE_COLONIE; break;
                case Constantes.ROUTE :
                    valeurPointVictoire = Constantes.POINTS_VICTOIRE_ROUTE; break;
            }
        }
    }
/*
    // Ajoute les points de victoire au joueur
    public void ajouterPointVictoire(Joueur j) {
        try {
            if (j != null)
                j.ajouterPointVictoire(valeurPointVictoire);
        }
        catch (Exception e) {
            throw new RuntimeException("Erreur : ajouterPointVictoire(), le joueur est nul");
        }
    }
*/
    public void ajouterStructure(Joueur j) {

    }

}
