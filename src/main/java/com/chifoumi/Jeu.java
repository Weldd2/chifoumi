package com.chifoumi;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.awt.image.BufferedImage;

/**
 * Le jeu de chifoumi.
 */
public class Jeu {

    private Scanner sc = new Scanner(System.in);


    private boolean loaded = false;

    /**
     * Une liste de joueurs.
     */
    public ArrayList<Joueur> joueurs;

    /**
     * Le joueur dont c'est le tour.
     */
    public Joueur joueurActuel;

    /**
     * Le {@link Plateau} de jeu.
     */
    public Plateau plateau;

    private int[][] tabPosition;

    private int nbImage;

    private BufferedImage[] tabImages;

    private BufferedImage ciseauxBlancImg, pierreBlancImg, feuilleBlancImg, puitBlancImg, ciseauxNoirImg, pierreNoirImg, feuilleNoirImg, puitNoirImg, murImg, videImg;

    private Fenetre fenetre;
    
    /**
     * Un attribut permettant de gérer la sauvegarde du jeu
     */
    public Sauvegarde sauvegarde;

      
    /**
     * Le constructeur du {@link Jeu}.
     * @param nbJoueur Le nombre de joueurs du jeu.
     */
    public Jeu(int nbJoueur) {
        this.joueurs = new ArrayList<Joueur>();
        this.tabPosition = new int[49+1][2];
        this.tabImages = new BufferedImage[49+1];
        this.plateau = new Plateau();

        initListeJoueur(nbJoueur);
        this.sauvegarde = new Sauvegarde();
        propositionChargement();

        fillBufferImage();

        this.update();

        this.fenetre = new Fenetre(this);
    }

    /**
     * Initialise le jeu.
     * @return L'index du joueur actif.
     */
    public int initJeu() {
        if(!this.loaded){
            int indexJoueurActif = 0;
            this.joueurActuel = this.joueurs.get(indexJoueurActif);
        }
        int indexJoueurActif = this.joueurs.indexOf(this.joueurActuel);
        
        return indexJoueurActif;
    }

    /**
     * Rempli le buffer d'images.
     */
    private void fillBufferImage() {
        try {
            ciseauxBlancImg = ImageIO.read(new File("./images/ciseaux_blanc.jpg"));
            pierreBlancImg = ImageIO.read(new File("./images/pierre_blanc.jpg"));
            feuilleBlancImg = ImageIO.read(new File("./images/feuille_blanc.jpg"));
            puitBlancImg = ImageIO.read(new File("./images/puits_blanc.jpg"));
            ciseauxNoirImg = ImageIO.read(new File("./images/ciseaux_noir.jpg"));
            pierreNoirImg = ImageIO.read(new File("./images/pierre_noir.jpg"));
            feuilleNoirImg = ImageIO.read(new File("./images/feuille_noir.jpg"));
            puitNoirImg = ImageIO.read(new File("./images/puits_noir.jpg"));
            murImg = ImageIO.read(new File("./images/mur.jpg"));
            videImg = ImageIO.read(new File("./images/vide.jpg"));
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
    }

    /**
     * Demande au joueur s'il souhaite charger la dernière partie sauvegardée (ne fait rien si aucune partie n'est sauvegardée).
     */
    private void propositionChargement() {
        if(new File("sauvegarde.txt").exists()) {
            System.out.println("Souhaitez vous charger la dernière partie sauvegardée ?");
            System.out.println("-Oui\t-Non");
            if("oui".equals(sc.nextLine().toLowerCase())) {
                this.sauvegarde.loadJeu(this);
            }
        }
    }   

    /**
     * Initialise la liste des joueurs.
     * @param nbJoueur Le nombre de joueurs de la partie.
     */
    private void initListeJoueur(int nbJoueur) {
        Couleur[] couleurs = {Couleur.BLANC, Couleur.NOIR, Couleur.ROUGE, Couleur.ORANGE};
        for(int i = 0; i < nbJoueur; i++){
            this.joueurs.add(new Joueur(couleurs[i], this.plateau));
        }
    }

    /**
     * Met à jour le visuel.
     */
    private void update(){
        int nbImage = 0;
        for(int y=0;y<7;y++){
            for(int x=0;x<7;x++){
                    nbImage++;
                    this.tabPosition[nbImage][0] = x*64;
                    this.tabPosition[nbImage][1] = y*64;
                    if(this.plateau.cases[y][x].typeCase == TypeCase.VIDE){this.tabImages[nbImage] = videImg;}
                    else if(this.plateau.cases[y][x].typeCase == TypeCase.MUR){this.tabImages[nbImage] = murImg;}
                    else if(this.plateau.cases[y][x].getPionDessus().getNom() == "Puit" && this.plateau.cases[y][x].getPionDessus().getCouleur() == Couleur.BLANC){this.tabImages[nbImage] = puitBlancImg;}
                    else if(this.plateau.cases[y][x].getPionDessus().getNom() == "Puit" && this.plateau.cases[y][x].getPionDessus().getCouleur() == Couleur.NOIR){this.tabImages[nbImage] = puitNoirImg;}
                    else if(this.plateau.cases[y][x].getPionDessus().getNom() == "Feuille" && this.plateau.cases[y][x].getPionDessus().getCouleur() == Couleur.BLANC){this.tabImages[nbImage] = feuilleBlancImg;}
                    else if(this.plateau.cases[y][x].getPionDessus().getNom() == "Feuille" && this.plateau.cases[y][x].getPionDessus().getCouleur() == Couleur.NOIR){this.tabImages[nbImage] = feuilleNoirImg;}
                    else if(this.plateau.cases[y][x].getPionDessus().getNom() == "Pierre" && this.plateau.cases[y][x].getPionDessus().getCouleur() == Couleur.BLANC){this.tabImages[nbImage] = pierreBlancImg;}
                    else if(this.plateau.cases[y][x].getPionDessus().getNom() == "Pierre" && this.plateau.cases[y][x].getPionDessus().getCouleur() == Couleur.NOIR){this.tabImages[nbImage] = pierreNoirImg;}
                    else if(this.plateau.cases[y][x].getPionDessus().getNom() == "Ciseaux" && this.plateau.cases[y][x].getPionDessus().getCouleur() == Couleur.BLANC){this.tabImages[nbImage] = ciseauxBlancImg;}
                    else if(this.plateau.cases[y][x].getPionDessus().getNom() == "Ciseaux" && this.plateau.cases[y][x].getPionDessus().getCouleur() == Couleur.NOIR){this.tabImages[nbImage] = ciseauxNoirImg;}
            }
        }
        this.nbImage = nbImage;
    }

    /**
     * Demande à l'utilisateur à quelles coordonnées sont le pion qu'il souhaite déplacer, et où.
     * @return la liste des coordonnées rentrées.
     */
    private ArrayList<Integer> demandeCoordonnees() {
        ArrayList<Integer> list = new ArrayList<Integer>();

        while(true) {
            try { 
                System.out.println("OrigineY");
                list.add(sc.nextInt());
                System.out.println("OrigineX");
                list.add(sc.nextInt());
        
                System.out.println("DestinationY");
                list.add(sc.nextInt());
                System.out.println("DestinationX");
                list.add(sc.nextInt());
                break;
            } catch(Exception e) {
                list.clear();
                System.err.println("Vous devez rentrer une coordonnée valide");
                sc.nextLine();
            }
        }
        return list;
    }

    /**
     * Fait jouer le joueur.
     * @param joueur Le joueur qui va jouer.
     */
    private void tour(Joueur joueur){
        boolean finDuTour = false;
        ResultatDeplacement resultatDeplacement;
        
        while(!finDuTour){
            ArrayList<Integer> coordonnees = demandeCoordonnees();

            resultatDeplacement = this.plateau.deplacer(coordonnees.get(0), coordonnees.get(1), coordonnees.get(2), coordonnees.get(3), joueur.couleur);
            
            if(resultatDeplacement == ResultatDeplacement.IMPOSSIBLE){
                System.out.println("Déplacement impossible, retente.");
            }
            else if(resultatDeplacement == ResultatDeplacement.REUSSI){
                System.out.println("Déplacement réussi, fin du tour.");
                finDuTour = true;
                this.update();
                this.fenetre.majIHM();
            }
            else{System.out.println("Beau coup! Tu peux rejouer.");
                this.update();
                this.fenetre.majIHM();
            }
        }
        return;
    }

    /**
     * Vérifie si une prise est possible dans le jeu. Utilisée pour vérifier une condition de victoire.
     * @return True ou False, selon si la prise est possible.
     */
    private boolean prisePossible(){
        ArrayList<Joueur> autreJoueurs = new ArrayList<Joueur>();
        ArrayList<Pion> pionsJoueurActuel = new ArrayList<Pion>();
        ArrayList<Pion> pionsJoueurAutres = new ArrayList<Pion>();
        boolean returnValue = false;
        for (Joueur joueur : this.joueurs) {
            pionsJoueurActuel.clear();
            pionsJoueurAutres.clear();
            autreJoueurs = (ArrayList<Joueur>)this.joueurs.clone();
            autreJoueurs.remove(joueur);
            for (Case[] colonne : this.plateau.cases) {
                for (Case c : colonne) {
                    if(c.getTypeCase() == TypeCase.PION){
                        if(c.getPionDessus().getCouleur() == joueur.couleur){
                            pionsJoueurActuel.add(c.getPionDessus());
                        }
                        else{pionsJoueurAutres.add(c.getPionDessus());}
                    }
                }
            }
            returnValue = aUnePrisePossible(pionsJoueurActuel, pionsJoueurAutres);
        }
        return returnValue;
    }

    /**
     * Vérifie pour les pions d'un joueur actuel si une prise est encore possible.
     * @param pionsJoueurActuel Les pions du joueur dont on veut vérifier la victoire.
     * @param pionsJoueurAutres Les pions des autres joueurs.
     * @return True ou False, selon si la prise est possible.
     */
    private boolean aUnePrisePossible(ArrayList<Pion> pionsJoueurActuel, ArrayList<Pion> pionsJoueurAutres) {
        boolean returnValue = false;
        for (Pion pionActuel : pionsJoueurActuel) {
            for (Pion pionCible : pionsJoueurAutres) {
                if(pionActuel.peutBattre(pionCible)){
                    returnValue = true;
                }
            }
        }
        return returnValue;
    }

    /**
     * Calcule les points de chaque joueur.
     * @return Le joueur ayant gagné la partie.
     */
    public Joueur resultatPartie(){
        Joueur joueurEnTete = this.joueurs.get(0);
        float pointsMax = 0;
        float points = 0;
        for (Joueur joueur : this.joueurs) {
            points = plateau.compteCouleur(joueur.couleur);
            if(joueur == this.joueurs.get(this.joueurs.size() -1)){points += 0.5;}
            if(points > pointsMax){
                joueurEnTete = joueur;
                pointsMax = points;
            }
            System.out.println(joueur.couleur + ": " + points + " points!");
        }
        return joueurEnTete;
    }

    /**
     * Boucle faisant tourner le jeu.
     * @param indexJoueurActif La place du joueur actif.
     */
    public void jouer(int indexJoueurActif) {
        boolean fini = false;
        int nbPerdant = 0;
        while(!fini){
            if(!this.joueurActuel.aPerdu()){
                if(!this.prisePossible()){                    
                    fini = true;
                    break;
                }  
                System.out.println(this.joueurActuel.couleur + " a toi de jouer!");
                this.tour(this.joueurActuel);
                this.update();
                this.fenetre.majIHM();

                for (Joueur j : this.joueurs) {
                    if (j.aPerdu()){nbPerdant++;}
                }
                if(nbPerdant == this.joueurs.size() - 1){
                    fini = true;
                    break;
                }
                nbPerdant = 0;
            }
            if(indexJoueurActif == this.joueurs.size()-1){indexJoueurActif = 0;}
            else{indexJoueurActif++;}
            this.joueurActuel = this.joueurs.get(indexJoueurActif);
            try {
                this.sauvegarde.saveJeu(this);
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }

    /**
     * retourne le nombre d'images.
     * @return le nombre d'images.
     */
    public int getNbImage(){
        return this.nbImage;
    }

    /**
     * Retourne une positon dans le tableau de Position.
     * @param index L'index à rechercher.
     * @return la position dans le tableau.
     */
    public int[] getPosition(int index){
        return this.tabPosition[index];
    }

    /**
     * Renvoie une image à l'index associé.
     * @param index L'index à rechercher.
     * @return l'image correspondant à l'index.
     */
    public BufferedImage getImage(int index) {
        return this.tabImages[index];
    }

    /**
     * Set la valeur de loaded.
     * @param loaded L'indicateur de chargement du jeu lors du démarrage du jeu.
     */
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}   