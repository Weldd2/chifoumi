package com.chifoumi;

/**
 * Un {@link Pion} correspondant aux ciseaux.
 */
public class Ciseaux extends Pion {
    
    /**
     * Constructeur de la classe Ciseaux.
     * @param couleur La couleur de la pièce.
     */
    public Ciseaux(Couleur couleur) {
        super(couleur, "Ciseaux");
    }


    public boolean peutBattre(Pion pion) {
        if(pion.getCouleur() == this.getCouleur()){return false;}
        if(pion.getNom() != "Feuille"){return false;}
        return true;
    }

}
