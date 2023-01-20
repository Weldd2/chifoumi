package com.chifoumi;
import java.util.ArrayList;


/**
 * Le plateau de jeu.
 */
public class Plateau {
    
    /**
     * Le tableau de {@link Case}.
     */
    Case[][] cases;

    /**
     * Le constructeur du {@link Plateau}.
     */
    public Plateau() {
        this.cases = new Case[7][7]; 
        creerCases();
    }


    /**
     * Permet de créer les cases.
     */
    private void creerCases() {
        this.cases[0][0] =  new Case(0, 0);
        this.cases[0][1] =  new Case(0, 1);
        this.cases[1][1] =  new Case(1, 1);
        this.cases[1][0] =  new Case(1, 0);

        this.cases[5][6] =  new Case(5, 6);
        this.cases[6][6] =  new Case(6, 6);
        this.cases[5][5] =  new Case(5, 5);
        this.cases[6][5] =  new Case(6, 5);

        this.cases[0][5] =  new Case(0, 5);
        this.cases[0][6] =  new Case(0, 6);
        this.cases[1][5] =  new Case(1, 5);
        this.cases[1][6] =  new Case(1,6);

        this.cases[5][0] =  new Case(5, 0);
        this.cases[6][0] =  new Case(0, 6);
        this.cases[5][1] =  new Case(1, 5);
        this.cases[6][1] =  new Case(1, 6);

        this.cases[2][0] = new Case(2, 0, new ArrayList<Pion>());
        this.cases[3][0] = new Case(3, 0, new ArrayList<Pion>());
        this.cases[4][0] = new Case(4, 0, new ArrayList<Pion>());

        this.cases[2][6] = new Case(2, 6, new ArrayList<Pion>());
        this.cases[3][6] = new Case(3, 6, new ArrayList<Pion>());
        this.cases[4][6] = new Case(4, 6, new ArrayList<Pion>());

        this.cases[2][2] = new Case(2,2, new ArrayList<Pion>());
        this.cases[3][2] = new Case(3,2, new ArrayList<Pion>());
        this.cases[4][2] = new Case(4,2, new ArrayList<Pion>());
        this.cases[2][3] = new Case(2,3, new ArrayList<Pion>());
        this.cases[3][3] = new Case(3,3, new ArrayList<Pion>());
        this.cases[4][3] = new Case(4,3, new ArrayList<Pion>());
        this.cases[2][4] = new Case(2,4, new ArrayList<Pion>());
        this.cases[3][4] = new Case(3,4, new ArrayList<Pion>());
        this.cases[4][4] = new Case(4,4, new ArrayList<Pion>());

        for (int i = 0; i < this.cases.length; i++) {
            this.cases[3][i] = new Case(3, i, new ArrayList<Pion>());
        }
        
        this.cases[0][2] = new Case(0, 2, new Feuille(Couleur.NOIR));
        this.cases[5][2] = new Case(5, 2, new Feuille(Couleur.BLANC));

        this.cases[1][4] = new Case(1, 4, new Feuille(Couleur.NOIR));
        this.cases[6][4] = new Case(6, 4, new Feuille(Couleur.BLANC));

        this.cases[0][4] = new Case(0, 4, new Pierre(Couleur.NOIR));
        this.cases[1][3] = new Case(1, 3, new Pierre(Couleur.NOIR));
        
        this.cases[5][3] = new Case(5, 3, new Pierre(Couleur.BLANC));
        this.cases[6][2] = new Case(6, 2, new Pierre(Couleur.BLANC));

        this.cases[0][3] = new Case(0, 3, new Ciseaux(Couleur.NOIR));
        this.cases[1][2] = new Case(1, 2, new Ciseaux(Couleur.NOIR));
        
        this.cases[5][4] = new Case(5, 4, new Ciseaux(Couleur.BLANC));
        this.cases[6][3] = new Case(6, 3, new Ciseaux(Couleur.BLANC));

        this.cases[2][1] = new Case(2, 1, new Puit(Couleur.NOIR));
        this.cases[2][5] = new Case(2, 5, new Puit(Couleur.NOIR));
        
        this.cases[4][1] = new Case(4, 1, new Puit(Couleur.BLANC));
        this.cases[4][5] = new Case(4, 5, new Puit(Couleur.BLANC));

    }


    /**
     * Compte le nombre de pions de la couleur passée en paramètre.
     * @param couleur La couleur à compter.
     * @return Le nombre de couleurs.
     */
    public int compteCouleur(Couleur couleur){
        int compteur = 0;
        for (Case[] ca : cases) {
            for (Case c : ca) {
                if(c.getPionDessus() == null){}
                else if(c.getPionDessus().getCouleur() == couleur) {compteur+=c.getPions().size();}
            }
        }
        return compteur;
    }

    /**
     * Vérifie si le pion peut etre déplacé et l'exécute si oui.
     * @param posY La position en x de la piece à déplacer.
     * @param posX La position en y de la piece à déplacer.
     * @param newPosY La position en y où déplacer la pièce.
     * @param newPosX La position en x où déplacer la pièce.
     * @param couleur La couleur de la pièce à déplacer.
     * @return Le résultat du déplacement.
     */
    public ResultatDeplacement deplacer(int posY, int posX, int newPosY, int newPosX, Couleur couleur){
        //Vérification si la case d'origine est valide.
        if(posX > this.cases.length || posY > this.cases.length || posX < 0 || posY < 0){return ResultatDeplacement.IMPOSSIBLE;}
        
        if(this.cases[posY][posX].typeCase != TypeCase.PION){return ResultatDeplacement.IMPOSSIBLE;}

        if(this.cases[posY][posX].getPionDessus().getCouleur() != couleur){return ResultatDeplacement.IMPOSSIBLE;}
        

        //Vérification si le mouvement est licite.
        if(Math.abs(newPosX - posX) > 1 || Math.abs(newPosY - posY) > 1){return ResultatDeplacement.IMPOSSIBLE;}

        if(posX == newPosX && posY == newPosY) {
            return ResultatDeplacement.IMPOSSIBLE;
        }
        

        //Vérification si la case de destination est valide.
        if(newPosX > this.cases.length || newPosY > this.cases.length || newPosX < 0 || newPosY < 0){return ResultatDeplacement.IMPOSSIBLE;}
        
        if(this.cases[posY][posX].typeCase == TypeCase.MUR){return ResultatDeplacement.IMPOSSIBLE;}
        

        //Si tout est valide et que la destination est vide.
        if(this.cases[newPosY][newPosX].typeCase == TypeCase.VIDE){
            deplacement(this.cases[posY][posX], this.cases[newPosY][newPosX]);
            return ResultatDeplacement.REUSSI;
        }
        

        //Si tout est valide et que la destination est un pion ou une pile.
        if(this.cases[newPosY][newPosX].typeCase == TypeCase.PION){
            if(this.cases[newPosY][newPosX].getPionDessus().getCouleur() == couleur){
                deplacement(this.cases[posY][posX], this.cases[newPosY][newPosX]);
                return ResultatDeplacement.REUSSI;
            }  
            else{
                if(this.cases[posY][posX].getPionDessus().peutBattre(this.cases[newPosY][newPosX].getPionDessus())){
                    deplacement(this.cases[posY][posX], this.cases[newPosY][newPosX]);
                    return ResultatDeplacement.PRISE;
                }
                else{return ResultatDeplacement.IMPOSSIBLE;}
            }
        }

        return ResultatDeplacement.IMPOSSIBLE;
    }


    /**
     * Déplace le pion.
     * @param origine La case d'origine.
     * @param destination La case d'arrivée.
     */
    public void deplacement(Case origine, Case destination){
            if(destination.typeCase == TypeCase.PION){
                if(destination.getPionDessus().getCouleur() == origine.getPionDessus().getCouleur()){
                    destination.addPionDessus(origine.getPionDessus());
                    origine.removePionDessus();
                    return;
                }
                else{
                    switch(origine.getPionDessus().getNom()){
                        case "Pierre":
                            this.cases[destination.posY][destination.posX] = new Case(destination.posY, destination.posX, new Pierre(origine.getPionDessus().getCouleur()));
                            break;
                        case "Feuille":
                            this.cases[destination.posY][destination.posX] = new Case(destination.posY, destination.posX, new Feuille(origine.getPionDessus().getCouleur()));
                            break;
                        case "Ciseaux":
                            this.cases[destination.posY][destination.posX] = new Case(destination.posY, destination.posX, new Ciseaux(origine.getPionDessus().getCouleur()));
                            break;
                        case "Puit":
                            this.cases[destination.posY][destination.posX] = new Case(destination.posY, destination.posX, new Puit(origine.getPionDessus().getCouleur()));
                            break;
                        default :
                            break;
                    }
                    origine.removePionDessus();
                    return;
                }
            } else {
                destination.addPionDessus(origine.getPionDessus());
                origine.removePionDessus();
                return;
            }
    }
}
