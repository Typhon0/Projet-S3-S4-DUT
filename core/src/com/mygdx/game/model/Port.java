package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * <b> Port est la classe représentant un port du jeu</b>
 * <p>
 * Un Port  est caractérisé par les informations suivantes :
 * <ul>
 * <li>La position</li>
 * <li>Sa spécialité</li>
 * <li>Le taux de change</li>
 * <li>La texture du port</li>
 * </ul>
 * </p>
 */

public class Port {

    /**
     * Position du port
     */
    private Vector2 position;

    /**
     * Spécialité du port qui permet un taux de change plus faible que la normale
     */
    private String specialite;

    /**
     * Taux de change du port. Ex : 3 pour 1 => le joueur doit donner 3 fois plus de ressources en échange de ce qu'il demande
     */
    private int tauxDeChange;

    /**
     * Texture du port
     */
    private Texture texturePort;

    /**
     *
     * @param position Position du port
     * @param specialite spécialité de ressources du port
     * @param tauxDeChange taux de change du port
     * @param texturePort texture du port
     */
    public Port(Vector2 position,String specialite,Integer tauxDeChange,Texture texturePort) {
        this.position = position;
        this.specialite = specialite;
        this.tauxDeChange = tauxDeChange;
        this.texturePort = texturePort;
    }

    /**
     * Renvoie le pixel du coin inférieur gauche d'un carré intégré dans la tuile. Sert à dessiner le port
     * @return Pixel d'origine pour dessiner et étirer la texture
     */
    public Vector2 getCoinInferieurGauchePort() { return new Vector2(position.x-32,position.y-32); }

    // Renvoie le pixel du coin supérieur droit d'un carré intégré dans la tuile
    // Sert à dessiner le jeton
    public Vector2 getCoinSuperieurDroitPort() { return new Vector2(position.x+32,position.y+32); }

    /**
     * Deux ports sont identiques s'ils ont la même position
     * @param o Object à comparer
     * @return boolean s'ils sont identiques
     */
    public boolean equals(Object o) {
        if (o instanceof Port) {
            Port p = (Port)o;
            return this.position.equals(p.position);
        }
        return false;
    }

    /**
     * toString de la classe Port
     * @return String contenant la spécialité et le taux de change
     */
    public String toString() {
        return position.toString()+", spécialité : "+specialite+", taux de change : "+tauxDeChange;
    }


    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setTauxDeChange(int tauxDeChange) {
        this.tauxDeChange = tauxDeChange;
    }

    public void setTexturePort(Texture texturePort) {
        this.texturePort = texturePort;
    }

    public Vector2 getPosition() {
        return position;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getTauxDeChange() {
        return tauxDeChange;
    }

    public Texture getTexturePort() {
        return texturePort;
    }
}
