package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * <b> Structure est la classe représentant une structure qui peut être construite par le joueur (ville,colonie ou route). Une structure se place sur un site de construction</b>
 * <p>
 * Une Structure  est caractérisé par les informations suivantes :
 * <ul>
 * <li>Le joueur qui l'a construit</li>
 * <li>La position de la structure</li>
 * <li>Le type de structure dont il s'agit</li>
 * <li>Le valeur de points victoire que rapporte la structure/li>
 * <li>Le site de construction de la structure</li>
 * <li>La texture de la structure/li>
 * </ul>
 * </p>
 *
 * @author Geris Nicolas
 * @version 1.0
 * @see Joueur
 * @see Vector2
 * @see SiteConstruction
 * @see Texture
 */

// Un structure correspond à soit à une ville, colonie ou route
public class Structure {

    /**
     * Joueur possédant la structure
     */
    private Joueur joueur;

    /**
     * Position de la structure
     */
    private Vector2 position;

    /**
     * Type de la structure.
     *
     * @see Constantes
     */
    private int typeStructure; // type de la structure

    /**
     * Valeur points victoire de la strucure
     *
     * @see Constantes
     */
    private int valeurPointVictoire;

    /**
     * Site de construction qui accueille la structure
     */
    private SiteConstruction sc;

    /**
     * Texture de la structure
     */
    private Texture texture;

    /**
     * Constructeur
     *
     * @param joueur        Joueur qui construit la structure
     * @param sc            Site de construction qui accueille la stucture
     * @param position      Position (Vector2) de la structure
     * @param typeStructure Type de la structure (ville, colonie ou route)
     */
    public Structure(Joueur joueur, SiteConstruction sc, Vector2 position, int typeStructure) {
        this.joueur = joueur;
        this.position = position;
        this.typeStructure = typeStructure;
        this.sc = sc;
        this.position.x = sc.getPosition().x;
        this.position.y = sc.getPosition().y;

        affecterValeurPointVictoire();
        ajouterPointVictoire();
    }

    /**
     * Affecte la valeur de points victoire de la structure en fonction du type de structure dont il s'agit
     */
    public void affecterValeurPointVictoire() {
        if (typeStructure >= Constantes.NUMERO_STRUCTURE_MIN && typeStructure <= Constantes.NUMERO_STRUCTURE_MAX) {
            switch (typeStructure) {
                case Constantes.VILLE:
                    valeurPointVictoire = Constantes.POINTS_VICTOIRE_VILLE;
                    break;
                case Constantes.COLONIE:
                    valeurPointVictoire = Constantes.POINTS_VICTOIRE_COLONIE;
                    break;
                case Constantes.ROUTE:
                    valeurPointVictoire = Constantes.POINTS_VICTOIRE_ROUTE;
                    break;
            }
        }
    }

    /**
     * Affecte la texture à la structure selon la couleur du joueur et le type de structure dont il s'agit
     */
    public void affecterTexture() {
        switch (joueur.getCouleur()) {
            case Constantes.COULEUR_ROUGE:
                if (typeStructure == Constantes.VILLE) {
                    texture = Plateau.getVilleRouge();
                } else if (typeStructure == Constantes.COLONIE) {
                    texture = Plateau.getColonieRouge();
                } else {
                    texture = Plateau.getRouteRouge();
                }
                break;
            case Constantes.COULEUR_BLEU:
                if (typeStructure == Constantes.VILLE) {
                    texture = Plateau.getVilleBleu();
                } else if (typeStructure == Constantes.COLONIE) {
                    texture = Plateau.getColonieBleu();
                } else {
                    texture = Plateau.getRouteBleu();
                }
                break;
            case Constantes.COULEUR_VERT:
                if (typeStructure == Constantes.VILLE) {
                    texture = Plateau.getVilleVert();
                } else if (typeStructure == Constantes.COLONIE) {
                    texture = Plateau.getColonieVert();
                } else {
                    texture = Plateau.getRouteVert();
                }
                break;
            case Constantes.COULEUR_JAUNE:
                if (typeStructure == Constantes.VILLE) {
                    texture = Plateau.getVilleJaune();
                } else if (typeStructure == Constantes.COLONIE) {
                    texture = Plateau.getColonieJaune();
                } else {
                    texture = Plateau.getRouteJaune();
                }
                break;
            default:
                ;
        }
    }

    /**
     * Incrémente le nombre de points victoire par la valeur de la structure
     */
    public void ajouterPointVictoire() {
        joueur.ajouterPointVictoire( valeurPointVictoire );
    }


    // Getters & Setters

    public SiteConstruction getSc() {
        return sc;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public int getTypeStructure() {
        return typeStructure;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
