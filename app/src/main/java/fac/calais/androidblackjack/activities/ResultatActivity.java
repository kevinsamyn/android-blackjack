package fac.calais.androidblackjack.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import fac.calais.androidblackjack.R;

/**
 * Cette activité à pour but d'afficher le gagnant de la partie et le score.
 */
public class ResultatActivity extends AppCompatActivity {

    // Ces constantes représentent les noms des "Extras" permettant de faire passer les nombre de points
    // du joueur et du donneur (banque) de l'activité TirageCartesActivity à ResultatActivity
    public static final String POINTS_DONNEUR = "POINTS_DONNEUR";
    public static final String POINTS_JOUEUR = "POINTS_JOUEUR";

    // Composants de l'interface
    private TextView mScoreText;    // Permet d'afficher le score final
    private TextView mResultatText; // Permet d'afficher le gagnant

    // Données de l'activité
    private Integer mPointsDonneur; // Récupéré dans l'Intent entrant
    private Integer mPointsJoueur; // Récupéré dans l'Intent entrant

    /**
     * La méthode onCreate est executée dès le lancement de l'activité
     * <p>
     * Habituellement, on récupère dans cette méthode des références vers nos composants définis
     * dans le layout. Puis on donne à nos composant leur comportement.
     * </p>
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  On récupère les valeurs passées dans l'Intent créée dans TirageCartesActivity
        final Intent intent = getIntent();
        mPointsDonneur = intent.getIntExtra(POINTS_DONNEUR, 0);
        mPointsJoueur = intent.getIntExtra(POINTS_JOUEUR, 0);

        // On récupère les références aux composants
        mResultatText = findViewById(R.id.resultatText);
        mScoreText = findViewById(R.id.scoreText);

        // Comportement du floating action button
        // Bouton flottant en bas de l'activité
        final FloatingActionButton floatingActionBtn = findViewById(R.id.fab);
        floatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Le clic sur le bouton flottant provoque l'affichage d'une modal donnant le choix au joueur de :
                // - Rejouer : Il est redirigé vers l'activité TirageCartesActivity
                // - Arreter : Il est redirigé vers l'activité HomeActivity
            }
        });

        // On affiche le résultat à l'écran
        afficherResultat();
    }


    /**
     * Affichage du résultat de la partie
     * <ol>
     * <li>Récupération des parametres de l'Intent</li>
     * <li>Affichage du score sous la forme : points joueur vs points donneur</li>
     * <li>Affichage du résultat : Victoire, égalité ou perdu</li>
     * </ol>
     */
    private void afficherResultat() {

        // Affichage du score
        mScoreText.setText(String.format(Locale.FRANCE, "%d vs %d", mPointsJoueur, mPointsDonneur));

        if (mPointsJoueur > 21) {
            mResultatText.setText(R.string.defaite);
        } else if (mPointsDonneur > 21) {
            mResultatText.setText(R.string.victoire);
        } else {
            // Affichage du résultat
            switch (mPointsDonneur.compareTo(mPointsJoueur)) {
                case -1:
                    mResultatText.setText(R.string.victoire);
                    break;
                case 0:
                    mResultatText.setText(R.string.nul);
                    break;
                case 1:
                    mResultatText.setText(R.string.defaite);
                    break;
            }
        }
    }

}
