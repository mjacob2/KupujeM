package pl.middlers.kupujem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.builders.Actions;

public class Nowe_prawdz_dewelopera extends AppCompatActivity {


    //Declaring Toolbar
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nowe_sprawdz_dewelopera);


        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();



        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Title_Sprawdz_dewelopera);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);          // Czy pokazywać cofającą strzałkę?


        TextView t2 = (TextView) findViewById(R.id.sprawdz_dewelopera_akapit2);
        t2.setMovementMethod(LinkMovementMethod.getInstance());


        TextView t3 = (TextView) findViewById(R.id.sprawdz_dewelopera_akapit3);
        t3.setMovementMethod(LinkMovementMethod.getInstance());


    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        return Actions.newView("Nowe_prawdz_dewelopera", "http://middlers.pl/jaksprawdzicdewelopera");
    }
}



