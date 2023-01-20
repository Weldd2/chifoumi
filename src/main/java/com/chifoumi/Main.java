package com.chifoumi;

/**
 * La classe contenant la méthode Main.
 * @author Jules Mignotte, Antoine Mura.
 */
public class Main {

    /**
     * Constructeur de la classe Main
     */
    public Main(){}

    /**
     * Permet de lancer le jeu.
     * @param args Un tableau de paramètres.
     */
     public static void main(String[] args) {
        Jeu jeu = new Jeu(2);

        Joueur gagnant = jeu.joueurs.get(0);
        
        int indexJoueurActif = jeu.initJeu();
        
        jeu.jouer(indexJoueurActif);
        
        jeu.sauvegarde.deleteSauvegarde();
        gagnant = jeu.resultatPartie();
        System.out.println("Le joueur " + gagnant.couleur + " a gagné la partie!");
    }
    
}
