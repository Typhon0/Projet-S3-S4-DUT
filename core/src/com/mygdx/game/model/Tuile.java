package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by XXIII on 24/01/2017.
 */

public class Tuile {
    private Vector2 center;
    private Vector2 point1;
    private Vector2 point2;
    private Vector2 point3;
    private Vector2 point4;
    private Vector2 point5;
    private Vector2 point6;
    private float taille;
    private float deltaX;
    private Jeton jeton;
    private ArrayList<Vector2> listeSommets;
    private String type;
    private Texture textureTuile;

    public Tuile(Vector2 center, float taille) {
        this.center = center;
        this.taille = taille;
        //this.type = "desert";
        this.jeton = new Jeton(3);
        this.deltaX = (float) ((Math.sqrt(3)* taille)/2);
        listeSommets = new ArrayList<Vector2>();
        ajouterSommet(this.center);
        genererSommets();
    }

    // Affecte à une tuile son type et sa texture
    public void affecterType(String s){
        this.setType(s);
        if (s.equals("foret"))
            textureTuile = Plateau.getFORET();
        else if (s.equals("pre"))
            textureTuile = Plateau.getPRE();
        else if (s.equals("champ"))
            textureTuile = Plateau.getCHAMP();
        else if (s.equals("colline"))
            textureTuile = Plateau.getCOLLINE();
        else if (s.equals("montagne"))
            textureTuile = Plateau.getMONTAGNE();
        else if (s.equals("desert"))
            textureTuile = Plateau.getDESERT();
        else {
            textureTuile = Plateau.getDESERT();
            System.out.println("Type incorrect");
        }
    }

    // Affect à la tuile un jeton et inversément
    public void affecterJeton(Jeton t) {
        this.setJeton(t);
        t.affecterTuile(this);
    }

    // Génère les points d'un hexagone sur pointe
    public void genererSommets() {
        float deltaX = (float) ((Math.sqrt(3)* taille)/2);
        Vector2 point1 = new Vector2(center.x,center.y+ taille);
        Vector2 point2 = new Vector2(center.x+deltaX,center.y+ taille /2);
        Vector2 point3 = new Vector2(center.x+deltaX,center.y- taille /2);
        Vector2 point4 = new Vector2(center.x,center.y- taille);
        Vector2 point5 = new Vector2(center.x-deltaX,center.y- taille /2);
        Vector2 point6 = new Vector2(center.x-deltaX,center.y+ taille /2);
        ajouterSommet(point1);
        ajouterSommet(point2);
        ajouterSommet(point3);
        ajouterSommet(point4);
        ajouterSommet(point5);
        ajouterSommet(point6);
    }

    public void ajouterSommet(Vector2 corner) {
        if (!listeSommets.contains(corner))
            listeSommets.add(corner);
    }

    public boolean equals(Object o) {
        if (o instanceof Tuile) {
//            System.out.println("dans instance of");
            Tuile ht = (Tuile)o;
            System.out.println("nous sommes identiques");
            return true;//this.center.equals(ht.center); //&& this.taille==ht.taille; // deux tuiles sont identiques si elles ont le meme centre et meme rayon
        }
        return false;

    }

    public String getCoordonnees() {
        String s = "Centre : "+center.x+","+center.y+"\n";
        for(int i=1; i<=6; i++) {
            s += "Point"+i+" : "+(int) listeSommets.get(i).x+","+(int) listeSommets.get(i).y+"\n";
        }
        return s;
    }

    public float[] getVertices() {
        float[] tab = {
            listeSommets.get(1).x, listeSommets.get(1).y,
            listeSommets.get(2).x, listeSommets.get(2).y,
            listeSommets.get(3).x, listeSommets.get(3).y,
            listeSommets.get(4).x, listeSommets.get(4).y,
            listeSommets.get(5).x, listeSommets.get(5).y,
            listeSommets.get(6).x, listeSommets.get(6).y,
        };
        return tab;
    }

    // Renvoie le pixel du coin inférieur gauche de la tuile
    // Sert à dessiner la texture
    public Vector2 getCoinInferieurGaucheTuile() { return new Vector2(center.x-deltaX,center.y-taille); }

    // Renvoie le pixel du coin supérieur droit de la tuile
    // Sert à dessiner la texture
    public Vector2 getCoinSuperieurDroitTuile() { return new Vector2(center.x+deltaX,center.y+taille); }

    // Renvoie le pixel du coin inférieur gauche d'un carré intégré dans la tuile
    // Sert à dessiner le jeton
    public Vector2 getCoinInferieurGaucheJeton() { return new Vector2(center.x-32,center.y-32); }

    // Renvoie le pixel du coin supérieur droit d'un carré intégré dans la tuile
    // Sert à dessiner le jeton
    public Vector2 getCoinSuperieurDroitJeton() { return new Vector2(center.x+32,center.y+32); }

    // Getter & Setter
    public void setCentre(Vector2 centre) {
        this.center = centre;
    }

    public void setPoint1(Vector2 point1) {
        this.point1 = point1;
    }

    public void setPoint2(Vector2 point2) {
        this.point2 = point2;
    }

    public void setPoint3(Vector2 point3) {
        this.point3 = point3;
    }

    public void setPoint4(Vector2 point4) {
        this.point4 = point4;
    }

    public void setPoint5(Vector2 point5) {
        this.point5 = point5;
    }

    public void setPoint6(Vector2 point6) {
        this.point6 = point6;
    }

    public void setJeton(Jeton jeton) {
        this.jeton = jeton;
    }

    public Vector2 getCentre() {
        return center;
    }

    public Vector2 getPoint1() {
        return point1;
    }

    public Vector2 getPoint2() {
        return point2;
    }

    public Vector2 getPoint3() {
        return point3;
    }

    public Vector2 getPoint4() {
        return point4;
    }

    public Vector2 getPoint5() {
        return point5;
    }

    public Vector2 getPoint6() {
        return point6;
    }

    public Jeton getJeton() {
        return jeton;
    }

    public Vector2 getCenter() {
        return center;
    }

    public float getTaille() {
        return taille;
    }

    public ArrayList<Vector2> getListeSommets() {
        return listeSommets;
    }

    public Texture getTextureTuile() {
        return textureTuile;
    }

    public void setCenter(Vector2 center) {
        this.center = center;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public void setListeSommets(ArrayList<Vector2> listeSommets) {
        this.listeSommets = listeSommets;
    }

    public void setTextureTuile(Texture textureTuile) {
        this.textureTuile = textureTuile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public float getDeltaX() {
        return deltaX;
    }

}
