package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by XXIII on 24/01/2017.
 */

public class HexTile {
    /*
    public static final Texture
            FORET = new Texture(Gdx.files.internal("textures/texture_foret.png")),
            PRE = new Texture(Gdx.files.internal("textures/texture_pre.png")) ,
            CHAMP = new Texture(Gdx.files.internal("textures/texture_champ.png")),
            COLLINE = new Texture(Gdx.files.internal("textures/texture_colline.png")) ,
            MONTAGNE = new Texture(Gdx.files.internal("textures/texture_montagne.png")),
            DESERT = new Texture(Gdx.files.internal("textures/texture_desert.png"));
            */
    private Vector2 center;
    private Vector2 point1;
    private Vector2 point2;
    private Vector2 point3;
    private Vector2 point4;
    private Vector2 point5;
    private Vector2 point6;
    private float size;
    private float deltaX;
    private Jeton jeton;
    private ArrayList<Vector2>corners;
    private String type;


    private Texture textureTerrain;
//    private TextureRegion textureRegion;


    public HexTile(Vector2 center,float size) {
        this.center = center;
        this.size = size;
        this.type = "desert";
        this.jeton = new Jeton(3);
        this.deltaX = (float) ((Math.sqrt(3)*size)/2);
        corners = new ArrayList<Vector2>();
        addCorner(this.center);
        //textureTerrain = new Texture(Gdx.files.internal("t2.png"));
        generateCorners();
    }

    public void affectType(String s){
        this.setType(s);
        if (s.equals("foret"))
            textureTerrain = Board.getFORET();
        else if (s.equals("pre"))
            textureTerrain = Board.getPRE();
        else if (s.equals("champ"))
            textureTerrain = Board.getCHAMP();
        else if (s.equals("colline"))
            textureTerrain = Board.getCOLLINE();
        else if (s.equals("montagne"))
            textureTerrain = Board.getMONTAGNE();
        else if (s.equals("desert"))
            textureTerrain = Board.getDESERT();
        else {
            textureTerrain = Board.getDESERT();
            System.out.println("Type incorrect");
        }
    }

    public void affectToken(Jeton t) {
        this.setJeton(t);
        t.affecterTuile(this);
    }

    // Renvoie le pixel le plus en bas à gauche
    public Vector2 getBottomLeftCorner() {
        return new Vector2(center.x-deltaX,center.y-size);
    }

    public Vector2 getBottomLeftCorner2() { return new Vector2(center.x-(32),center.y-32);}

    // Génère les points d'un hexagone sur pointe
    public void generateCorners() {
        float deltaX = (float) ((Math.sqrt(3)*size)/2);
        Vector2 point1 = new Vector2(center.x,center.y+size);
        Vector2 point2 = new Vector2(center.x+deltaX,center.y+size/2);
        Vector2 point3 = new Vector2(center.x+deltaX,center.y-size/2);
        Vector2 point4 = new Vector2(center.x,center.y-size);
        Vector2 point5 = new Vector2(center.x-deltaX,center.y-size/2);
        Vector2 point6 = new Vector2(center.x-deltaX,center.y+size/2);
        addCorner(point1);
        addCorner(point2);
        addCorner(point3);
        addCorner(point4);
        addCorner(point5);
        addCorner(point6);
    }

    public void addCorner(Vector2 corner) {
        if (!corners.contains(corner))
            corners.add(corner);
    }

    public boolean equals(Object o) {
        if (o instanceof HexTile) {
//            System.out.println("dans instance of");
            HexTile ht = (HexTile)o;
            System.out.println("nous sommes identiques");
            return true;//this.center.equals(ht.center); //&& this.size==ht.size; // deux tuiles sont identiques si elles ont le meme centre et meme rayon
        }
        return false;

    }

    public String getCoordonnees() {
        String s = "Centre : "+center.x+","+center.y+"\n";
        for(int i=1; i<=6; i++) {
            s += "Point"+i+" : "+(int)corners.get(i).x+","+(int)corners.get(i).y+"\n";
        }
        return s;
    }

    public float[] getVertices() {
        float[] tab = {
            corners.get(1).x,corners.get(1).y,
            corners.get(2).x,corners.get(2).y,
            corners.get(3).x,corners.get(3).y,
            corners.get(4).x,corners.get(4).y,
            corners.get(5).x,corners.get(5).y,
            corners.get(6).x,corners.get(6).y,
        };
        return tab;
    }

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

//    public TextureRegion getTextureRegion() {
//        return textureRegion;
//    }

    public Vector2 getCenter() {
        return center;
    }

    public float getSize() {
        return size;
    }

    public ArrayList<Vector2> getCorners() {
        return corners;
    }

    public Texture getTextureTerrain() {
        return textureTerrain;
    }

    public void setCenter(Vector2 center) {
        this.center = center;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setCorners(ArrayList<Vector2> corners) {
        this.corners = corners;
    }

    public void setTextureTerrain(Texture textureTerrain) {
        this.textureTerrain = textureTerrain;
    }

//    public void setTextureRegion(TextureRegion textureRegion) {
//        this.textureRegion = textureRegion;
//    }

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
