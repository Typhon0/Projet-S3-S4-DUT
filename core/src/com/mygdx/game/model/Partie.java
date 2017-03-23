package com.mygdx.game.model;

import com.mygdx.game.Screen.HUD;

import java.util.ArrayList;

/**
 * <b> Partie est la classe représentant le partie du jeu</b>
 * <p>
 * Un Partie  est caractérisé par les informations suivantes :
 * <ul>
 * <li>L'interface de boutons</li>
 * <li>Le joueur actif (il s'agit de son tour)</li>
 * <li>Le tableau des joueurs</li>
 * <li>Le type de la structure</li>
 * <li>Le plateau de jeu</li>
 * <li>Les deux dés</li>
 * </ul>
 * </p>
 *
 * @author Geris Nicolas
 * @version 1.0
 * @see HUD
 * @see Joueur
 * @see Plateau
 * @see SiteConstruction
 * @see Structure
 * @see Tuile
 * @see De
 */

public class Partie {
    /**
     * Interface de boutons de la partie
     */
    private static HUD hud;

    /**
     * Indice de tableau du joueur actif/dont c'est le tour de jouer
     */
    private int indiceJoueurActif;

    /**
     * Tableau contenant les joueurs
     */
    private Joueur[] joueurs;

    /**
     * Joueur actif/ dont il est le tour de jouer
     */
    private Joueur joueurActif;

    /**
     *
     */
    private Joueur joueurAQuiOnVeutEchanger;

    /**
     * Type de structure sélectionnée par le jour dans l'interface de construction
     */
    private int typeStructure; // type de structure sélectionnée par le joueur

    /**
     * Plateau de jeu
     */
    private Plateau plateau;

    /**
     * Les deux dés qui sont lancés à chaque début de tour pour produire des ressources
     */
    private De de1, de2;

    /**
     * Constructeur de partie
     */
    public Partie() {
        creerPlateau();
        creerJoueurs();
        creerDes();
        joueurActif = joueurs[0];
        donnerRessourcesDepart();
    }

    /**
     * Crée les 4 joueurs
     */
    public void creerJoueurs() {
        Joueur j1 = new Joueur(  Constantes.COULEUR_ROUGE );
        Joueur j2 = new Joueur( Constantes.COULEUR_BLEU );
        Joueur j3 = new Joueur( Constantes.COULEUR_VERT );
        Joueur j4 = new Joueur(  Constantes.COULEUR_JAUNE );
        joueurs = new Joueur[]{j1, j2, j3, j4};
    }

    /**
     * Crée les deux
     */
    public void creerDes() {
        de1 = new De();
        de2 = new De();
    }

    /**
     * Crée le plateau
     */
    public void creerPlateau() {
        plateau = new Plateau();
        plateau.generer();
    }

    /**
     * Fin de tour du joueur. Réinitialise la structure sélectionnée.
     */
    public void finDeTour() {
        joueurSuivant();
        typeStructure = 0;
        nouveauTour();
    }

    /**
     * Affecte le prochain le joueur actif selon l'odre du tableau
     */
    public void joueurSuivant() {
        for (int i = 0; i < joueurs.length; i++) {
            if (joueurActif.equals( joueurs[i] )) {
                if (i != joueurs.length - 1) {
                    joueurActif = joueurs[i + 1];
                    //System.out.println("suivant");
                    break;
                } else {
                    joueurActif = joueurs[0];
                    break;
                }
            }
        }
    }

    /**
     * Début du nouveau tour
     */
    public void nouveauTour() {
        hud.afficherMessage( "Nouveau tour", "C'est au tour du joueur : " + Constantes.couleurJoueur( joueurActif.getCouleur() ) );
        de1.lancer();
        de2.lancer();
        donnerRessourcesAuxJoueurs( de1.getValeur() + de2.getValeur() );
    }

    /**
     * Vérifie si le joueur actif a atteint les 10 points de victoire
     */
    public void verifierPointsVictoire() {
        if (joueurActif.getPoints() >= Constantes.POINTS_VICTOIRE_MAX) {
            System.out.println( "Le joueur " + joueurActif.toString() + " a gagné" );
            hud.afficherMessage( "Vainqueur de la partie : joueur " + Constantes.couleurJoueur( joueurActif.getCouleur() ), "Vous avez gagné !" );
        } else {
            System.out.println( "Le joueur " + joueurActif.toString() + "n'a pas atteint 10 PV" );
        }
    }

    /**
     * Donne des ressources à chaque joueur
     */
    public void donnerRessourcesDepart() {
        for (int i = 0; i < joueurs.length; i++) {
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(), plateau.getRessources(), Constantes.ARGILE, Constantes.MONTANT_RESSOURCE_DEPART_ARGILE );
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(), plateau.getRessources(), Constantes.BLE, Constantes.MONTANT_RESSOURCE_DEPART_BLE );
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(), plateau.getRessources(), Constantes.MINERAI, Constantes.MONTANT_RESSOURCE_DEPART_MINERAI );
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(), plateau.getRessources(), Constantes.BOIS, Constantes.MONTANT_RESSOURCE_DEPART_BOIS );
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(), plateau.getRessources(), Constantes.LAINE, Constantes.MONTANT_RESSOURCE_DEPART_LAINE );
        }
    }

    public void afficherPointsVictoire() {
        for (int i = 0; i < joueurs.length; i++) {
            System.out.println( "Joueur " + i + " " + joueurs[i].getPoints() );
        }
    }

    /**
     * Donne des ressources à chaque jouer qui possède des structures dont le jeton de la tuile correspond à la somme des dés
     *
     * @param sommeDes Résultat de la somme des deux dés
     */
    public void donnerRessourcesAuxJoueurs(int sommeDes) {
        ArrayList<Tuile> liste = getListeTuileJeton( sommeDes );
        for (int i = 0; i < liste.size(); i++) {
            Tuile t = liste.get( i );
            for (int j = 0; j < liste.get( i ).getListeSitesConstruction().size(); j++) {
                SiteConstruction sc = liste.get( i ).getListeSitesConstruction().get( j );
                if (sc.isEstConstruit()) {
                    Structure s = sc.getStructure();
                    Joueur joueur = s.getJoueur();
                    int valeur = 0;
                    valeur = (s.getTypeStructure() == Constantes.VILLE) ? 2 : 1;
                    switch (t.getType()) {
                        case Constantes.FORET:
                            PaquetRessources.recevoirRessource( joueur.getRessources(), plateau.getRessources(), Constantes.BOIS, valeur );
                            break;
                        case Constantes.PRE:
                            PaquetRessources.recevoirRessource( joueur.getRessources(), plateau.getRessources(), Constantes.LAINE, valeur );
                            break;
                        case Constantes.MONTAGNE:
                            PaquetRessources.recevoirRessource( joueur.getRessources(), plateau.getRessources(), Constantes.MINERAI, valeur );
                            break;
                        case Constantes.COLLINE:
                            PaquetRessources.recevoirRessource( joueur.getRessources(), plateau.getRessources(), Constantes.ARGILE, valeur );
                            break;
                        case Constantes.CHAMP:
                            PaquetRessources.recevoirRessource( joueur.getRessources(), plateau.getRessources(), Constantes.BLE, valeur );
                            break;
                        default:
                            System.err.println( "Erreur donnerRessourceAuxJoueurs t.getTypeTuile deuxieme switch " );
                            break; // ne doit jamais se produire
                    }
                }
            }
        }
    }

    /**
     * Renvoie la liste des tuiles dont le jeton est égal à la somme des dés
     *
     * @param sommeDes Somme des résultats des dés
     * @return liste des tuiles
     */
    public ArrayList<Tuile> getListeTuileJeton(int sommeDes) {
        ArrayList<Tuile> liste = new ArrayList<Tuile>();
        for (int i = 0; i < plateau.getListeTuiles().size(); i++) {
            if (plateau.getListeTuiles().get( i ).getJeton().getValeur() == sommeDes) {
                liste.add( plateau.getListeTuiles().get( i ) );
            }
        }
        return liste;
    }

    // Getters & Setters

    public int getIndiceJoueurActif() {
        return indiceJoueurActif;
    }

    public void setIndiceJoueurActif(int indiceJoueurActif) {
        this.indiceJoueurActif = indiceJoueurActif;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    public Joueur getJoueurActif() {
        return joueurActif;
    }

    public void setJoueurActif(Joueur joueurActif) {
        this.joueurActif = joueurActif;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public De getDe1() {
        return de1;
    }

    public void setDe1(De de1) {
        this.de1 = de1;
    }

    public De getDe2() {
        return de2;
    }

    public void setDe2(De de2) {
        this.de2 = de2;
    }

    public int getTypeStructure() {
        return typeStructure;
    }

    public void setTypeStructure(int typeStructure) {
        this.typeStructure = typeStructure;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }

    public static HUD getHud() {

        return hud;
    }
}
