package com.chifoumi;

/**
 * Un {@link Pion} correspondant au puit.
 */
public class Puit extends Pion{

    /**
     * Constructeur de la classe Puit.
     * @param couleur La couleur de la pièce.
     */
    public Puit(Couleur couleur) {
        super(couleur, "Puit");
    }

    
    @Override
    public boolean peutBattre(Pion pion) {
        if(pion.getCouleur() == this.getCouleur()){return false;}
        if(pion.getNom() != "Pierre" && pion.getNom() != "Ciseaux"){return false;}
        return true;
    }
}