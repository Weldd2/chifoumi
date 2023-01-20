package com.chifoumi;

/**
 * Un joueur du jeu.
 */
public class Joueur {
    /**
     * La {@link Couleur} du joueur.
     */
    public Couleur couleur;

    /**
     * Le {@link Plateau} sur lequel joue le joueur.
     */
    public Plateau plateau;

    /**
     * Le constructeur du joueur.
     * @param couleur La {@link Couleur} du joueur.
     * @param plateau Le {@link Plateau} sur lequel joue le joueur.
     */
    public Joueur(Couleur couleur, Plateau plateau){
        this.couleur = couleur;
        this.plateau = plateau;
    }

    
    /** 
     * Vérifie si le joueur a perdu.
     * @return True ou False, selon si le joueur a perdu.
     */
    public boolean aPerdu(){
        if(plateau.compteCouleur(this.couleur) == 0){return true;}
        return false;
    }
}