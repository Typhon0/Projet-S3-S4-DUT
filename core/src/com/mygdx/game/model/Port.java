package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Nico on 22-02-17.
 */

public class Port {

    private Vector2 position;
    private String specialite;
    private int tauxDeChange;
    private Texture texturePort;
    private SiteConstruction sc;
    // private PositionConstruction p1,p2; 2 sommets lié au port

    public Port(SiteConstruction sc,Vector2 position,String specialite,Integer tauxDeChange,Texture texturePort) {
        this.position = position;
        this.specialite = specialite;
        this.tauxDeChange = tauxDeChange;
        this.texturePort = texturePort;
        this.sc = sc;
    }

    public Port(Vector2 position,String specialite,Integer tauxDeChange,Texture texturePort) {
        this.position = position;
        this.specialite = specialite;
        this.tauxDeChange = tauxDeChange;
        this.texturePort = texturePort;
    }

    // Renvoie le pixel du coin inférieur gauche d'un carré intégré dans la tuile
    // Sert à dessiner le jeton
    public Vector2 getCoinInferieurGauchePort() { return new Vector2(position.x-32,position.y-32); }

    // Renvoie le pixel du coin supérieur droit d'un carré intégré dans la tuile
    // Sert à dessiner le jeton
    public Vector2 getCoinSuperieurDroitPort() { return new Vector2(position.x+32,position.y+32); }

    public boolean equals(Object o) {
        if (o instanceof Port) {
            Port p = (Port)o;
            return this.position.equals(p.position);
        }
        return false;
    }

    public String toString() {
        return position.toString()+", spécialité : "+specialite+", taux de change : "+tauxDeChange;
    }

    public SiteConstruction getSc() {
        return sc;
    }

    public int getTauxDeChange() {
        return tauxDeChange;
    }

    public Texture getTexturePort() {
        return texturePort;
    }
}
