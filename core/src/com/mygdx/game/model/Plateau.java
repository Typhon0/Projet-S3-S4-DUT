package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

/**
 * Created by XXIII on 23/01/2017.
 */

public class Plateau {

    public static final int TAILLE_TUILE = Gdx.graphics.getHeight()/10;
    private static Texture JETON_2, JETON_3, JETON_4, JETON_5, JETON_6, JETON_8, JETON_9, JETON_10, JETON_11, JETON_12, FORET, PRE, CHAMP, COLLINE, MONTAGNE, DESERT, MER;
    private ArrayList<Tuile> listeTuiles; // liste des tuiles
    private com.mygdx.game.autre.StackNonRedimensionnable<String> stackTerrains; // stack de type de tuile
    private com.mygdx.game.autre.StackNonRedimensionnable<Jeton> stackJetons; // stack de tokens
    private Texture textureMer;



    public Plateau() {
        chargerTextures();
        listeTuiles = new ArrayList<Tuile>();
        stackTerrains = new com.mygdx.game.autre.StackNonRedimensionnable<String>(19);
        stackJetons = new com.mygdx.game.autre.StackNonRedimensionnable<Jeton>(18);
        textureMer = MER;
        //generer();
    }

    // Génère le plateau de jeu ainsi que ses composants
    public void generer() {
        genererTuiles();

        genererStackTerrains();
        affecterType();

        genererStackJetons();
        affecterJetons();
    }

    // Génère les tuiles
    private void genererTuiles() {
        Vector2 center = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        //Vector2 activeCenter = new Vector2(center);
        int taille = TAILLE_TUILE;
        float deltaX = (float) ((Math.sqrt(3)*taille)/2);

        // Première ligne
        listeTuiles.add(new Tuile(new Vector2(center.x-2*deltaX,center.y+3*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x,center.y+3*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x+2*deltaX,center.y+3*taille),taille));

        // Deuxième ligne
        listeTuiles.add(new Tuile(new Vector2(center.x-3*deltaX,center.y+1.5f*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x-1*deltaX,center.y+1.5f*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x+1*deltaX,center.y+1.5f*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x+3*deltaX,center.y+1.5f*taille),taille));

        // Troisième ligne
        listeTuiles.add(new Tuile(new Vector2(center.x-4*deltaX,center.y),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x-2*deltaX,center.y),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x,center.y),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x+2*deltaX,center.y),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x+4*deltaX,center.y),taille));

        // Quatrième ligne
        listeTuiles.add(new Tuile(new Vector2(center.x-3*deltaX,center.y-1.5f*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x-1*deltaX,center.y-1.5f*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x+1*deltaX,center.y-1.5f*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x+3*deltaX,center.y-1.5f*taille),taille));

        // Cinquième ligne
        listeTuiles.add(new Tuile(new Vector2(center.x-2*deltaX,center.y-3*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x,center.y-3*taille),taille));
        listeTuiles.add(new Tuile(new Vector2(center.x+2*deltaX,center.y-3*taille),taille));
    }

    // Génère le paquet de type de terrain
    private void genererStackTerrains() {
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
    private void affecterType() {
        for (Tuile h : listeTuiles) {
            h.affecterType(stackTerrains.pop());
        }
    }

    // Génère le paquet de type de jeton
    private void genererStackJetons() {
        for (int i=0 ; i<2 ; i++) {
            stackJetons.push(new Jeton(3));
            stackJetons.push(new Jeton(4));
            stackJetons.push(new Jeton(5));
            stackJetons.push(new Jeton(6));
            stackJetons.push(new Jeton(8));
            stackJetons.push(new Jeton(9));
            stackJetons.push(new Jeton(10));
            stackJetons.push(new Jeton(11));
        }
        stackJetons.push(new Jeton(2));
        stackJetons.push(new Jeton(12));
        System.out.println(stackJetons);
        stackJetons.shuffle();
        System.out.println(stackJetons);
    }

    // Affecte un jeton à chaque tuile excepté la tuile désert
    private void affecterJetons() {
        for (Tuile h : listeTuiles) {
            if (!h.getType().equals("desert"))
                h.affecterJeton(stackJetons.pop());
        }
    }

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

    public ArrayList<Tuile> getListeTuiles() {
        return listeTuiles;
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
