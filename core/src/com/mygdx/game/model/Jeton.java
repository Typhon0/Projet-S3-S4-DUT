package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * <b> Jeton est la classe représentant le jeton affecté à chaque Tuile. Le jeton permet de connaitre les tuiles qui doivent produire en fonction du résultat des lancers de dés</b>
 * <p>
 * Un jeton  est caractérisé par les informations suivantes :
 * <ul>
 * <li>La valeur du jeton</li>
 * <li>La tuile associée </li>
 * <li>La texture de l'image</li>
 * </ul>
 * </p>
 *
 * @author Geris Nicolas
 * @version 1.0
 * @see Tuile
 * @see Texture
 */

public class Jeton {

    /**
     * La valeur du jeton. Cet
     */
    private Integer valeur;

    /**
     * La tuile sur laquelle se trouve le jeton
     */
    private Tuile tuile;

    /**
     * Le texture du jeton. Cette image dépend de la valeur du jeton
     */
    private Texture textureJeton;

    /**
     * Constructeur qui reçoit la valeur du jeton en argument.
     *
     * @param valeur la valeur du jeton
     * @see Jeton#affecterValeur(Integer)
     */
    public Jeton(Integer valeur) {
        affecterValeur(valeur);
    }

    /**
     * Affecte au jeton la tuile sur laquelle il se trouve
     *
     * @param h Tuile sur laquelle se trouve le jeton
     * @see Jeton#affecterTuile(Tuile)
     */
    public void affecterTuile(Tuile h) {
        this.setTuile(h);
    }

    /**
     * Affecte au jeton la valeur qu'il reçoit en argument.
     * Affecte aussi la texture du jeton
     *
     * @param valeur Valeur du jeton
     * @see Jeton#affecterTexture()
     */
    public void affecterValeur(Integer valeur) {
        this.setValeur(valeur);
        affecterTexture();
    }

    /**
     * Affecte au jeton sa texteture en fonction de sa valeur.
     * Ex : Si la valeur du jeton est 2, on lui affecte la texture du jeton 2.
     * Pour le desert, on affecte une texture transparente sans incidence
     *
     * @see Plateau#getJeton2()
     * @see Jeton#valeur
     * @see Jeton#textureJeton
     */
    public void affecterTexture() {

        if (valeur == Constantes.JETON_2)
            textureJeton = Plateau.getJeton2();
        else if (valeur == Constantes.JETON_3)
            textureJeton = Plateau.getJeton3();
        else if (valeur == Constantes.JETON_4)
            textureJeton = Plateau.getJeton4();
        else if (valeur == Constantes.JETON_5)
            textureJeton = Plateau.getJeton5();
        else if (valeur == Constantes.JETON_6)
            textureJeton = Plateau.getJeton6();
        else if (valeur == Constantes.JETON_8)
            textureJeton = Plateau.getJeton8();
        else if (valeur == Constantes.JETON_9)
            textureJeton = Plateau.getJeton9();
        else if (valeur == Constantes.JETON_10)
            textureJeton = Plateau.getJeton10();
        else if (valeur == Constantes.JETON_11)
            textureJeton = Plateau.getJeton11();
        else if (valeur == Constantes.JETON_12)
            textureJeton = Plateau.getJeton12();
        else {
            textureJeton = Plateau.getTRANSPARENT();
            System.err.println("Valeur du jeton incorrecte");
        }
    }

    /**
     * Méthode toString() de la classe
     *
     * @return en String la valeur du jeton
     */
    public String toString() {
        return "Valeur du jeton : " + valeur.toString();
    }

    // Getters & Setters

    public void setTuile(Tuile tuile) {
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

    public Tuile getTuile() {
        return tuile;
    }
}