package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by robrinne on 18/03/2017.
 */

public class Voleur {
    private Tuile tuile;
    private boolean actif;
    private Texture texture;

    public Voleur() {
        texture = Plateau.getVOLEUR();
        actif = false;
    }

    public Voleur(Tuile t) {
        this.tuile = t;
        texture = Plateau.getVOLEUR();
        actif = false;
    }

    public void setTuile(Tuile t) {
        this.tuile = t;
    }

    // Verifie si le voleur est a la tuile placee en parametre
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

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}

