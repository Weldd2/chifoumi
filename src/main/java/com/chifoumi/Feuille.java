package com.chifoumi;

/**
 * Un {@link Pion} correspondant à la feuille.
 */
public class Feuille extends Pion {
    
    /**
     * Constructeur de la classe Feuille.
     * @param couleur La couleur de la pièce.
     */
    public Feuille(Couleur couleur) {
        super(couleur, "Feuille");

    }

    @Override
    public boolean peutBattre(Pion pion) {
        if(pion.getCouleur() == this.getCouleur()){return false;}
        if(pion.getNom() != "Pierre" && pion.getNom() != "Puit"){return false;}
        return true;
    }

    
}
