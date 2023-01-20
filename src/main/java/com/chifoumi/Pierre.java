package com.chifoumi;

/**
 * Un {@link Pion} correspondant à la pierre.
 */
public class Pierre extends Pion {
    
    /**
     * Constructeur de la classe Pierre.
     * @param couleur La couleur de la pièce.
     */
    public Pierre(Couleur couleur) {
        super(couleur, "Pierre");
    }

    @Override
    public boolean peutBattre(Pion pion) {
        if(pion.getCouleur() == this.getCouleur()){return false;}
        if(pion.getNom() != "Ciseaux"){return false;}
        return true;
    }
}
