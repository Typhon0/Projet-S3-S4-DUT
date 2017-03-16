package com.mygdx.game.model;

/**
 * Created by XXIII on 15/03/2017.
 */

public class PaquetRessources {

    private int[] ressources; // on ne travaille pas avec l'indice 0
    private String proprietaire; // Nom du propriétaire : est le paquet d'un joueur ou du jeu (ressource globale)

    public PaquetRessources(String proprietaire) {
        ressources = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];
        this.proprietaire = proprietaire;
        for (int i=1 ; i<ressources.length ; i++) {
            ressources[i] = 0;
        }
    }

    // Remplir le paquet avec le montant maximum de chaque ressource
    // Sert au paquet de ressource du jeu
    public void remplir() {
        for (int i=Constantes.NUMERO_RESSOURCE_MIN ; i<ressources.length ; i++) {
            ressources[i] = Constantes.MONTANT_RESSOURCE_MAXIMUM;
        }
    }

    // Vérifie que la quantité de typeRessrouce est retirable
    public boolean estRetirable(int typeRessource,int quantite) {
        //if (typeRessource >= Constantes.NUMERO_RESSOURCE_MIN && typeRessource <= Constantes.NUMERO_RESSOURCE_MAX)
        System.out.println("Ressource en stock de "+Constantes.nomRessource(typeRessource)+" en stock = "+ressources[typeRessource]+" ,quantité demandée = "+quantite);
            return ressources[typeRessource] >= quantite;
        //return false;
    }

    // Ajoute la quantité "quantite" à la ressource "typeRessource" du paquet
    public void retirerRessource(int typeRessource,int quantite) {
        ressources[typeRessource] -= quantite;
    }

    // Retire la quantité "quantite" à la ressource "typeRessource" du paquet
    public void ajouterRessource(int typeRessource,int quantite) {
        ressources[typeRessource] += quantite;
    }

    // le paquet 1 reçoit x quantité de ressources du type "ressource" du paquet 2
    public static void recevoirRessource(PaquetRessources p1, PaquetRessources p2, int typeRessource, int quantite) {
        // Vérification que le type de ressource est correcte
        if (typeRessource >= Constantes.NUMERO_RESSOURCE_MIN && typeRessource <= Constantes.NUMERO_RESSOURCE_MAX) {
            if (p2.estRetirable(typeRessource,quantite)) {
                p2.retirerRessource(typeRessource,quantite);
                p1.ajouterRessource(typeRessource,quantite);
                System.out.println("Echange effectué avec succès");
            }
            else
                throw new RuntimeException("Erreur : le paquet 2 n'a pas assez de ressources");
        }
        else {
            throw new RuntimeException("Erreur de type de ressource lors de l'échange");
        }
    }

    public int[] getRessources() {
        return ressources;
    }

    public String getProprietaire() {
        return proprietaire;
    }
}
