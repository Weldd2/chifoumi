package com.chifoumi;

/**
 * Un pion du jeu.
 */
public abstract class Pion {

    /**
     * Couleur du Pion.
     */
    private Couleur couleur;
    /**
     * Nom du Pion.
     */
    private String nom;

    /**
     * Constructeur pour créer un Pion. Généralement utilisé sans dans les classes filles.
     * @param couleur La {@link Couleur} du pion.
     * @param nom Le nom de la pièce.
     */
    protected Pion(Couleur couleur, String nom) {
        this.couleur = couleur;
        this.nom = nom;
    }

    
    /** 
     * Retourne la couleur du pion (Couleur.BLANC, Couleur.NOIR, Couleur.ORANGE, Couleur.ROUGE).
     * @return La couleur du Pion.
     */
    public Couleur getCouleur() {
        return couleur;
    }

    
    /** 
     * Retourne le nom du pion.
     * @return Le nom du Pion.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Vérifie si le pion peut battre le pion passé en paramètre.
     * @param pion Le pion à combattre.
     * @return Un boolean indiquant si ce Pion peut battre celui donnée en paramètre.
     */
    public abstract boolean peutBattre(Pion pion);
}
