package fac.calais.androidblackjack.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import fac.calais.androidblackjack.R;
import fac.calais.androidblackjack.beans.Carte;

public class CarteRecyclerViewAdapter extends RecyclerView.Adapter<CarteRecyclerViewAdapter.CarteHolder> {

    /**
     * Liste des cartes devant être affichées par l'Adapter
     */
    private final List<Carte> cartes;

    /**
     * Constructeur de l'adapter prenant en parametre la liste des cartes qu'il doit représenter.
     *
     * @param cartes List<Carte>
     */
    public CarteRecyclerViewAdapter(final List<Carte> cartes) {
        this.cartes = cartes;
    }

    @NonNull
    @Override
    public CarteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_carte, viewGroup, false);

        return new CarteHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CarteHolder carteHolder, int positionCarte) {
        final Carte carte = cartes.get(positionCarte);
        carteHolder.valeur.setText(String.format(Locale.FRANCE, "%d", carte.getValeur()));
        switch (carte.getCouleur()) {
            case COEUR:
                carteHolder.couleur.setImageResource(R.drawable.coeur);
                break;
            case CARREAU:
                carteHolder.couleur.setImageResource(R.drawable.carreau);
                break;
            case PIQUE:
                carteHolder.couleur.setImageResource(R.drawable.pique);
                break;
            case TREFLE:
                carteHolder.couleur.setImageResource(R.drawable.trefle);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return cartes.size();
    }

    /**
     * Holder permettant de dessiner chaque carte individuellement dans la liste
     */
    public static class CarteHolder extends RecyclerView.ViewHolder {

        public ImageView couleur;
        public TextView valeur;

        public CarteHolder(@NonNull View itemView) {
            super(itemView);

            couleur = itemView.findViewById(R.id.carteCouleurImg);
            valeur = itemView.findViewById(R.id.carteValeurTxt);
        }
    }
}
