package pl.middlers.kupujem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Nowe_prospekt extends AppCompatActivity {

    //Declaring Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nowe_prospekt);

        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_Prospekt_emisyjny);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?
    }
}
