package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

/**
 * Created by XXIII on 23/01/2017.
 */

public class Board {

    public static final int HEXTILE_SIZE = Gdx.graphics.getHeight()/10; // pourquoi 100 ??

    private ArrayList<HexTile> hextiles; // liste des tuiles
    private StackNonRedimensionnable<String> stackTerrains; // stack de type de tuile
    private StackNonRedimensionnable<Token> stackTokens;

    private Texture textureMer;

    public Board() {
        hextiles = new ArrayList<HexTile>();
        stackTerrains = new StackNonRedimensionnable<String>(19);
        stackTokens = new StackNonRedimensionnable<Token>(18);
        textureMer = new Texture(Gdx.files.internal("texture_sea3.jpg"));
        generate();
    }

    public void generate() {
        generateHexTiles();
        generateStackTerrains();
        affectType();
        generateStackTokens();
        affectTokens();
    }


    private void generateHexTiles() {
        Vector2 center = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        Vector2 activeCenter = new Vector2(center);
        int size = HEXTILE_SIZE;
        float deltaX = (float) ((Math.sqrt(3)*size)/2);

        // Première ligne
        hextiles.add(new HexTile(new Vector2(center.x-2*deltaX,center.y+3*size),size));
        hextiles.add(new HexTile(new Vector2(center.x,center.y+3*size),size));
        hextiles.add(new HexTile(new Vector2(center.x+2*deltaX,center.y+3*size),size));

        // Deuxième ligne
        hextiles.add(new HexTile(new Vector2(center.x-3*deltaX,center.y+1.5f*size),size));
        hextiles.add(new HexTile(new Vector2(center.x-1*deltaX,center.y+1.5f*size),size));
        hextiles.add(new HexTile(new Vector2(center.x+1*deltaX,center.y+1.5f*size),size));
        hextiles.add(new HexTile(new Vector2(center.x+3*deltaX,center.y+1.5f*size),size));

        // Troisième ligne
        hextiles.add(new HexTile(new Vector2(center.x-4*deltaX,center.y),size));
        hextiles.add(new HexTile(new Vector2(center.x-2*deltaX,center.y),size));
        hextiles.add(new HexTile(new Vector2(center.x,center.y),size));
        hextiles.add(new HexTile(new Vector2(center.x+2*deltaX,center.y),size));
        hextiles.add(new HexTile(new Vector2(center.x+4*deltaX,center.y),size));

        // Quatrième ligne
        hextiles.add(new HexTile(new Vector2(center.x-3*deltaX,center.y-1.5f*size),size));
        hextiles.add(new HexTile(new Vector2(center.x-1*deltaX,center.y-1.5f*size),size));
        hextiles.add(new HexTile(new Vector2(center.x+1*deltaX,center.y-1.5f*size),size));
        hextiles.add(new HexTile(new Vector2(center.x+3*deltaX,center.y-1.5f*size),size));

        // Cinquième ligne
        hextiles.add(new HexTile(new Vector2(center.x-2*deltaX,center.y-3*size),size));
        hextiles.add(new HexTile(new Vector2(center.x,center.y-3*size),size));
        hextiles.add(new HexTile(new Vector2(center.x+2*deltaX,center.y-3*size),size));
    }

    private void generateStackTerrains() {
        for (int i=0 ; i<4 ; i++) {
            stackTerrains.push("foret");
            stackTerrains.push("pre");
            stackTerrains.push("champ");
            if (i>0) {
                stackTerrains.push("colline");
                stackTerrains.push("montagne");
            }
        }
        stackTerrains.push("desert");
        System.out.println(stackTerrains);
        stackTerrains.shuffle();
        System.out.println(stackTerrains);
    }

    private void affectType() {
        for (HexTile h : hextiles) {
            h.affectType(stackTerrains.pop());
        }
    }

    private void generateStackTokens() {
        for (int i=0 ; i<2 ; i++) {
            stackTokens.push(new Token(3));
            stackTokens.push(new Token(4));
            stackTokens.push(new Token(5));
            stackTokens.push(new Token(6));
            stackTokens.push(new Token(8));
            stackTokens.push(new Token(9));
            stackTokens.push(new Token(10));
            stackTokens.push(new Token(11));
        }
        stackTokens.push(new Token(2));
        stackTokens.push(new Token(12));
        System.out.println(stackTokens);
        stackTokens.shuffle();
        System.out.println(stackTokens);
    }

    private void affectTokens() {
        for (HexTile h : hextiles) {
            if (!h.getType().equals("desert"))
                h.affectToken(stackTokens.pop());
        }
    }

    /* La fonction ne marche pas ??????
    public void addHextile(HexTile h) {
        if (!hextiles.contains(h)) {
            hextiles.add(h);
            System.out.println("ajouté");
        }
        else
            System.out.println("non ajouté");
    }
    */


    public ArrayList<HexTile> getHextiles() {
        return hextiles;
    }

    public Texture getTextureMer() {
        return textureMer;
    }
/*
    // Crée les sommets d'un hexagone suivant son centre
    public static FloatArray creerSommets(Vector2 center) {
        FloatArray sommets = new FloatArray(new float[] {

                center.x,center.y+DX,
                center.x+DX,center.y+DY,
                center.x+DX,center.y-DY,
                center.x,center.y-DX,
                center.x-DX,center.y-DY,
                center.x-DX,center.y+DY
        });
        return sommets;
    }
*/



}
