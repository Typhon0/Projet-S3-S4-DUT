package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.autre.StackNonRedimensionnable;

import java.util.*;

/**
 * <b> Plateau est la classe représentant le Plateau du jeu</b>
 * <p>
 * Un Plateau  est caractérisé par les informations suivantes :
 * <ul>
 * <li>La taille de d'une tuile</li>
 * <li>La taille latérale d'une tuile</li>
 * <li>Le centre du plateau</li>
 * <li>Toutes les textures du plateau</li>
 * <li>La liste des tuiles et des ports/mer</li>
 * <li>Les paquets de terrains et de jetons (piles)</li>
 * <li>Les ressources du plateau de jeu</li>
 * <li>Le voleur</li>
 * </ul>
 * </p>
 *
 * @author Geris Nicolas
 * @version 1.0
 * @see Constantes
 * @see Joueur
 * @see Plateau
 * @see SiteConstruction
 * @see Structure
 * @see Tuile
 * @see De
 * @see Jeton
 * @see PaquetRessources
 * @see Texture
 * @see Port
 */

public class Plateau {

    /**
     * Taille de la tuile en fonction de la hauteur de l'écran
     */
    public static final int TAILLE_TUILE = Gdx.graphics.getHeight() / 10;

    /**
     * Distance en le centre d'une tuile et le centre d'une arêtes
     */
    public static final float DELTA_X = (float) Math.sqrt( 3 ) * TAILLE_TUILE / 2;

    /**
     * Centre du plateau
     */
    public static final Vector2 CENTRE_PLATEAU = new Vector2( Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 );

    /**
     * Toutes les Textures de jeton et des tuiles
     */
    private static Texture JETON_2, JETON_3, JETON_4, JETON_5, JETON_6, JETON_8, JETON_9, JETON_10, JETON_11, JETON_12, FORET, PRE, CHAMP, COLLINE, MONTAGNE, DESERT, MER, PORT,TRANSPARENT;

    /**
     * Toutes les textures des pions
     */
    private static Texture VILLE_ROUGE, COLONIE_ROUGE, ROUTE_ROUGE, VILLE_BLEU, COLONIE_BLEU, ROUTE_BLEU, VILLE_VERT, COLONIE_VERT, ROUTE_VERT, VILLE_JAUNE, COLONIE_JAUNE, ROUTE_JAUNE,VOLEUR;

    /**
     * Liste des tuiles du plateau
     */
    private ArrayList<Tuile> listeTuiles;

    /**
     * Liste des ports du plateau
     */
    private ArrayList<Port> listePorts;

    /**
     * Stack de terrains qui seront placé un par un
     */
    private com.mygdx.game.autre.StackNonRedimensionnable<Integer> stackTerrains; // stack de type de tuile

    /**
     * Stack de jetons qui seront placé un par un
     */
    private com.mygdx.game.autre.StackNonRedimensionnable<Jeton> stackJetons; // stack de tokens

    /**
     * La texture de la mer
     */
    private Texture textureMer;

    /**
     * Voleur
     */
    private Voleur voleur;
    /**
     * Le paquet de ressources du plateau de jeu
     */
    private static PaquetRessources ressources;

    /**
     * Constructeur
     */
    public Plateau() {
        chargerTextures();
        listeTuiles = new ArrayList<Tuile>();
        listePorts = new ArrayList<Port>();
        stackTerrains = new com.mygdx.game.autre.StackNonRedimensionnable<Integer>( 19 );
        stackJetons = new com.mygdx.game.autre.StackNonRedimensionnable<Jeton>( 18 );
        //stackPorts = new StackNonRedimensionnable<Port>(6);
        textureMer = MER;

        ressources = new PaquetRessources(Constantes.COULEUR_JEU);
        ressources.remplir();
    }

    public void placerVoleurSurDesert() {
        voleur = new Voleur();
        Tuile t = null;
        for (int i=0 ; i<listeTuiles.size() ; i++) {
            if (listeTuiles.get( i ).getType() == Constantes.DESERT) {
                System.out.println("trouvé le desert");
               t = listeTuiles.get( i);
                break;
            }
        }
        if (t != null) {
            voleur.deplacer( t );
        }
        else {
            System.err.println("Erreur de placement du voleur sur le désert");
        }
    }

    /**
     * Génère le plateau de jeu ainsi que ses composants
     */
    public void generer() {
        genererTuiles();

        genererStackTerrains();
        affecterType();

        genererStackJetons();
        affecterJetons();

        genererPorts();

        fusionnerSiteConstruction();
        fusionnerSiteConstructionRoute();

        placerVoleurSurDesert();
    }

    /**
     * Génère les tuiles du plateau
     */
    private void genererTuiles() {
        // Première ligne
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x - 2 * DELTA_X, CENTRE_PLATEAU.y + 3 * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x, CENTRE_PLATEAU.y + 3 * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x + 2 * DELTA_X, CENTRE_PLATEAU.y + 3 * TAILLE_TUILE ), TAILLE_TUILE ) );

        // Deuxième ligne
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x - 3 * DELTA_X, CENTRE_PLATEAU.y + 1.5f * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x - 1 * DELTA_X, CENTRE_PLATEAU.y + 1.5f * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x + 1 * DELTA_X, CENTRE_PLATEAU.y + 1.5f * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x + 3 * DELTA_X, CENTRE_PLATEAU.y + 1.5f * TAILLE_TUILE ), TAILLE_TUILE ) );

        // Troisième ligne
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x - 4 * DELTA_X, CENTRE_PLATEAU.y ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x - 2 * DELTA_X, CENTRE_PLATEAU.y ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x, CENTRE_PLATEAU.y ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x + 2 * DELTA_X, CENTRE_PLATEAU.y ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x + 4 * DELTA_X, CENTRE_PLATEAU.y ), TAILLE_TUILE ) );

        // Quatrième ligne
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x - 3 * DELTA_X, CENTRE_PLATEAU.y - 1.5f * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x - 1 * DELTA_X, CENTRE_PLATEAU.y - 1.5f * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x + 1 * DELTA_X, CENTRE_PLATEAU.y - 1.5f * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x + 3 * DELTA_X, CENTRE_PLATEAU.y - 1.5f * TAILLE_TUILE ), TAILLE_TUILE ) );

        // Cinquième ligne
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x - 2 * DELTA_X, CENTRE_PLATEAU.y - 3 * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x, CENTRE_PLATEAU.y - 3 * TAILLE_TUILE ), TAILLE_TUILE ) );
        listeTuiles.add( new Tuile( new Vector2( CENTRE_PLATEAU.x + 2 * DELTA_X, CENTRE_PLATEAU.y - 3 * TAILLE_TUILE ), TAILLE_TUILE ) );
    }

    /**
     * Génère le paquet de type de terrain
     */
    private void genererStackTerrains() {
        for (int i = 0; i < 4; i++) {
            stackTerrains.push( Constantes.FORET );
            stackTerrains.push( Constantes.PRE );
            stackTerrains.push( Constantes.CHAMP );
            if (i > 0) {
                stackTerrains.push( Constantes.COLLINE );
                stackTerrains.push( Constantes.MONTAGNE );
            }
        }
        stackTerrains.push( Constantes.DESERT );
        stackTerrains.shuffle();
    }

    /**
     * Affecte un type à chaque tuile en tirer un type dans le paquet de type
     */
    private void affecterType() {
        for (Tuile h : listeTuiles) {
            h.affecterType( stackTerrains.pop() );
        }
    }

    /**
     * Génère le paquet de type de jeton
     */
    private void genererStackJetons() {
        for (int i = 0; i < 2; i++) {
            stackJetons.push( new Jeton( 3 ) );
            stackJetons.push( new Jeton( 4 ) );
            stackJetons.push( new Jeton( 5 ) );
            stackJetons.push( new Jeton( 6 ) );
            stackJetons.push( new Jeton( 8 ) );
            stackJetons.push( new Jeton( 9 ) );
            stackJetons.push( new Jeton( 10 ) );
            stackJetons.push( new Jeton( 11 ) );
        }
        stackJetons.push( new Jeton( 2 ) );
        stackJetons.push( new Jeton( 12 ) );
        //System.out.println(stackJetons);
        stackJetons.shuffle();
        //System.out.println(stackJetons);
    }

    /**
     * Affecte un jeton à chaque tuile excepté la tuile désert
     */
    private void affecterJetons() {
        for (Tuile h : listeTuiles) {
            if (h.getType() != Constantes.DESERT)
                h.affecterJeton( stackJetons.pop() );
            else
                System.out.println( "J'ai trouve le desert" );
        }
    }

    /**
     * Génère les 6 ports
     */
    private void genererPorts() {
        listePorts.add( new Port( new Vector2( listeTuiles.get( 2 ).getListeSommets().get( 0 ).x + DELTA_X, listeTuiles.get( 2 ).getListeSommets().get( 0 ).y + 1.5f * TAILLE_TUILE ), "aucun", 3, PORT ) );
        listePorts.add( new Port( new Vector2( listeTuiles.get( 11 ).getListeSommets().get( 0 ).x + 2 * DELTA_X, listeTuiles.get( 11 ).getListeSommets().get( 0 ).y ), "aucun", 3, PORT ) );
        listePorts.add( new Port( new Vector2( listeTuiles.get( 18 ).getListeSommets().get( 0 ).x + DELTA_X, listeTuiles.get( 18 ).getListeSommets().get( 0 ).y - 1.5f * TAILLE_TUILE ), "aucun", 3, PORT ) );
        listePorts.add( new Port( new Vector2( listeTuiles.get( 16 ).getListeSommets().get( 0 ).x - DELTA_X, listeTuiles.get( 16 ).getListeSommets().get( 0 ).y - 1.5f * TAILLE_TUILE ), "aucun", 3, PORT ) );
        listePorts.add( new Port( new Vector2( listeTuiles.get( 7 ).getListeSommets().get( 0 ).x - 2 * DELTA_X, listeTuiles.get( 7 ).getListeSommets().get( 0 ).y ), "aucun", 3, PORT ) );
        listePorts.add( new Port( new Vector2( listeTuiles.get( 0 ).getListeSommets().get( 0 ).x - DELTA_X, listeTuiles.get( 0 ).getListeSommets().get( 0 ).y + 1.5f * TAILLE_TUILE ), "aucun", 3, PORT ) );
    }

    /**
     * Fusionne les sites de construction (type batiment) qui sont identiques
     *
     * @see SiteConstruction#equals(Object)
     */
    public void fusionnerSiteConstruction() {
        for (int i = 0; i < listeTuiles.size() - 1; i++) {
            for (int j = i + 1; j < listeTuiles.size(); j++) {
                for (int k = 0; k < listeTuiles.get( j ).getListeSitesConstruction().size(); k++) {
                    listeTuiles.get( i ).fusionnerSiteConstruction( listeTuiles.get( j ).getListeSitesConstruction().get( (k) ) );
                }
            }
        }
    }

    /**
     * Fusionne les sites de construction (type route) qui sont identiques
     *
     * @see SiteConstruction#equals(Object)
     */
    public void fusionnerSiteConstructionRoute() {
        for (int i = 0; i < listeTuiles.size() - 1; i++) {
            for (int j = i + 1; j < listeTuiles.size(); j++) {
                for (int k = 0; k < listeTuiles.get( j ).getListeSitesConstructionRoute().size(); k++) {
                    listeTuiles.get( i ).fusionnerSiteConstructionRoute( listeTuiles.get( j ).getListeSitesConstructionRoute().get( (k) ) );
                }
            }
        }
    }

    /**
     * Méthode qui charge toutes les textures du plateau et incrémente un message d'erreur en fonction des images qui n'ont pas pu être chargées
     */
    private void chargerTextures() {
        String messageErreur = "";
        try {
            JETON_2 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_2.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_2.png" + "\n";
        }
        try {
            JETON_3 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_3.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_3.png" + "\n";
        }
        try {
            JETON_4 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_4.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_4.png" + "\n";
        }
        try {
            JETON_5 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_5.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_5.png" + "\n";
        }
        try {
            JETON_6 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_6.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_6.png" + "\n";
        }
        try {
            JETON_8 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_8.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_8.png" + "\n";
        }
        try {
            JETON_9 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_9.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_9.png" + "\n";
        }
        try {
            JETON_10 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_10.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_10.png" + "\n";
        }
        try {
            JETON_11 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_11.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_11.png" + "\n";
        }
        try {
            JETON_12 = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "jeton_12.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "jeton_12.png" + "\n";
        }
        try {
            FORET = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_TUILES + "texture_foret.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_TUILES + "texture_foret.png" + "\n";
        }
        try {
            PRE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_TUILES + "texture_pre.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_TUILES + "texture_pre.png" + "\n";
        }
        try {
            CHAMP = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_TUILES + "texture_champ.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_TUILES + "texture_champ.png" + "\n";
        }
        try {
            COLLINE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_TUILES + "texture_colline.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_TUILES + "texture_colline.png" + "\n";
        }
        try {
            MONTAGNE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_TUILES + "texture_montagne.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_TUILES + "texture_montagne.png" + "\n";
        }
        try {
            DESERT = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_TUILES + "texture_desert.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_TUILES + "texture_desert.png" + "\n";
        }
        try {
            MER = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_MER + "texture_mer.jpg" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_MER + "texture_mer.jpg" + "\n";
        }
        try {
            PORT = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PORTS + "texture_port2.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PORTS + "texture_port2.png" + "\n";
        }
        // Pions rouge
        try {
            VILLE_ROUGE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "ville_rouge.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "ville_rouge.png" + "\n";
        }
        try {
            COLONIE_ROUGE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "colonie_rouge.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "colonie_rouge.png" + "\n";
        }
        try {
            ROUTE_ROUGE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "route_rouge.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "route_rouge.png" + "\n";
        }
        // Pions bleu
        try {
            VILLE_BLEU = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "ville_bleu.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "ville_bleu.png" + "\n";
        }
        try {
            COLONIE_BLEU = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "colonie_bleu.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "colonie_bleu.png" + "\n";
        }
        try {
            ROUTE_BLEU = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "route_bleu.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "route_bleu.png" + "\n";
        }
        // Pions vert
        try {
            VILLE_VERT = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "ville_vert.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "ville_vert.png" + "\n";
        }
        try {
            COLONIE_VERT = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "colonie_vert.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "colonie_vert.png" + "\n";
        }
        try {
            ROUTE_VERT = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "route_vert.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "route_vert.png" + "\n";
        }
        // Pions jaune
        try {
            VILLE_JAUNE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "ville_jaune.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "ville_jaune.png" + "\n";
        }
        try {
            COLONIE_JAUNE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "colonie_jaune.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "colonie_jaune.png" + "\n";
        }
        try {
            ROUTE_JAUNE = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_PIONS + "route_jaune.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_PIONS + "route_jaune.png" + "\n";
        }
        // Transparent
        try {
            TRANSPARENT = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_JETONS + "transparent.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_JETONS + "transparent.png" + "\n";
        }
        // Voleur
        try {
            VOLEUR = new Texture( Gdx.files.internal( Constantes.CHEMIN_ACCES_VOLEUR + "voleur.png" ) );
        } catch (Exception e) {
            messageErreur += "Erreur lors du chargement de la texture : " + Constantes.CHEMIN_ACCES_VOLEUR + "voleur.png" + "\n";
        }
        System.err.println( messageErreur );
    }

    // Getter & Setter
    public static PaquetRessources getRessources() {
        return ressources;
    }

    public ArrayList<Tuile> getListeTuiles() {
        return listeTuiles;
    }

    public ArrayList<Port> getListePorts() {
        return listePorts;
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

    public static Texture getPORT() {
        return PORT;
    }

    public static int getTailleTuile() {
        return TAILLE_TUILE;
    }

    public static float getDeltaX() {
        return DELTA_X;
    }

    public static Vector2 getCentrePlateau() {
        return CENTRE_PLATEAU;
    }

    public static Texture getVilleRouge() {
        return VILLE_ROUGE;
    }

    public static Texture getColonieRouge() {
        return COLONIE_ROUGE;
    }

    public static Texture getRouteRouge() {
        return ROUTE_ROUGE;
    }

    public static Texture getVilleBleu() {
        return VILLE_BLEU;
    }

    public static Texture getColonieBleu() {
        return COLONIE_BLEU;
    }

    public static Texture getRouteBleu() {
        return ROUTE_BLEU;
    }

    public static Texture getVilleVert() {
        return VILLE_VERT;
    }

    public static Texture getColonieVert() {
        return COLONIE_VERT;
    }

    public static Texture getRouteVert() {
        return ROUTE_VERT;
    }

    public static Texture getVilleJaune() {
        return VILLE_JAUNE;
    }

    public static Texture getColonieJaune() {
        return COLONIE_JAUNE;
    }

    public static Texture getRouteJaune() {
        return ROUTE_JAUNE;
    }

    public com.mygdx.game.autre.StackNonRedimensionnable<Integer> getStackTerrains() {
        return stackTerrains;
    }

    public static Texture getTRANSPARENT() {
        return TRANSPARENT;
    }

    public static Texture getVOLEUR() {
        return VOLEUR;
    }

    public Voleur getVoleur() {
        return voleur;
    }

    public StackNonRedimensionnable<Jeton> getStackJetons() {
        return stackJetons;
    }
}