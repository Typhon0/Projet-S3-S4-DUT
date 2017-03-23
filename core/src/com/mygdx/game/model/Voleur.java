package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by robrinne on 18/03/2017.
 */

public class Voleur
{
    private Tuile tuile;
    private Texture texture;
    private boolean actif;

    public Voleur()
    {
        this.actif = false;
        this.texture = Plateau.getVOLEUR();
    }
    public Voleur(Tuile t)
    {
        this.actif = false;
        this.texture = Plateau.getVOLEUR();
        this.tuile = t;
    }
    public Tuile getTuile()
    {
        return this.tuile;
    }
    public void setTuile(Tuile t)
    {
        this.tuile = t;
    }
    // Verifie si le voleur est a la tuile placee en parametre
    public boolean tuileVoleur(Tuile t)
    {
        boolean b = false;
        if(this.getTuile() == t)
        {
            b = true;
        }
        return b;
    }
    
    
}

