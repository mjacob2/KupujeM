package pl.middlers.kupujem;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Uzywane_sprawdz_sprzedajacego extends AppCompatActivity {

    //Declaring Toolbar
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uzywane_sprawdz_sprzedajacego);


        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_Sprawdz_sprzedajacego);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?


        TextView t2 = (TextView) findViewById(R.id.wtorny_sprawdz_sprzedajacego_akapit2);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t3 = (TextView) findViewById(R.id.wtorny_sprawdz_sprzedajacego_akapit7);
        t3.setMovementMethod(LinkMovementMethod.getInstance());


    }
}
