package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * Created by Nico on 14/03/2017.
 */

public class SiteConstruction {
    //private float deltaX,deltaY;
    private Vector2 position;
    private ArrayList<Tuile> listeTuilesAdjacentes;
    private Structure structure;
    private boolean estConstruit;
    private boolean estBatiment; // true s'il s'agit d'une ville/colonie, false pour une route

    public SiteConstruction(Vector2 position) {
        this.estBatiment = true;
        this.position = position;
        this.estConstruit = false;
        this.structure = null;
        listeTuilesAdjacentes = new ArrayList<Tuile>();
    }

    public SiteConstruction(Vector2 position,boolean estBatiment) {
        this.estBatiment = estBatiment;
        this.position = position;
        this.estConstruit = false;
        this.structure = null;
        listeTuilesAdjacentes = new ArrayList<Tuile>();
    }

    // Deux sites de construction sont identiques s'il la distance entre eux est inférieur à 3 pixel en x et y
    public boolean equals(Object o) {
        if (o instanceof SiteConstruction) {
            SiteConstruction sc = (SiteConstruction) o;
            return ( (Math.abs(this.position.x - sc.position.x) <= 3.0 && Math.abs(this.position.y - sc.position.y) <= 3.0) && sc.estBatiment==this.estBatiment);
        }
        return false;
    }

    public boolean estToucheInt(int x,int y) {
        if (x >= position.x-Constantes.STRUCTURE_DELTA_X && x <= position.x+Constantes.STRUCTURE_DELTA_X &&
                y >= position.y-Constantes.STRUCTURE_DELTA_Y && y <= position.y+Constantes.STRUCTURE_DELTA_Y) {
            String message = "";
            if (estBatiment) {
                message = "batiment";
            }
            else {
                message = "route";
            }
            System.out.println("Le site de construction ("+message+") : "+this.toString()+" "+position.x+","+position.y+" a été touché");
            return true;
        }
        return false;
    }

    public String toString2() {
        try {
            String message = "";
            if (estBatiment) {
                message = "batiment";
            }
            else {
                message = "route";
            }
            return "ID : "+this.toString()+" ,Type : "+message+", ("+position.x+","+position.y+")";
        } catch (Exception e) {
            System.err.println(e.getMessage()+"//"+e.getLocalizedMessage()+"//"+e.getCause());
        }
        return "erreur";
    }

    public Vector2 getCoinInferieurGaucheSiteConstruction() {
        return new Vector2(position.x - Constantes.DISTANCE_SITE_CONSTRUCTION_X, position.y - Constantes.DISTANCE_SITE_CONSTRUCTION_X);
    }

    // Renvoie le pixel du coin supérieur droit d'un carré intégré dans la tuile
    // Sert à dessiner le jeton
    public Vector2 getCoinSuperieurDroitSiteConstruction() {
        return new Vector2(position.x + Constantes.DISTANCE_SITE_CONSTRUCTION_X, position.y + Constantes.DISTANCE_SITE_CONSTRUCTION_X);
    }
    /*
        TO DO
        - listeTuilesAdjacentes
     */

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

    public void setEstBatiment(boolean b) {
        this.estBatiment = b;
    }
}
