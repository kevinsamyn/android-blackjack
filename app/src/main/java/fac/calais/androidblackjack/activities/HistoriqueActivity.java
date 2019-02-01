package fac.calais.androidblackjack.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fac.calais.androidblackjack.R;
import fac.calais.androidblackjack.adapters.HistoriqueListAdapter;
import fac.calais.androidblackjack.beans.Resultat;
import fac.calais.androidblackjack.sqlite.BlackjackResultatService;

public class HistoriqueActivity extends AppCompatActivity {

    private List<Resultat> mResultats = new ArrayList<>();

    private ListView mHistoriqueList;
    private HistoriqueListAdapter mHistoriqueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        mHistoriqueAdapter = new HistoriqueListAdapter(HistoriqueActivity.this, mResultats);
        mHistoriqueList = findViewById(R.id.historique_list);
        mHistoriqueList.setAdapter(mHistoriqueAdapter);

        final BlackjackResultatService service = new BlackjackResultatService(HistoriqueActivity.this);
        service.open();
        mResultats.addAll(service.findAll());
        service.close();

        mHistoriqueAdapter.notifyDataSetChanged();
    }
}
