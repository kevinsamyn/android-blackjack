package fac.calais.androidblackjack.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Set;
import java.util.TreeSet;

import fac.calais.androidblackjack.beans.Resultat;

import static fac.calais.androidblackjack.sqlite.BlackjackSQLite.COL_ID;
import static fac.calais.androidblackjack.sqlite.BlackjackSQLite.COL_ID_INDEX;
import static fac.calais.androidblackjack.sqlite.BlackjackSQLite.COL_SCORE_DONNEUR;
import static fac.calais.androidblackjack.sqlite.BlackjackSQLite.COL_SCORE_DONNEUR_INDEX;
import static fac.calais.androidblackjack.sqlite.BlackjackSQLite.COL_SCORE_JOUEUR;
import static fac.calais.androidblackjack.sqlite.BlackjackSQLite.COL_SCORE_JOUEUR_INDEX;

/**
 * Cette classe permet d'enregistrer et de récupérer les résultats dans la base de données
 */
public class BlackjackResultatService {


    private SQLiteDatabase bdd;

    private BlackjackSQLite blackjackSQLite;

    /**
     * Constructeur
     *
     * @param context
     */
    public BlackjackResultatService(final Context context) {
        //On crée la BDD et sa table
        blackjackSQLite = new BlackjackSQLite(context, BlackjackSQLite.DATABASE_NOM, null, BlackjackSQLite.DATABASE_VERSION);
    }

    /**
     * Ouverture d'une connexion
     */
    public void open() {
        //on ouvre la BDD en écriture
        bdd = blackjackSQLite.getWritableDatabase();
    }

    /**
     * Fermeture de la connexion
     */
    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }

    /**
     * Permet d'ajouter un résultat en BDD
     *
     * @param resultat a ajouter
     * @return long, l'id du résultat
     */
    public long insert(final Resultat resultat) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(BlackjackSQLite.COL_SCORE_DONNEUR, resultat.getScoreDonneur());
        values.put(BlackjackSQLite.COL_SCORE_JOUEUR, resultat.getScoreJoueur());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(BlackjackSQLite.TABLE_RESULTATS, null, values);
    }

    /**
     * Retourne tous les résultats
     *
     * @return Set
     */
    public Set<Resultat> findAll() {

        final Set<Resultat> resultats = new TreeSet<>();

        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        final Cursor c = bdd.query(BlackjackSQLite.TABLE_RESULTATS, new String[]{COL_ID, COL_SCORE_DONNEUR, COL_SCORE_JOUEUR}, null, null, null, null, null);

        try {
            while (c.moveToNext()) {

                final Resultat res = new Resultat(c.getInt(COL_ID_INDEX), c.getInt(COL_SCORE_DONNEUR_INDEX), c.getInt(COL_SCORE_JOUEUR_INDEX));
                resultats.add(res);
            }
        } finally {
            c.close();
        }

        return resultats;

    }

}
