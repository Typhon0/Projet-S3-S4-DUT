package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import javax.swing.BorderFactory;

/**
 * Created by XXIII on 23/01/2017.
 */

public class Jeton {
    // Texture de chaque Jeton
    /*
    public static final Texture
            JETON_2 = new Texture(Gdx.files.internal("textures/jeton_2.png")),
            JETON_3 = new Texture(Gdx.files.internal("textures/jeton_3.png")),
            JETON_4 = new Texture(Gdx.files.internal("textures/jeton_4.png")),
            JETON_5 = new Texture(Gdx.files.internal("textures/jeton_5.png")),
            JETON_6 = new Texture(Gdx.files.internal("textures/jeton_6.png")),
            JETON_8 = new Texture(Gdx.files.internal("textures/jeton_8.png")),
            JETON_9 = new Texture(Gdx.files.internal("textures/jeton_9.png")),
            JETON_10 = new Texture(Gdx.files.internal("textures/jeton_10.png")),
            JETON_11 = new Texture(Gdx.files.internal("textures/jeton_11.png")),
            JETON_12 = new Texture(Gdx.files.internal("textures/jeton_12.png")) ;
*/
    private Integer valeur; // valeur du jeton
    private HexTile tuile; // la tuile associée à chaque jeton
    private Texture textureJeton;

    public Jeton(Integer valeur) {
        affecterValeur(valeur);
    }

    public void affecterTuile(HexTile h) {
        this.setTuile(h);
    }

    public void affecterValeur(Integer valeur){
        this.setValeur(valeur);
        if (valeur==2)
            textureJeton = Board.getJeton2();
        else if (valeur==3)
            textureJeton = Board.getJeton3();
        else if (valeur==4)
            textureJeton = Board.getJeton4();
        else if (valeur==5)
            textureJeton = Board.getJeton5();
        else if (valeur==6)
            textureJeton = Board.getJeton6();
        else if (valeur==8)
            textureJeton = Board.getJeton8();
        else if (valeur==9)
            textureJeton = Board.getJeton9();
        else if (valeur==10)
            textureJeton = Board.getJeton10();
        else if (valeur==11)
            textureJeton = Board.getJeton11();
        else if (valeur==12)
            textureJeton = Board.getJeton12();
        else {
            textureJeton = null;
            System.err.println("Valeur du jeton incorrecte");
        }
    }

    public void produire() {

    }

    public String toString() {
        return valeur.toString();
    }
    // Getters & Setters

    public void setTuile(HexTile tuile) {
        this.tuile = tuile;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public Texture getTextureJeton() {
        return textureJeton;
    }

    public Integer getValeur() {
        return valeur;
    }

    public HexTile getTuile() {
        return tuile;
    }
}