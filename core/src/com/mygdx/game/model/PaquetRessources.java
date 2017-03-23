package com.mygdx.game.model;

/**
 * <b> PaquetRessources est la classe représentant le paquet de ressources que possède chaque acteur du jeu (les joueurs et le plateau de jeu)</b>
 * <p>
 * Un PaquetRessources  est caractérisé par les informations suivantes :
 * <ul>
 * <li>un tableau de ressources</li>
 * <li>un proprietaire </li>
 * </ul>
 * </p>
 *
 * @author Geris Nicolas
 * @version 1.0
 * @see Constantes
 */

public class PaquetRessources {

    /**
     * Tableau de ressources. On ne travaille pas avec l'indice 0
     */
    private int[] ressources;

    /**
     * Nom du propriétaire : est le paquet d'un joueur ou du plateau jeu (=ressource globale)
     */
    private int proprietaire;

    /**
     * Constructeur qui crée le tableau de ressource et initialise le montant de chaque ressource à 0
     *
     * @param proprietaire nom du proprietaire du paquet de ressources
     */
    public PaquetRessources(int proprietaire) {
        ressources = new int[Constantes.TAILLE_TABLEAU_RESSOURCE];
        this.proprietaire = proprietaire;
        for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < ressources.length; i++) {
            ressources[i] = 0;
        }
    }

    /**
     * Permet de remplir le paquet de ressources avec le montant maximum de chaque ressource.
     * Cette méthode sert à remplir le paquet de ressource du plateau de jeu au début, avant de commencer la partie.
     * Les joueurs recevront des ressources du paquet de ressources du plateau de jeu
     */
    public void remplir() {
        for (int i = Constantes.NUMERO_RESSOURCE_MIN; i < ressources.length; i++) {
            ressources[i] = Constantes.MONTANT_RESSOURCE_MAXIMUM;
        }
    }

    /**
     * @param typeRessource Constantes qui représente l'indice de la ressource
     * @param quantite      quantité de la ressource à retirer
     * @return boolean s'il est possible de retirer autant de ressources
     */
    public boolean estRetirable(int typeRessource, int quantite) {
        return ressources[typeRessource] >= quantite;
    }

    /**
     * Soustrait la ressource reçue en argument de la quantité reçue en argument
     *
     * @param typeRessource Type de la ressource
     * @param quantite      quantité de la ressource
     */
    public void retirerRessource(int typeRessource, int quantite) {
        ressources[typeRessource] -= quantite;
    }

    /**
     * Ajoute la ressource reçue en argument de la quantité reçue en argument
     *
     * @param typeRessource Type de la ressource
     * @param quantite      quantité de la ressource
     */
    public void ajouterRessource(int typeRessource, int quantite) {
        ressources[typeRessource] += quantite;
    }

    /**
     * Permet d'échanger des ressources entre deux acteurs, le paquet 1 reçoit x quantité de ressources du type "ressource" du paquet 2
     *
     * @param p1            Paquet de ressources de l'acteur qui reçoit les ressources
     * @param p2            Paquet de ressources de l'acteur qui
     * @param typeRessource type de la ressource qui va être échangée
     * @param quantite      quantité de la ressource qui va être échangée
     */
    public static void recevoirRessource(PaquetRessources p1, PaquetRessources p2, int typeRessource, int quantite) {
        // Vérification que le type de ressource est correcte
        if (typeRessource >= Constantes.NUMERO_RESSOURCE_MIN && typeRessource <= Constantes.NUMERO_RESSOURCE_MAX) {
            // Vérifiie si la quantité à retirer est retirable pour l'acteur qui va perdre des ressources
            if (p2.estRetirable( typeRessource, quantite )) {
                p2.retirerRessource( typeRessource, quantite );
                p1.ajouterRessource( typeRessource, quantite );
            } else
                Partie.getHud().afficherMessage( "Erreur lors de l'échange", "Pas suffisamment de ressources disponibles à donner" );
        } else {
            // Ne doit jamais se produire
            Partie.getHud().afficherMessage( "Erreur lors de l'échange", "Type de ressource incorrect" );
        }
    }

    // Getter

    public int[] getRessources() {
        return ressources;
    }

    public int getProprietaire() {
        return proprietaire;
    }
}
