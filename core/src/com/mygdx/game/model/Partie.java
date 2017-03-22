package com.mygdx.game.model;


import java.util.ArrayList;


/**
 * Created by XXIII on 21/03/2017.
 */

public class Partie {
    private int indiceJoueurActif;
    private Joueur[] joueurs;
    private Joueur joueurActif;
    private int typeStructure; // type de structure sélectionnée par le joueur
    private Plateau plateau;
    private De de1,de2;

    public Partie() {
        creerPlateau();
        creerJoueurs();
        creerDes();
        joueurActif = joueurs[0];
        donnerRessourcesDepart();
    }

    public void creerJoueurs() {
        Joueur j1 = new Joueur("joueur 1", Constantes.COULEUR_ROUGE );
        Joueur j2 = new Joueur("joueur 2", Constantes.COULEUR_BLEU );
        Joueur j3 = new Joueur("joueur 3", Constantes.COULEUR_VERT );
        Joueur j4 = new Joueur("joueur 4", Constantes.COULEUR_JAUNE );
        joueurs = new Joueur[]{j1,j2,j3,j4};
    }

    public void creerDes() {
        de1 = new De();
        de2 = new De();
    }

    public void creerPlateau() {
        plateau = new Plateau();
        plateau.generer();
    }

    public void finDeTour() {
        joueurSuivant();
        typeStructure = 0;
        nouveauTour();
    }

    public void joueurSuivant() {
        for (int i=0 ; i<joueurs.length ; i++) {
            if (joueurActif.equals( joueurs[i] )) {
                if (i != joueurs.length-1) {
                    joueurActif = joueurs[i+1];
                    System.out.println("suivant");
                    break;
                }
                else {
                    joueurActif = joueurs[0];
                    break;
                }
            }
        }
    }

    public void nouveauTour(){
        de1.lancer();
        de2.lancer();
        donnerRessourcesAuxJoueurs( de1.getValeur() + de2.getValeur());
    }

    public void verifierPointsVictoire() {
        if (joueurActif.getPoints() >= Constantes.POINTS_VICTOIRE_MAX) {
            System.out.println("Le joueur "+joueurActif.toString()+" a gagné");
        }
        else {
            System.out.println("Le joueur "+joueurActif.toString()+"n'a pas atteint 10 PV");
        }
    }

    public void donnerRessourcesDepart() {
        for (int i=0 ; i<joueurs.length ; i++) {
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(),plateau.getRessources(),Constantes.ARGILE,10 );
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(),plateau.getRessources(),Constantes.BLE,10 );
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(),plateau.getRessources(),Constantes.MINERAI,10 );
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(),plateau.getRessources(),Constantes.BOIS,10 );
            PaquetRessources.recevoirRessource( joueurs[i].getRessources(),plateau.getRessources(),Constantes.LAINE,10 );
        }
    }

    public void afficherPointsVictoire() {
        for (int i=0 ; i<joueurs.length ; i++) {
            System.out.println("Joueur "+i+" "+joueurs[i].getPoints());
        }
    }
/*
    public static void majPoints() {
        afficherPointsVictoire();
    }
*/
    // Ajout Robin

    // Donne les ressources aux joueurs selon la somme des des
    public void donnerRessourcesAuxJoueurs(int sommeDes) {
        ArrayList<Tuile> liste = getListeTuileJeton(sommeDes);
        for(int i=0; i<liste.size(); i++) {
            Tuile t = liste.get(i);
            for(int j=0; j<liste.get(i).getListeSitesConstruction().size(); j++)      {
                SiteConstruction sc = liste.get(i).getListeSitesConstruction().get(j);
                if(sc.isEstConstruit())                {
                    Structure s = sc.getStructure();
                    Joueur joueur = s.getJoueur();
                    int valeur = 0;
                    valeur = (s.getTypeStructure()== Constantes.VILLE)?2:1;
                    switch(t.getType())                    {
                        case Constantes.FORET : PaquetRessources.recevoirRessource(joueur.getRessources(), plateau.getRessources(), Constantes.BOIS, valeur); break;
                        case Constantes.PRE : PaquetRessources.recevoirRessource(joueur.getRessources(), plateau.getRessources(), Constantes.LAINE, valeur); break;
                        case Constantes.MONTAGNE : PaquetRessources.recevoirRessource(joueur.getRessources(), plateau.getRessources(), Constantes.MINERAI, valeur); break;
                        case Constantes.COLLINE : PaquetRessources.recevoirRessource(joueur.getRessources(), plateau.getRessources(), Constantes.ARGILE, valeur); break;
                        case Constantes.CHAMP : PaquetRessources.recevoirRessource(joueur.getRessources(), plateau.getRessources(), Constantes.BLE, valeur); break;
                        default : System.out.println("Erreur donnerRessourceAuxJoueurs t.getTypeTuile deuxieme switch "); break;
                    }
                }
            }
        }
    }

    // Renvoie la liste des tuiles dont le jeton est egal a la somme des des
    public ArrayList<Tuile> getListeTuileJeton(int sommeDes) {
        ArrayList<Tuile> liste = new ArrayList<Tuile>();
        for(int i=0; i<plateau.getListeTuiles().size(); i++)        {
            if(plateau.getListeTuiles().get(i).getJeton().getValeur() == sommeDes)            {
                liste.add(plateau.getListeTuiles().get(i));
            }
        }
        return liste;
    }


    // Fin ajout

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
}
