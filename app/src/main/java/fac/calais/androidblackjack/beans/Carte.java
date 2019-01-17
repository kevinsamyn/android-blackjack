package fac.calais.androidblackjack.beans;

import fac.calais.androidblackjack.enums.CouleurCarteEnum;

/**
 * Classe représentant une carte
 */
public class Carte {
    /**
     * Valeur de la carte en point
     */
    private Integer valeur;
    /**
     * Couleur de la carte
     */
    private CouleurCarteEnum couleur;

    public Carte(int pValeur, CouleurCarteEnum pCouleur) {
        this.valeur = pValeur;
        this.couleur = pCouleur;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public CouleurCarteEnum getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurCarteEnum couleur) {
        this.couleur = couleur;
    }
}
