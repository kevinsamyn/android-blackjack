package fac.calais.androidblackjack.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

import fac.calais.androidblackjack.R;
import fac.calais.androidblackjack.adapters.CarteRecyclerViewAdapter;
import fac.calais.androidblackjack.beans.Carte;
import fac.calais.androidblackjack.enums.CouleurCarteEnum;

/**
 * Cette activité permet de faire le tirage des cartes du joueur et de la banque
 */
public class TirageCartesActivity extends AppCompatActivity {

    // Composants de la vue
    private TextView mPointsJoueurText;   // Points du joueur
    private TextView mPointsDonneurText;   // Points du donneur
    private RecyclerView mCartesDonneurRV; // Liste des cartes du donneur
    private RecyclerView mCartesJoueurRV; // Liste des cartes du joueur
    private Button mEncoreBtn;  // Bouton permettant de demander une carte
    private Button mStopBtn; // Bouton permettant d'arreter la distribution de carte

    // Adapters permettant d'afficher les listes de la vue
    private CarteRecyclerViewAdapter mCartesJoueurRVAdapter;
    private CarteRecyclerViewAdapter mCartesDonneurRVAdapter;

    // Données utilisées par l'activité
    private List<Carte> mCartesJoueur = new ArrayList<>();
    private List<Carte> mCartesDonneur = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirage_cartes);

        // On récupère les références vers nos composants graphique
        mPointsJoueurText = findViewById(R.id.pointsJoueurText);
        mPointsDonneurText = findViewById(R.id.pointsDonneurText);
        mCartesJoueurRV = findViewById(R.id.cartesJoueurRV);
        mCartesDonneurRV = findViewById(R.id.cartesDonneurRV);
        mEncoreBtn = findViewById(R.id.encoreBtn);
        mStopBtn = findViewById(R.id.stopBtn);

        // Comportement des boutons
        mEncoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEncoreBtnClick();
            }
        });
        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStopBtnClick();
            }
        });

        // On cache les éléments non utile au début
        mStopBtn.setVisibility(View.INVISIBLE);
        mPointsDonneurText.setVisibility(View.INVISIBLE);

        // Création d'un LinearLayout pour indiquer au RecyclerView d'afficher les éléments les uns à la suite des autres
        final LinearLayoutManager llmJoueur = new LinearLayoutManager(this);
        llmJoueur.setOrientation(LinearLayoutManager.HORIZONTAL);

        // Initialisation de l'Adapter pour l'affichage de la liste des cartes du joueur
        mCartesJoueurRVAdapter = new CarteRecyclerViewAdapter(mCartesJoueur);
        mCartesJoueurRV.setLayoutManager(llmJoueur);
        mCartesJoueurRV.setAdapter(mCartesJoueurRVAdapter);

        // Initialisation de l'Adapter pour l'affichage de la liste des cartes de la banque
        final LinearLayoutManager llmDonneur = new LinearLayoutManager(this);
        llmDonneur.setOrientation(LinearLayoutManager.HORIZONTAL);
        mCartesDonneurRVAdapter = new CarteRecyclerViewAdapter(mCartesDonneur);
        mCartesDonneurRV.setLayoutManager(llmDonneur);
        mCartesDonneurRV.setAdapter(mCartesDonneurRVAdapter);
    }

    /**
     * Comportement du bouton encoreBtn
     *
     * <ol>
     * <li>Tire au sort une valeur de carte</li>
     * <li>Ajoute cette valeur dans la liste des cartes tirées</li>
     * <li>Met à jour l'affichage : nombre de points et liste des cartes</li>
     * <li>Si 5 cartes tirées ou points > 20, on rend invisible le bouton encore</li>
     * </ol>
     */
    private void onEncoreBtnClick() {
        // Tirage d'une valeur de carte entre 1 et 11.
        final Carte carte = tirerUneCarte();
        mCartesJoueur.add(carte);

        // On demande à l'adapter de redessiner les cartes du joueur
        mCartesJoueurRVAdapter.notifyDataSetChanged();

        // Mise à jour de l'affichage du nombre de point du joueur
        final Integer sommePoints = sommePoints(mCartesJoueur);
        mPointsJoueurText.setText(String.valueOf(sommePoints(mCartesJoueur)) + " point(s)");

        // On cache le bouton encore si :
        // - le nombre de carte maximal est atteint
        // - le nombre de points est supérieur à 20.
        if (mCartesJoueur.size() >= 5 || sommePoints > 20) {
            mEncoreBtn.setVisibility(View.INVISIBLE);
        }

        // On rend le bouton stop visible
        mStopBtn.setVisibility(View.VISIBLE);
        mPointsDonneurText.setVisibility(View.VISIBLE);
    }

    /**
     * Cette méthode retourne une carte tirée au hasard
     * <p>
     * Elle est utilisée pour les cartes du joueur et les cartes de la banque
     * </p>
     *
     * @return Carte
     */
    @NonNull
    private Carte tirerUneCarte() {
        // On tire une valeur
        final int valeurCarte = RandomUtils.nextInt(1, 11);
        // On tire une couleur
        final CouleurCarteEnum couleurCarte = CouleurCarteEnum.values()[RandomUtils.nextInt(0, 3)];

        // On construit et retourne la carte
        return new Carte(valeurCarte, couleurCarte);
    }

    /**
     * Retourne la somme des valeurs des cartes de la liste passées en parametres
     *
     * <p>
     * La somme est faite en bouclant sur la List<Integer> mCartesJoueur
     * </p>
     *
     * @return Integer positif
     */
    private Integer sommePoints(final List<Carte> pCartes) {
        Integer somme = 0;
        for (final Carte mCartesTiree : pCartes) {
            somme += mCartesTiree.getValeur();
        }

        return somme;
    }

    /**
     * Comportement du bouton stopBtn
     *
     * <ol>
     * <li>On tire les cartes du donneur jusqu'a avoir un nombre de points >= 17</li>
     * <li>On lance l'activité Resultat en passant a parametres : </li>
     * <ul>
     * <li>Le nombre de point du joueur</li>
     * <li>Le nombre de point du donneur</li>
     * </ul>
     * </ol>
     */
    private void onStopBtnClick() {

        mEncoreBtn.setVisibility(View.INVISIBLE);

        // On tire les cartes du donneur
        if(mCartesDonneur.isEmpty()) {
            tirerCartesBanque();
        }else if(sommePoints(mCartesDonneur) >= 17){
            lancerResultatActivity();
        }
    }

    private void lancerResultatActivity() {
        // On lance l'activité Resultat en lui passant les scores du joueur et de la banque
        final Intent i = new Intent(TirageCartesActivity.this, ResultatActivity.class);
        i.putExtra(ResultatActivity.POINTS_DONNEUR, sommePoints(mCartesDonneur));
        i.putExtra(ResultatActivity.POINTS_JOUEUR, sommePoints(mCartesJoueur));

        // On démarre l'activité ResultatActivity
        startActivity(i);

        // On termine l'activité courante
        finish();
    }

    private void tirerCartesBanque() {

        mStopBtn.setVisibility(View.INVISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final Carte carte = tirerUneCarte();
                mCartesDonneur.add(carte);


                TirageCartesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCartesDonneurRVAdapter.notifyDataSetChanged();
                        mPointsDonneurText.setText(String.valueOf(sommePoints(mCartesDonneur)) + " point(s)");
                    }
                });

                if(sommePoints(mCartesDonneur)<17){
                    tirerCartesBanque();
                }else{

                    TirageCartesActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mStopBtn.setText(R.string.btn_voir_resultat_label);
                            mStopBtn.setVisibility(View.VISIBLE);
                        }
                    });

                }



            }
        }).start();


    }
}
