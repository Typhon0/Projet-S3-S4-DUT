package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

/**
 * Created by XXIII on 23/01/2017.
 */

public class Board {

    public static final int HEXTILE_SIZE = Gdx.graphics.getHeight()/10;
    private static Texture
            JETON_2,
            JETON_3,
            JETON_4,
            JETON_5 ,
            JETON_6,
            JETON_8,
            JETON_9,
            JETON_10,
            JETON_11,
            JETON_12,
            FORET,
            PRE,
            CHAMP,
            COLLINE,
            MONTAGNE,
            DESERT,
            MER;

    private ArrayList<HexTile> hextiles; // liste des tuiles
    private com.mygdx.game.autre.StackNonRedimensionnable<String> stackTerrains; // stack de type de tuile
    private com.mygdx.game.autre.StackNonRedimensionnable<Jeton> stackTokens; // stack de tokens
    private Texture textureMer;

    // Charge toutes les textures pour le plateau de jeu
    private void chargerTextures() {
        String messageErreur = "";
        try {
            JETON_2 = new Texture(Gdx.files.internal("textures/jeton_2.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_2.png"+"\n";
        }
        try {
            JETON_3 = new Texture(Gdx.files.internal("textures/jeton_3.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_3.png"+"\n";
        }
        try {
            JETON_4 = new Texture(Gdx.files.internal("textures/jeton_4.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_4.png"+"\n";
        }
        try {
            JETON_5 = new Texture(Gdx.files.internal("textures/jeton_5.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_5.png"+"\n";
        }
        try {
            JETON_6 = new Texture(Gdx.files.internal("textures/jeton_6.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_6.png"+"\n";
        }
        try {
            JETON_8 = new Texture(Gdx.files.internal("textures/jeton_8.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_8.png"+"\n";
        }
        try {
            JETON_9 = new Texture(Gdx.files.internal("textures/jeton_9.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_9.png"+"\n";
        }
        try {
            JETON_10 = new Texture(Gdx.files.internal("textures/jeton_10.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_10.png"+"\n";
        }
        try {
            JETON_11 = new Texture(Gdx.files.internal("textures/jeton_11.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_11.png"+"\n";
        }
        try {
            JETON_12 = new Texture(Gdx.files.internal("textures/jeton_12.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/jeton_12.png"+"\n";
        }
        try {
            FORET = new Texture(Gdx.files.internal("textures/texture_foret.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/texture_foret.png"+"\n";
        }
        try {
            PRE = new Texture(Gdx.files.internal("textures/texture_pre.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/texture_pre.png"+"\n";
        }
        try {
            CHAMP = new Texture(Gdx.files.internal("textures/texture_champ.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/texture_champ.png"+"\n";
        }
        try {
            COLLINE = new Texture(Gdx.files.internal("textures/texture_colline.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/texture_colline.png"+"\n";
        }
        try {
            MONTAGNE = new Texture(Gdx.files.internal("textures/texture_montagne.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/texture_montagne.png"+"\n";
        }
        try {
            DESERT = new Texture(Gdx.files.internal("textures/texture_desert.png"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/texture_desert.png"+"\n";
        }
        try {
            MER = new Texture(Gdx.files.internal("textures/texture_mer.jpg"));
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : "+"textures/texture_mer.jpg"+"\n";
        }
        System.err.println(messageErreur);
    }

    public Board() {
        chargerTextures();
        hextiles = new ArrayList<HexTile>();
        stackTerrains = new com.mygdx.game.autre.StackNonRedimensionnable<String>(19);
        stackTokens = new com.mygdx.game.autre.StackNonRedimensionnable<Jeton>(18);
        textureMer = MER;
        //generate();
    }

    // Génère le plateau de jeu ainsi que ses composants
    public void generate() {
        generateHexTiles();

        generateStackTerrains();
        affectType();

        generateStackTokens();
        affectTokens();
    }

    // Génère les tuiles
    private void generateHexTiles() {
        Vector2 center = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        //Vector2 activeCenter = new Vector2(center);
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

    // Génère le paquet de type de terrain
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

    // Affecte un type à chaque tuile en tirer un type dans le paquet de type
    private void affectType() {
        for (HexTile h : hextiles) {
            h.affectType(stackTerrains.pop());
        }
    }

    // Génère le paquet de type de jeton
    private void generateStackTokens() {
        for (int i=0 ; i<2 ; i++) {
            stackTokens.push(new Jeton(3));
            stackTokens.push(new Jeton(4));
            stackTokens.push(new Jeton(5));
            stackTokens.push(new Jeton(6));
            stackTokens.push(new Jeton(8));
            stackTokens.push(new Jeton(9));
            stackTokens.push(new Jeton(10));
            stackTokens.push(new Jeton(11));
        }
        stackTokens.push(new Jeton(2));
        stackTokens.push(new Jeton(12));
        System.out.println(stackTokens);
        stackTokens.shuffle();
        System.out.println(stackTokens);
    }

    // Affecte un jeton à chaque tuile excepté la tuile désert
    private void affectTokens() {
        for (HexTile h : hextiles) {
            if (!h.getType().equals("desert"))
                h.affectToken(stackTokens.pop());
        }
    }

    public ArrayList<HexTile> getHextiles() {
        return hextiles;
    }

    public Texture getTextureMer() {
        return textureMer;
    }

    public static Texture getJeton2() {
        return JETON_2;
    }

    public static Texture getJeton3() {
        return JETON_3;
    }

    public static Texture getJeton4() {
        return JETON_4;
    }

    public static Texture getJeton5() {
        return JETON_5;
    }

    public static Texture getJeton6() {
        return JETON_6;
    }

    public static Texture getJeton8() {
        return JETON_8;
    }

    public static Texture getJeton9() {
        return JETON_9;
    }

    public static Texture getJeton10() {
        return JETON_10;
    }

    public static Texture getJeton11() {
        return JETON_11;
    }

    public static Texture getJeton12() {
        return JETON_12;
    }

    public static Texture getFORET() {
        return FORET;
    }

    public static Texture getPRE() {
        return PRE;
    }

    public static Texture getCHAMP() {
        return CHAMP;
    }

    public static Texture getCOLLINE() {
        return COLLINE;
    }

    public static Texture getMONTAGNE() {
        return MONTAGNE;
    }

    public static Texture getDESERT() {
        return DESERT;
    }

    public static Texture getMER() {
        return MER;
    }
}
