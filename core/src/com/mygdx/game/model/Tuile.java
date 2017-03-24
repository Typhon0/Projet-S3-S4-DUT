package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * <b> Tuile est la classe représentant la tuile/hexagone sur pointe qui sert à définir un terrain qui produit un certain type de ressource selon le type de terrain</b>
 * <p>
 * une Tuile est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le centre de la tuile </li>
 * <li>la taille de la tuile qui est là distance entre le centre et le sommet</li>
 * <li>la distance entre le centre de la tuile et le centre des aretes des côtés</li>
 * <li>La valeur maximale possible du dé</li>
 * </ul>
 * </p>
 * <p>Chaque terrain possède 6 sites de constructions de batiment (ville ou colonie) qui correspondent aux sommets.
 * Il possède également 6 sites de constructions de route qui correspondent au centre des arêtes de la tuile</p>
 *
 * @author Geris Nicolas
 * @version 1.0
 * @see Jeton
 * @see Vector2
 * @see SiteConstruction
 * @see Texture
 */

public class Tuile {

    /**
     * Centre de la tuile
     */
    private Vector2 center;

    /**
     * Taille de la tuile. Distance entre le centre et le sommet
     */
    private float taille;

    /**
     * Distance entre le centre de la tuile et le centre d'une arete
     */
    private float deltaX;

    /**
     * Jeton assigné à la tuile
     */
    private Jeton jeton;

    /**
     * Liste des sommets de la tuile. Sert à construire graphiquement les tuiles
     */
    private ArrayList<Vector2> listeSommets;

    /**
     * Liste des sites de constructions (aux sommets) de la tuile
     */
    private ArrayList<SiteConstruction> listeSitesConstruction;

    /**
     * Liste des sites de constructions de route (aux centre des aretes) de la tuile
     */
    private ArrayList<SiteConstruction> listeSitesConstructionRoute;

    /**
     * Type de terrain de la tuile
     */
    private int type;

    /**
     * Texture de la tuile
     */
    private Texture textureTuile;

    /**
     * Constructeur de la classe Tuile qui prend le un pixel (Vector2) et une distance en argument
     *
     * @param center Centre de la tuile
     * @param taille Taille de la tuile
     * @see Tuile#center
     * @see Tuile#taille
     */
    public Tuile(Vector2 center, float taille) {
        this.center = center;
        this.taille = taille;
        this.jeton = new Jeton(0);
        this.deltaX = (float) ((Math.sqrt(3) * taille) / 2);

        listeSommets = new ArrayList<Vector2>();
        listeSitesConstruction = new ArrayList<SiteConstruction>();
        listeSitesConstructionRoute = new ArrayList<SiteConstruction>();

        ajouterSommet(this.center);
        genererSommets();

        genererSitesConstruction();
        genererSitesConstructionRoute();
    }

    /**
     * Affecte le type de la tuile et la texture correspondante au type de terrain
     *
     * @param s valeur entière qui représente le type de terrain
     */
    public void affecterType(int s) {
        this.setType(s);
        if (s == Constantes.FORET)
            textureTuile = Plateau.getFORET();
        else if (s == Constantes.PRE)
            textureTuile = Plateau.getPRE();
        else if (s == Constantes.CHAMP)
            textureTuile = Plateau.getCHAMP();
        else if (s == Constantes.COLLINE)
            textureTuile = Plateau.getCOLLINE();
        else if (s == Constantes.MONTAGNE)
            textureTuile = Plateau.getMONTAGNE();
        else if (s == Constantes.DESERT)
            textureTuile = Plateau.getDESERT();
        else {
            textureTuile = Plateau.getDESERT();
            System.err.println("Type incorrect");
        }
    }

    /**
     * Affecte à la tuile le jeton passé en argument
     * Affecte aussi au jeton la tuile (this)
     *
     * @param t Jeton passé en argument
     */
    public void affecterJeton(Jeton t) {
        this.setJeton(t);
        t.affecterTuile(this);
    }

    /**
     * Génère les 6 sommets de chaque tuile autour du centre et ajoute ces sommets à la liste des sommets.
     * Cette liste de sommets sert à dessiner le squelette (contour) de la tuile.
     * deltaX correspond à la distance entre le centre de la tuile et le centre d'une arete
     */
    public void genererSommets() {
        float deltaX = (float) ((Math.sqrt(3) * taille) / 2);
        ajouterSommet(new Vector2(center.x, center.y + taille));
        ajouterSommet(new Vector2(center.x + deltaX, center.y + taille / 2));
        ajouterSommet(new Vector2(center.x + deltaX, center.y - taille / 2));
        ajouterSommet(new Vector2(center.x, center.y - taille));
        ajouterSommet(new Vector2(center.x - deltaX, center.y - taille / 2));
        ajouterSommet(new Vector2(center.x - deltaX, center.y + taille / 2));
    }

    /**
     * Ajoute à la liste de sommet le pixel(Vector2) passé en argument.
     * Avant l'ajout, on vérifie que la liste ne contient pas déjà le sommet
     *
     * @param corner Corner correspond à un sommet ajouté à la liste des sommets de la tuile
     */
    public void ajouterSommet(Vector2 corner) {
        if (!listeSommets.contains(corner))
            listeSommets.add(corner);
    }

    /**
     * Génère les sites de constructions (aux sommets) de la tuile et ajoute ces sites "vides" à la liste des sites de construction (batiment) de la tuile
     */
    public void genererSitesConstruction() {
        ajouterSiteConstruction(new SiteConstruction(new Vector2(center.x, center.y + taille), true));
        ajouterSiteConstruction(new SiteConstruction(new Vector2(center.x + deltaX, center.y + taille / 2), true));
        ajouterSiteConstruction(new SiteConstruction(new Vector2(center.x + deltaX, center.y - taille / 2), true));
        ajouterSiteConstruction(new SiteConstruction(new Vector2(center.x, center.y - taille), true));
        ajouterSiteConstruction(new SiteConstruction(new Vector2(center.x - deltaX, center.y - taille / 2), true));
        ajouterSiteConstruction(new SiteConstruction(new Vector2(center.x - deltaX, center.y + taille / 2), true));
    }

    /**
     * Ajoute le site de construction à la liste des sites de constructions en vérifiant qu'il n'est pas déjà présent
     *
     * @param sc SiteConstruction que l'on ajoute à la liste des sites de constructions (batiment)
     */
    public void ajouterSiteConstruction(SiteConstruction sc) {
        if (!listeSitesConstruction.contains(sc))
            listeSitesConstruction.add(sc);
    }

    /**
     * Comme chauque Tuile génère ses sommets et ses routes, il existe des routes et sommets qui sont communs à plusieurs tuiles.
     * Cette méthode fait en sorte que deux sites de construction qui sont sur à la même position pointent vers le même et unique site de construction
     * Renvoie l'indice du premier SiteConstruction identique à celui passé en argument et remplace le SiteConstruction à l'indice i par celui passé en argument
     *
     * @param sc Site de construction que l'on veut rendre unique
     */
    public void fusionnerSiteConstruction(SiteConstruction sc) {
        if (listeSitesConstruction.contains(sc)) {
            int i = listeSitesConstruction.indexOf(sc);
            listeSitesConstruction.set(i, sc);
        }
    }

    /**
     * Génère les sites de constructions (route) de la tuile et les ajoute à la liste des sites de construction (route)
     */
    public void genererSitesConstructionRoute() {
        float deltaXDemi = deltaX / 2.0f;
        ajouterSiteConstructionRoute(new SiteConstruction(new Vector2(center.x + deltaXDemi, center.y + 3 * taille / 4), false));
        ajouterSiteConstructionRoute(new SiteConstruction(new Vector2(center.x + deltaX, center.y), false));
        ajouterSiteConstructionRoute(new SiteConstruction(new Vector2(center.x + deltaXDemi, center.y - 3 * taille / 4), false));
        ajouterSiteConstructionRoute(new SiteConstruction(new Vector2(center.x - deltaXDemi, center.y - 3 * taille / 4), false));
        ajouterSiteConstructionRoute(new SiteConstruction(new Vector2(center.x - deltaX, center.y), false));
        ajouterSiteConstructionRoute(new SiteConstruction(new Vector2(center.x - deltaXDemi, center.y + 3 * taille / 4), false));
    }

    /**
     * Ajoute le site de construction à la liste des sites de constructions (route) en vérifiant qu'il n'est pas déjà présent
     *
     * @param sc SiteConstruction que l'on ajoute à la liste des sites de constructions (route)
     */
    public void ajouterSiteConstructionRoute(SiteConstruction sc) {
        if (!listeSitesConstructionRoute.contains(sc))
            listeSitesConstructionRoute.add(sc);
    }

    /**
     * Comme chauque Tuile génère ses sommets et ses routes, il existe des routes et sommets qui sont communs à plusieurs tuiles.
     * Cette méthode fait en sorte que deux sites de construction qui sont sur à la même position pointent vers le même et unique site de construction
     * Renvoie l'indice du premier SiteConstruction identique à celui passé en argument et remplace le SiteConstruction à l'indice i par celui passé en argument
     *
     * @param sc Site de construction (route) que l'on veut rendre unique
     */
    public void fusionnerSiteConstructionRoute(SiteConstruction sc) {
        if (listeSitesConstructionRoute.contains(sc)) {
            int i = listeSitesConstructionRoute.indexOf(sc); // récupère l'indice
            listeSitesConstructionRoute.set(i, sc);
        }
    }

    /**
     * Deux tuiles sont identiques la valeur absolue de la distance entre leur x et y est inférieur ou égal à 3 pixel
     *
     * @param o Object o
     * @return boolean s'ils sont identiques
     */
    public boolean equals(Object o) {
        if (o instanceof Tuile) {
            Tuile t = (Tuile) o;
            return (Math.abs(this.center.x - t.center.x) <= 3.0 && Math.abs(this.center.y - t.center.y) <= 3.0);
        }
        return false;
    }

    public boolean equals(Vector2 v) {
        return (Math.abs(this.center.x - v.x) <= Constantes.DELTA_X * 2 / 3 && Math.abs(this.center.y - v.y) <= Constantes.DELTA_X * 2 / 3);
    }

    /**
     * Sert à dessiner le squelette de la tuile grâce aux coordonnées de ses sommets
     *
     * @return tableau contenant les couples x,y des sommets de la tuile à dessiner dans le render du batch (affichage)
     */
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

    /**
     * Sert à obtenir le pixel (l'origine) de départ pour dessiner la texture de la tuile
     *
     * @return Pixel se trouver au coin inférieur gauche de la tuile
     */
    public Vector2 getCoinInferieurGaucheTuile() {
        return new Vector2(center.x - deltaX, center.y - taille);
    }

    /**
     * Sert à obtenir le pixel (l'origine) de départ pour dessiner le jeton sur la tuile
     *
     * @return Pixel se trouver au coin inférieur gauche du jeton
     */
    public Vector2 getCoinInferieurGaucheJeton() {
        return new Vector2(center.x - Constantes.DISTANCE_TUILE_X, center.y - Constantes.DISTANCE_TUILE_X);
    }

    // Getter & Setter

    public ArrayList<SiteConstruction> getListeSitesConstruction() {
        return listeSitesConstruction;
    }

    public ArrayList<SiteConstruction> getListeSitesConstructionRoute() {
        return listeSitesConstructionRoute;
    }

    public void setJeton(Jeton jeton) {
        this.jeton = jeton;
    }

    public Jeton getJeton() {
        return jeton;
    }

    public ArrayList<Vector2> getListeSommets() {
        return listeSommets;
    }

    public Texture getTextureTuile() {
        return textureTuile;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
