package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * <b> SiteConstruction est la classe représentant une site de construction sur une Tuile. Le site de construction est l'emplacement où l'on peut construire une structure</b>
 * <p>
 * Un SiteConstruction  est caractérisé par les informations suivantes :
 * <ul>
 * <li>Sa position</li>
 * <li>La liste des tuiles adjacentes </li>
 * <li>La structure qu'elle peut accueillir</li>
 * <li>Un boolean permettant de savoir si le site est construit ou non </li>
 * <li>Un boolean permettant de savoir si le site de construction est un batiment ou une route</li>
 * </ul>
 * </p>
 *
 * @author Geris Nicolas
 * @version 1.0
 * @see Vector2
 * @see Tuile
 * @see Structure
 */

public class SiteConstruction {

    /**
     * Position du site de construction
     */
    private Vector2 position;

    /**
     * Liste des tuiles adjacentes du site de construction
     */
    private ArrayList<Tuile> listeTuilesAdjacentes;

    /**
     * La structure que peut accueillir le site de construction
     */
    private Structure structure;

    /**
     * Un boolean définissant si le site de construction est construit ou non
     */
    private boolean estConstruit;

    /**
     * Un boolean définissant si le site de construction est un batiment ou une route
     */
    private boolean estBatiment;

    /**
     * Constructeur initialisant le Site de construction
     *
     * @param position    position du site de construction
     * @param estBatiment boolean permettant de savoir si le site de construction est un batiment ou une route
     */
    public SiteConstruction(Vector2 position, boolean estBatiment) {
        this.estBatiment = estBatiment;
        this.position = position;
        this.estConstruit = false;
        this.structure = null;
        listeTuilesAdjacentes = new ArrayList<Tuile>();
    }

    /**
     * Méthode equals(Object o) de la classe. Deux sites de constructions sont identiques si la distance entre les deux inférieurs à 5 pixels
     *
     * @param o Object o passé en argument pour comparer
     * @return boolean pour savoir si les sites de constructions sont identiques ou non
     */
    public boolean equals(Object o) {
        if (o instanceof SiteConstruction) {
            SiteConstruction sc = (SiteConstruction) o;
            return ((Math.abs( this.position.x - sc.position.x ) <= 5.0 && Math.abs( this.position.y - sc.position.y ) <= 5.0) && sc.estBatiment == this.estBatiment);
        }
        return false;
    }

    /**
     * Methode vérifiant si les coordonnées du pixel touché correspondant au site de construction
     *
     * @param x coordonnée x du pixel touché
     * @param y coordonnée y du pixel touché
     * @return boolean si le site de construction est touché ou non
     */
    public boolean estToucheInt(int x, int y) {
        if (x >= position.x - Constantes.STRUCTURE_DELTA_X && x <= position.x + Constantes.STRUCTURE_DELTA_X &&
                y >= position.y - Constantes.STRUCTURE_DELTA_Y && y <= position.y + Constantes.STRUCTURE_DELTA_Y) {
//            String message = "";
//            if (estBatiment) {
//                message = "batiment";
//            }
//            else {
//                message = "route";
//            }
//            System.out.println("Le site de construction ("+message+") : "+this.toString()+" "+position.x+","+position.y+" a été touché");
            return true;
        }
        return false;
    }

    /**
     * Permet de connaitre le pixel d'origine pour afficher une structure à dessiner sur le site de construction.
     * Pour connaitre l'origine on va chercher le pixel se trouvant à gauche du site ce construction à une distance de Constantes.DISTANCE_SITE_CONSTRUCTION_X
     * De même pour y
     *
     * @return le pixel d'origine pour dessiner la texture
     */
    public Vector2 getCoinInferieurGaucheSiteConstruction() {
        return new Vector2( position.x - Constantes.DISTANCE_SITE_CONSTRUCTION_X, position.y - Constantes.DISTANCE_SITE_CONSTRUCTION_X );
    }


    // Getters && Setters

    public Vector2 getPosition() {
        return position;
    }

    public ArrayList<Tuile> getListeTuilesAdjacentes() {
        return listeTuilesAdjacentes;
    }

    public Structure getStructure() {
        return structure;
    }

    public boolean isEstConstruit() {
        return estConstruit;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public void setEstConstruit(boolean estConstruit) {
        this.estConstruit = estConstruit;
    }

    public boolean isEstBatiment() {
        return estBatiment;
    }

}
