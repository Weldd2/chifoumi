package com.chifoumi;

import java.util.ArrayList;

/**
 * Une case du Plateau.
 */
public class Case {
    /**
     * Un entier indiquant la position en abscisse. 
     */
    public int posX;
    /**
     * Un entier indiquant la position en abscisse .
     */
    public int posY;
    /**
     * Contient soit : MUR, VIDE ou PION.
     */
    public TypeCase typeCase;
    /**
     * Une ArrayList de pions
     */
    private ArrayList<Pion> pions;

    /**
     * Constructeur de la classe Case. Il ajoute dans l'attribut pions un ArrayList contenant un seul Pion.
     * @param posY La position en y de la case.
     * @param posX La position en y de la case.
     * @param pion La {@link Pion} à ajouter sur la case.
     */
    public Case(int posY, int posX, Pion pion) {
        this.posX = posX;
        this.posY = posY;
        this.pions = new ArrayList<Pion>();
        this.pions.add(pion);
        this.typeCase = TypeCase.PION;
    }
    /**
     * Constructeur de la classe Case. Il ajoute dans l'attribut pions la liste de pions en paramètre.
     * @param posY La position en y de la case.
     * @param posX La position en y de la case.
     * @param pions Les {@link Pion} à ajouter sur la case.
     */
    public Case(int posY, int posX, ArrayList<Pion> pions) {
        this.posX = posX;
        this.posY = posY;
        this.pions = new ArrayList<Pion>(pions);
        this.typeCase = (pions.size() > 0) ? TypeCase.PION : TypeCase.VIDE;
    }
    /**
     * Constructeur de la classe Case. Il laisse le valeur de l'attribut pions à null et ajoute le TypeCase à la case. Il est surtout utilisé pour créer des murs.
     * @param posY La position en y de la case.
     * @param posX La position en y de la case.
     */
    public Case(int posY, int posX) {
        this.posX = posX;
        this.posY = posY;
        this.typeCase = TypeCase.MUR;
    }

    
    /** 
     * Retourne les pions contenus dans la case.
     * @return La liste des pions de la case.
     */
    public ArrayList<Pion> getPions() {
        return pions;
    }

    
    /** 
     * Retourne le pion situé au dessus dans la case.
     * @return Le pion du dessus.
     */
    public Pion getPionDessus() {
        if(this.typeCase == TypeCase.PION){
            return this.pions.get(pions.size() - 1);
        }
        return null;
    }

    
    /** 
     * Ajoute un pion au dessus de la pile.
     * @param pion Le {@link Pion} à ajouter à la case.
     */
    public void addPionDessus(Pion pion) {
        if(this.typeCase != TypeCase.MUR){
            this.pions.add(pions.size(), pion);
            if(pions.size() > 0){this.typeCase = TypeCase.PION;}
        }
    }



    /** 
     * Supprime le pion du dessus.
     */
    public void removePionDessus(){
        if(this.typeCase == TypeCase.PION){
            this.pions.remove((pions.size() - 1));
            if(pions.size() == 1){this.typeCase = TypeCase.PION;}
            if(pions.size() == 0){this.typeCase = TypeCase.VIDE;}
        }
    }

    
    /** 
     * Compte le nombre de pions dans la pile.
     * @return le nombre de pions dans la pile.
     */
    public int comptePion(){
        if(this.typeCase == TypeCase.PION){
            return this.pions.size();
        }
        return 0;
    }

    
    /** 
     * Retourne un message contenant les coordonnées de la case.
     * @return Les coordonnées de la case.
     */
    public String toString(){
        return posX + " " + posY;
    }

    
    /** 
     * Retourne le type de la case (TypeCase.MUR ou TypeCase.VIDE ou TypeCase.Pion).
     * @return Le type de la case.
     */
    public TypeCase getTypeCase() {
        return typeCase;
    }

   
}
