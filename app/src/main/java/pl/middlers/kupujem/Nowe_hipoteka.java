package pl.middlers.kupujem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Nowe_hipoteka extends AppCompatActivity {
    //Declaring Toolbar
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nowe_hipoteka);

        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_wpisz_hipoteke);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?

        TextView akapit = (TextView) findViewById(R.id.hipoteka_akapit3);
        akapit.setMovementMethod(LinkMovementMethod.getInstance());


    }
}
