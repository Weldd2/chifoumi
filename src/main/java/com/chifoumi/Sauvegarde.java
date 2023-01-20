package com.chifoumi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Une classe permettant de gérer la sauvegarde d'un jeu.
 */
public class Sauvegarde {
    
    private String emplacementSauvegarde;

    /**
     * Le constructeur de la classe gérant la sauvegarde.
     */
    public Sauvegarde() {
        this.emplacementSauvegarde = "sauvegarde.txt";
    }

    /**
     * Charge le jeu sauvegardé.
     * @param jeu Le jeu à modifier.
     */
    public void loadJeu(Jeu jeu) {
        System.out.println("Chargement de la dernière partie sauvegardée...");  
        String jsonTxt = getSauvegardeTxt();
        
        try {
            JSONObject json = new JSONObject(jsonTxt);
            for (Joueur joueur : jeu.joueurs) {
                if(joueur.couleur.toString().equals(json.getString("actualPlayerColor"))) {
                    jeu.joueurActuel = joueur;
                }
            }
            extractDataFromSave(jeu, json, "cases");
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("Chargement réussi");

        jeu.setLoaded(true);
    }

    /**
     * Supprime la sauvegarde du jeu. Utilisée en fin de partie.
     */
    public void deleteSauvegarde() {
        File file = new File(this.emplacementSauvegarde);
        file.delete();
    }

    /**
     * Transforme les données de la sauvegarde dans le plateau
     * @param j
     * @param key
     */
    private void extractDataFromSave(Jeu jeu, JSONObject j, String key) {
        JSONArray pion = (JSONArray)j.get("cases");
        for (int i = 0; i < pion.length(); i++) {
            JSONArray temp = pion.getJSONArray(i);
            for(int k = 0; k < temp.length(); k++) {
                JSONObject temp2 = temp.getJSONObject(k);
                if(temp2.has("typeCase")) {
                    switch(temp2.getString("typeCase")) {
                        case "MUR" : jeu.plateau.cases[i][k] = new Case(i, k); break;
                        case "VIDE" : jeu.plateau.cases[i][k] = new Case(i, k, new ArrayList<Pion>(){}); break;
                        case "PION" : 
                            ArrayList<Pion> listPion = new ArrayList<Pion>();
                            JSONArray pionArray = temp2.getJSONArray("pions");
                            for (int l = 0; l < pionArray.length(); l++) {
                                JSONObject jsonObject = pionArray.getJSONObject(l);
                                String nom = jsonObject.getString("nom");
                                String couleur = jsonObject.getString("couleur");

                                Pion p = creerPionAvecNom(nom, stringToCouleur(couleur));
                                listPion.add(p);
                            }
                            jeu.plateau.cases[i][k] = new Case(i, k, listPion);
                        break;
                        default : break;
                    }
                    
                }
            }
        }
    }

    /**
     * Créé un Pion avec son nom et sa couleur
     * @param Nom
     * @param couleur
     * @return Un pion.
     */
    private Pion creerPionAvecNom(String nom, Couleur couleur) {
        Pion p;
        switch(nom) {
            case "Feuille": p = (new Feuille(couleur)); break;
            case "Pierre": p = (new Pierre(couleur)); break;
            case "Puit": p = (new Puit(couleur)); break;
            case "Ciseaux": p = (new Ciseaux(couleur)); break;
            default : return null;
        }
        return p;
    } 
    
    /**
     * Transforme une chaine de caractère en la couleur correspondante dans l'enumération Couleur.
     * @param couleur
     * @return la couleur associée
     */
    private Couleur stringToCouleur(String couleur) {
        switch(couleur) {
            case "BLANC": return Couleur.BLANC;
            case "NOIR": return Couleur.NOIR;
            case "ORANGE": return Couleur.ORANGE;
            case "ROUGE": return Couleur.ROUGE;
            default : return null;
        }
    }

    /**
     * Transforme la sauvegarde en une chaine de caractères.
     * @return le contenu du fichier de sauvegarde
     */
    private String getSauvegardeTxt() {
        File file = new File("sauvegarde.txt");
        String jsonTxt = "";
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                jsonTxt+=line+"\n";
            } 
            scan.close(); 
        } catch (Exception e) {
            System.err.println(e);
        }
        return jsonTxt;
    }
    
    /** 
     * Créé une sauvegarde du plateau et du joueur actuel.
     * @param jeu Le jeu dont on souhaite faire la sauvegarde.
     * @exception FileNotFoundException L'exception est levée si le fichier n'est pas trouvé.
     * @exception UnsupportedEncodingException L'Exception est levée si l'encodage n'est pas supporté.
     */
    public void saveJeu(Jeu jeu) throws FileNotFoundException, UnsupportedEncodingException {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("cases", jeu.plateau.cases);
        jsonObj.put("actualPlayerColor", jeu.joueurActuel.couleur);

        PrintWriter myFile = new PrintWriter("sauvegarde.txt", "UTF-8");
        myFile.println(jsonObj.toString(4));
        myFile.close();
    }

    
}
