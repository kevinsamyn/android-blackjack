package fac.calais.androidblackjack.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fac.calais.androidblackjack.R;

/**
 * Cette activité, désigné comme étant LAUNCHER fans AndroidManifest est lancé au démarrage de l'application
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // On défini le comportement du bouton flottant
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lancerTirageCartes();
            }
        });
    }

    /**
     * Méthode permettant de démarrer l'activité de tirage de carte
     */
    private void lancerTirageCartes() {
        final Intent i = new Intent(HomeActivity.this, TirageCartesActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Création et affichage du menu dans la topbar
     *
     * @param menu de l'activité
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    /**
     * Methode permettant de définir le comportement de chaque item du menu
     *
     * @param item cliqué
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // On récupère l'id de l'item cliqué
        // Cet id correspond à l'attribut android:id défini dans menu_home.xml
        int id = item.getItemId();

        // On traite les différents id. Ici nous n'en avons qu'un
        switch (id) {
            case R.id.menu_home_historique:
                final Intent i = new Intent(HomeActivity.this, HistoriqueActivity.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
