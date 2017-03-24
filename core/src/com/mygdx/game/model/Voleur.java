package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screen.GameScreen;

/**
 * <b> Voleur est la classe représentant le voleur du jeu</b>
 * <p>
 * Un Voleur est caractérisé par les informations suivantes :
 * <ul>
 * <li>La tuile</li>
 * <li>l'activité du voleur</li>
 * <li>La texture du voleur</li>
 * </ul>
 * </p>
 *
 * @see Tuile
 * @see Texture
 */

public class Voleur {
    private Tuile tuile;
    private boolean actif;
    private Texture texture;

    /**
     * Constructeur qui initialise la texture du Voleur. Rend le voleur inactif
     */
    public Voleur() {
        texture = Plateau.getVOLEUR();
        actif = false;
    }

    public void setTuile(Tuile t) {
        this.tuile = t;
    }

    /**
     * Verifie si le voleur est a la tuile placee en parametre
     *
     * @param t Tuile
     * @return
     */
    public boolean tuileVoleur(Tuile t) {
        boolean b = false;

        if (this.getTuile() == t) {
            b = true;
        }

        return b;
    }

    public Tuile getTuile() {
        return this.tuile;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean isActif() {
        return actif;
    }

    /**
     * Active ou désactive le voleur
     * @param actif activité du voleur
     */
    public void setActif(boolean actif) {
        this.actif = actif;
        if (this.actif == true)
            Partie.getHud().afficherMessage("Voleur actif", "Vous devez choisir une tuile ou placer le voleur");
    }
}

