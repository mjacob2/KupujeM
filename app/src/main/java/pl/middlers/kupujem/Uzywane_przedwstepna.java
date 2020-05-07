package pl.middlers.kupujem;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import pl.middlers.kupujem.R;

public class Uzywane_przedwstepna extends AppCompatActivity {

    //Declaring Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uzywane_przedwstepna);

        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_przedwstepna);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?

        TextView t2 = (TextView) findViewById(R.id.wtorny_przedwstepna_akapit2);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // kiedy klikniesz żeby wysłać email
    public void pomoc_click_radca_prawny(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:kupujem.app@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Proszę o kontakt do radcy prawnego");
        intent.putExtra(Intent.EXTRA_TEXT, "imię i nazwisko: \n\nnr. telefonu: \n\nopis transakcji: \n\nzniżka na hasło: KupujeM");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
