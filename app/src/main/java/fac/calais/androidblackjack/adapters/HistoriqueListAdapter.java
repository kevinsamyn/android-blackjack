package fac.calais.androidblackjack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fac.calais.androidblackjack.beans.Resultat;

/**
 * Cet adapter permet de construire les item de la liste des résultats sur {@link fac.calais.androidblackjack.activities.HistoriqueActivity}
 */
public class HistoriqueListAdapter extends ArrayAdapter<Resultat> {

    public HistoriqueListAdapter(final Context context, final List<Resultat> pResultats) {
        super(context, 0, pResultats);

    }

    /**
     * Cette méthode construit un item de la liste
     *
     * @param position    de l'objet a affiché dans la liste pResultats
     * @param convertView la view représentant l'objet
     * @param parent
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Resultat item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            // simple_list_item_1 est un layout mis à disposition par le SDK android pour afficher un simple text
            int layoutId = android.R.layout.simple_list_item_1;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        // On récupère une référence vers le text de
        final TextView text = convertView.findViewById(android.R.id.text1);

        // On y place le score.
        text.setText(item.getScoreDonneur() + " " + item.getScoreJoueur());

        return convertView;
    }

}
