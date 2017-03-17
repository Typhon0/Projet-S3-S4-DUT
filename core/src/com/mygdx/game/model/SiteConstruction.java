package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;

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

    public SiteConstruction(Vector2 position) {
        this.position = position;
        this.estConstruit = false;
        this.structure = null;
        listeTuilesAdjacentes = new ArrayList<Tuile>();
    }

    // Deux sites de construction sont identiques s'il la
    public boolean equals(Object o) {
        if (o instanceof SiteConstruction) {
            SiteConstruction sc = (SiteConstruction) o;
            return ( Math.abs(this.position.x - sc.position.x) <= 3.0 && Math.abs(this.position.y - sc.position.y) <= 3.0);
                    /*
            if ((int)this.position.x==(int)sc.position.x && (int)this.position.y==(int)sc.position.y) {
                //System.out.println("trouvé des sites identiques");
                return true;
            }
            */
        }
        return false;

    }

    public void supprimerDoublon(SiteConstruction sc) {
        //this = sc;
    }

    public boolean estToucheVector2(Vector2 pixel) {
        if (pixel.x >= position.x-Constantes.STRUCTURE_DELTA_X && pixel.x <= position.x+Constantes.STRUCTURE_DELTA_X &&
                pixel.y >= position.y-Constantes.STRUCTURE_DELTA_Y && pixel.y <= position.y+Constantes.STRUCTURE_DELTA_Y) {
            System.out.println("Le site de construction "+position.x+","+position.y+" a été touché");
            return true;
        }
        return false;
    }

    public boolean estToucheInt(int x,int y) {
        if (x >= position.x-Constantes.STRUCTURE_DELTA_X && x <= position.x+Constantes.STRUCTURE_DELTA_X &&
                y >= position.y-Constantes.STRUCTURE_DELTA_Y && y <= position.y+Constantes.STRUCTURE_DELTA_Y) {
            System.out.println("Le site de construction "+this.toString()+" "+position.x+","+position.y+" a été touché");
            return true;
        }
        return false;
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
}
