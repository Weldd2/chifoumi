package com.chifoumi;

/**
 * Indique le type de retour de la méthode deplacer() du Plateau.
 */
public enum ResultatDeplacement {
    /**
     * Indique un déplacement illicite.
     */
    IMPOSSIBLE,
    /**
     * Indique un déplacement normal.
     */
    REUSSI,
    /**
     * Indique un déplacement réussi ayant comme finalité une prise de pion adverse.
     */
    PRISE
}