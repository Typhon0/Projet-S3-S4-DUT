
package com.mygdx.game.model;

import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Screen.HUD;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

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
     * Interface de boutons de la partie
     */
    private static GameScreen gameScreen;

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
     * Joueur à qui on veut donner les ressources
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
    public Partie(GameScreen gamescreen, HUD hud) {
        this.gameScreen = gamescreen;
        this.setHud(hud);
    }

    public Partie() {
    }

    public void initialiserPartie() {
        creerPlateau();
        creerJoueurs();
        creerDes();
        joueurActif = joueurs[0];
        donnerRessourcesDepart();
        premierTour();
    }
    /**
     * Crée les 4 joueurs
     */
    public void creerJoueurs() {
        Joueur j1 = new Joueur(Constantes.COULEUR_ROUGE);
        Joueur j2 = new Joueur(Constantes.COULEUR_BLEU);
        Joueur j3 = new Joueur(Constantes.COULEUR_VERT);
        Joueur j4 = new Joueur(Constantes.COULEUR_JAUNE);
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
        if (plateau.getVoleur().isActif()) {
            plateau.getVoleur().setActif(false);
        }
        nouveauTour();
    }

    /**
     * Affecte le prochain le joueur actif selon l'odre du tableau
     */
    public void joueurSuivant() {
        for (int i = 0; i < joueurs.length; i++) {
            if (joueurActif.equals(joueurs[i])) {
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

    public void premierTour() {
        //hud.afficherMessage("Nouvelle partie ","C'est le joueur " + Constantes.couleurJoueur(joueurActif.getCouleur())+" qui commence");
        de1.lancer();
        de2.lancer();
        int sommeDes = de1.getValeur() + de2.getValeur();
        hud.afficherMessage("Nouvelle partie - tour du joueur " + Constantes.couleurJoueur(joueurActif.getCouleur()), "Valeur du lancer des dés : " + sommeDes);
        activerVoleur();
        donnerRessourcesAuxJoueurs(de1.getValeur() + de2.getValeur());
        for (int i=0 ; i<joueurs.length ; i++) {
            System.out.println(joueurs[i].getRessourcesString());
        }
    }

    /**
     * Début du nouveau tour
     */
    public void nouveauTour() {
        de1.lancer();
        de2.lancer();
        int sommeDes = de1.getValeur() + de2.getValeur();
        hud.afficherMessage("Tour du joueur " + Constantes.couleurJoueur(joueurActif.getCouleur()), "Valeur du lancer des dés : " + sommeDes);
        activerVoleur();
        donnerRessourcesAuxJoueurs(de1.getValeur() + de2.getValeur());
        for (int i=0 ; i<joueurs.length ; i++) {
            System.out.println(joueurs[i].getRessourcesString());
        }
    }

    /**
     * Donne des ressources à chaque joueur
     */
    public void donnerRessourcesDepart() {
        for (int i = 0; i < joueurs.length; i++) {
            PaquetRessources.recevoirRessource(joueurs[i].getPaquetRessources(), plateau.getRessources(), Constantes.ARGILE, Constantes.MONTANT_RESSOURCE_DEPART_ARGILE);
            PaquetRessources.recevoirRessource(joueurs[i].getPaquetRessources(), plateau.getRessources(), Constantes.BLE, Constantes.MONTANT_RESSOURCE_DEPART_BLE);
            PaquetRessources.recevoirRessource(joueurs[i].getPaquetRessources(), plateau.getRessources(), Constantes.MINERAI, Constantes.MONTANT_RESSOURCE_DEPART_MINERAI);
            PaquetRessources.recevoirRessource(joueurs[i].getPaquetRessources(), plateau.getRessources(), Constantes.BOIS, Constantes.MONTANT_RESSOURCE_DEPART_BOIS);
            PaquetRessources.recevoirRessource(joueurs[i].getPaquetRessources(), plateau.getRessources(), Constantes.LAINE, Constantes.MONTANT_RESSOURCE_DEPART_LAINE);
        }
    }

    public void activerVoleur() {
        if (de1.getValeur() + de2.getValeur() == 7) {
            plateau.getVoleur().setActif(true);
        }
    }

    /**
     * Donne des ressources à chaque jouer qui possède des structures dont le jeton de la tuile correspond à la somme des dés
     *
     * @param sommeDes Résultat de la somme des deux dés
     */
    public void donnerRessourcesAuxJoueurs(int sommeDes) {
        ArrayList<Tuile> liste = getListeTuileJeton(sommeDes);
        for (int i = 0; i < liste.size(); i++) {
            Tuile t = liste.get(i);
            if (!plateau.getVoleur().getTuile().equals(t)) {
                for (int j = 0; j < liste.get(i).getListeSitesConstruction().size(); j++) {
                    SiteConstruction sc = liste.get(i).getListeSitesConstruction().get(j);
                    if (sc.isEstConstruit()) {
                        Structure s = sc.getStructure();
                        Joueur joueur = s.getJoueur();
                        int valeur = 0;
                        valeur = (s.getTypeStructure() == Constantes.VILLE) ? 2 : 1;
                        switch (t.getType()) {
                            case Constantes.FORET:
                                PaquetRessources.recevoirRessource(joueur.getPaquetRessources(), plateau.getRessources(), Constantes.BOIS, valeur);
                                break;
                            case Constantes.PRE:
                                PaquetRessources.recevoirRessource(joueur.getPaquetRessources(), plateau.getRessources(), Constantes.LAINE, valeur);
                                break;
                            case Constantes.MONTAGNE:
                                PaquetRessources.recevoirRessource(joueur.getPaquetRessources(), plateau.getRessources(), Constantes.MINERAI, valeur);
                                break;
                            case Constantes.COLLINE:
                                PaquetRessources.recevoirRessource(joueur.getPaquetRessources(), plateau.getRessources(), Constantes.ARGILE, valeur);
                                break;
                            case Constantes.CHAMP:
                                PaquetRessources.recevoirRessource(joueur.getPaquetRessources(), plateau.getRessources(), Constantes.BLE, valeur);
                                break;
                            default:
                                System.err.println("Erreur donnerRessourceAuxJoueurs t.getTypeTuile deuxieme switch ");
                                break; // ne doit jamais se produire
                        }
                    }
                }
            }
        }
    }

    // Methode qui est utilisee lorsque le joueur fait 7 aux des et qu'il choisit ou il place le voleur (joueur et la tuile ciblee en parametres)
    public void actionVoleur(Tuile t) {
        // On deplace le voleur sur la tuile que le joueur a choisi
        plateau.getVoleur().setTuile(t);

        // On parcourt les joueurs
        for (int i = 0; i < this.getJoueurs().length; i++) {
            int nbCartes = 0;
            // Si le joueur n'est pas le joueur actif
            if (this.getJoueurs()[i] != joueurActif) {
                // On parcourt les ressources
                for (int k = Constantes.NUMERO_RESSOURCE_MIN; k < Constantes.TAILLE_TABLEAU_RESSOURCE; k++) {
                    nbCartes += getJoueurs()[i].getPaquetRessources().getRessources()[k];
                }
                System.err.println("nb cartes " + nbCartes);
                // Si le joueur a plus de sept cartes
                if (nbCartes > 7) {
                    int nbCartesRetirees = 0;
                    while (nbCartesRetirees < nbCartes / 2) {
                        Random r = new Random();
                        int a;
                        a = Constantes.NUMERO_RESSOURCE_MIN + r.nextInt(Constantes.NUMERO_RESSOURCE_MAX - Constantes.NUMERO_RESSOURCE_MIN + 1);
                        //System.out.println("L'aleatoire est : " + a);
                        //System.out.println(this.getJoueurs()[i].getRessourcesString());
                        //System.out.println(this.getJoueurs()[i].getPaquetRessources().getRessources()[a]);
                        if (this.getJoueurs()[i].getPaquetRessources().getRessources()[a] >= 1) {
                            //System.out.println("On peut retirer");
                            PaquetRessources.recevoirRessource(plateau.getRessources(), getJoueurs()[i].getPaquetRessources(), a, 1);
                            nbCartesRetirees++;
                        }

                    }
                }
            }
        }
        try {
            plateau.getVoleur().setActif(false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    // Vol d'une ressource aupres d'un des joueurs adjacents apres avoir deplace le voleur (et apres la methode actionVoleur ci-dessus)
    public void volJoueur(Joueur j, Joueur j2) {
        // On choisit une carte aleatoire
        int r = (int) (Math.random() * ((j2.getPaquetRessources().getRessources().length - 1) - 0) + 0);
        // On stocke la valeur qui se trouve a cet indice dans une variable a
        int a = j2.getPaquetRessources().getRessources()[r];
        // On ajoute la carte volee au joueur
        j.getPaquetRessources().ajouterRessource(a, 1);
        // On enleve la carte au joueur 2
        j2.getPaquetRessources().retirerRessource(a, 1);
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
            if (plateau.getListeTuiles().get(i).getJeton().getValeur() == sommeDes) {
                liste.add(plateau.getListeTuiles().get(i));
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

    public static void setGameScreen(GameScreen gameScreen) {
        Partie.gameScreen = gameScreen;
    }

    public static GameScreen getGameScreen() {

        return gameScreen;
    }

    public Joueur getJoueurAQuiOnVeutEchanger() {
        return joueurAQuiOnVeutEchanger;
    }

    public void setJoueurAQuiOnVeutEchanger(Joueur joueurAQuiOnVeutEchanger) {
        this.joueurAQuiOnVeutEchanger = joueurAQuiOnVeutEchanger;
    }
}


