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
    private int valeurPointVictoire; // Valeur en point victoire de la structure
    private SiteConstruction sc;
    private Texture texture;

    public Structure(Joueur joueur,SiteConstruction sc, Vector2 position, int typeStructure) {
        this.joueur = joueur;
        this.position = position;
        this.typeStructure = typeStructure;
        this.sc = sc;
        this.position.x = sc.getPosition().x;
        this.position.y = sc.getPosition().y;
        //this.texture = texture; charger la textrure par après

        affecterValeurPointVictoire();
        ajouterPointVictoire();
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

    public void affecterTexture() {
        switch (joueur.getCouleur()) {
            case Constantes.COULEUR_ROUGE :
                if (typeStructure == Constantes.VILLE) {
                    texture = Plateau.getVilleRouge();
                }
                else if (typeStructure == Constantes.COLONIE) {
                    texture = Plateau.getColonieRouge();
                }
                else {
                    texture = Plateau.getRouteRouge();
                }
                break;
            case Constantes.COULEUR_BLEU :
                if (typeStructure == Constantes.VILLE) {
                    texture = Plateau.getVilleBleu();
                }
                else if (typeStructure == Constantes.COLONIE) {
                    texture = Plateau.getColonieBleu();
                }
                else {
                    texture = Plateau.getRouteBleu();
                }
                break;
            case Constantes.COULEUR_VERT :
                if (typeStructure == Constantes.VILLE) {
                    texture = Plateau.getVilleVert();
                }
                else if (typeStructure == Constantes.COLONIE) {
                    texture = Plateau.getColonieVert();
                }
                else {
                    texture = Plateau.getRouteVert();
                }
                break;
            case Constantes.COULEUR_JAUNE :
                if (typeStructure == Constantes.VILLE) {
                    texture = Plateau.getVilleJaune();
                }
                else if (typeStructure == Constantes.COLONIE) {
                    texture = Plateau.getColonieJaune();
                }
                else {
                    texture = Plateau.getRouteJaune();
                }
                break;

            default:;
        }

    }

    //
    public void ajouterPointVictoire() {
        joueur.ajouterPointVictoire( valeurPointVictoire );
    }


    // Getters & Setters


    public SiteConstruction getSc() {
        return sc;
    }

    public void setSc(SiteConstruction sc) {
        this.sc = sc;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getTypeStructure() {
        return typeStructure;
    }

    public void setTypeStructure(int typeStructure) {
        this.typeStructure = typeStructure;
    }

    public int getValeurPointVictoire() {
        return valeurPointVictoire;
    }

    public void setValeurPointVictoire(int valeurPointVictoire) {
        this.valeurPointVictoire = valeurPointVictoire;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
