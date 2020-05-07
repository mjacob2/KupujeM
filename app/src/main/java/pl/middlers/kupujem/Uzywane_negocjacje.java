package pl.middlers.kupujem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import pl.middlers.kupujem.R;

public class Uzywane_negocjacje extends AppCompatActivity {
    //Declaring Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uzywane_negocjacje);

        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_negocjuj_cene);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?
    }
}
