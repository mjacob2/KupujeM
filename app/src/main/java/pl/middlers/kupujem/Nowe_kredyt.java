package pl.middlers.kupujem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Nowe_kredyt extends AppCompatActivity {

    //Declaring Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nowe_kredyt);

        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_wniosek_kredytowy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?
    }

    // kiedy klikniesz żeby wysłać email
    public void pomoc_click_doradca(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://middlers.pl/kontakt/")));
    }

    // kiedy klikniesz żeby wysłać email
    public void klik (View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:kupujem.app@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Proszę o kontakt do doradcy finansowego");
        intent.putExtra(Intent.EXTRA_TEXT, "imię i nazwisko: \n\nnr. telefonu: \n\nmiasto:\n");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    }
