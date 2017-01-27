package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Stack;

/**
 * Created by XXIII on 23/01/2017.
 */

public class Token {
    public static final Texture
            TWO = new Texture(Gdx.files.internal("token_two.png")),
            THREE = new Texture(Gdx.files.internal("token_three.png")) ,
            FOUR = new Texture(Gdx.files.internal("token_four.png")),
            FIVE = new Texture(Gdx.files.internal("token_five.png")) ,
            SIX = new Texture(Gdx.files.internal("token_six.png")),
            EIGHT = new Texture(Gdx.files.internal("token_eight.png")),
            NINE = new Texture(Gdx.files.internal("token_nine.png")),
            TEN = new Texture(Gdx.files.internal("token_ten.png")) ,
            ELEVEN = new Texture(Gdx.files.internal("token_eleven.png")),
            TWELVE = new Texture(Gdx.files.internal("token_twelve.png")) ;
    private Integer value;
    private HexTile hextile;
    private Texture textureToken;

    public Token(Integer value) {
        affectValue(value);
    }

    public void affectHextile(HexTile h) {
        this.setHextile(h);
    }

    public void affectValue(Integer i){
        this.setValue(i);
        if (i==2)
            textureToken = TWO;
        else if (i==3)
            textureToken = THREE;
        else if (i==4)
            textureToken = FOUR;
        else if (i==5)
            textureToken = FIVE;
        else if (i==6)
            textureToken = SIX;
        else if (i==8)
            textureToken = EIGHT;
        else if (i==9)
            textureToken = NINE;
        else if (i==10)
            textureToken = TEN;
        else if (i==11)
            textureToken = ELEVEN;
        else if (i==12)
            textureToken = TWELVE;
        else {
            textureToken = null;
            System.err.println("Valeur du jeton incorrecte");
        }
    }

    public void produire() {

    }

    public void setHextile(HexTile hextile) {
        this.hextile = hextile;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Texture getTextureToken() {
        return textureToken;
    }

    public Integer getValue() {
        return value;
    }

    public HexTile getHextile() {
        return hextile;
    }
}
