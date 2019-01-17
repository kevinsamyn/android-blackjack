package fac.calais.androidblackjack.beans;

/**
 * Représente un résultat enregistré en base de données
 */
public class Resultat implements Comparable<Resultat>{

    private Integer id;
    private Integer scoreDonneur;
    private Integer scoreJoueur;

    public Resultat(Integer id, Integer scoreDonneur, Integer scoreJoueur) {
        this.id = id;
        this.scoreDonneur = scoreDonneur;
        this.scoreJoueur = scoreJoueur;
    }

    /**
     * Constructeur
     */
    public Resultat(Integer scoreDonneur, Integer scoreJoueur) {
        this.scoreDonneur = scoreDonneur;
        this.scoreJoueur = scoreJoueur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScoreDonneur() {
        return scoreDonneur;
    }

    public void setScoreDonneur(Integer scoreDonneur) {
        this.scoreDonneur = scoreDonneur;
    }

    public Integer getScoreJoueur() {
        return scoreJoueur;
    }

    public void setScoreJoueur(Integer scoreJoueur) {
        this.scoreJoueur = scoreJoueur;
    }

    @Override
    public int compareTo(Resultat o) {
        return id.compareTo(o.getId());
    }
}
