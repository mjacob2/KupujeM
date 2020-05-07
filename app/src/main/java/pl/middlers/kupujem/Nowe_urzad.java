package pl.middlers.kupujem;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class Nowe_urzad extends AppCompatActivity {


    //Declaring Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nowe_urzad);

        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_formalnosci_urzad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?

        TextView t2 = (TextView) findViewById(R.id.formalnosci_urzad_akapit1);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t3 = (TextView) findViewById(R.id.formalnosci_urzad_akapit4);
        t3.setMovementMethod(LinkMovementMethod.getInstance());
    }


    // kiedy klikniesz żeby wysłać email
    public void gdzie_jeszcze(View view) {

        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:kupujem.app@gmail.com"));
        i.putExtra(Intent.EXTRA_SUBJECT, "zmiana adresu - gdzie zgłosić");
        i.putExtra(Intent.EXTRA_TEXT, "Zmianę adresu warto zgłosić w:\n");
        if (i.resolveActivity(getPackageManager()) !=null){
            startActivity(i);
        }
    }
}


