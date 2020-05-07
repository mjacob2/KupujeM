package pl.middlers.kupujem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Nowe_zaplac extends AppCompatActivity {
    //Declaring Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nowe_zaplac);

        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_zaplac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?
    }

}
