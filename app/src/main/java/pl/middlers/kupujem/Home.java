package pl.middlers.kupujem;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.builders.Actions;


public class Home extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    private Toolbar toolbar;
    public String url = "";
    final Context context = this;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        //Pokoloruj navigation bar na biało
       // if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //      getWindow().setNavigationBarColor(getResources().getColor(R.color.fafafa));
      //  }

        //złap intent, kiedy ktoś sheruje link do tej aplikacji
        //       if (getIntent().getExtras() != null) {
        //          String shareVia = (String) getIntent().getExtras().get(Intent.EXTRA_TEXT);
        //           if (shareVia != null) {
        //               url = shareVia;

        //               Toast.makeText(getApplication(), "Przekazany link: " + url, Toast.LENGTH_LONG).show();

        //Firebase Event
        //               mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //               Bundle params = new Bundle();
        //               mFirebaseAnalytics.logEvent("flatUp_przekazano_ShareIntent", params);

        //               Intent intent = new Intent(Home.this, flatUp_MainActivity.class);
        //               intent.putExtra(Intent.EXTRA_TEXT, url);
        //               startActivity(intent);


        //           }
        //       }


        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            // Firebase Event
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle params = new Bundle();
            mFirebaseAnalytics.logEvent("share_click", params);

            // Start share intent
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Pobierz aplikację KupujeM! Bardzo pomaga bezpiecznie kupić własne M.\n\n" + "https://play.google.com/store/apps/details?id=pl.middlers.kupujem");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            return true;

        } else if (id == R.id.action_rate) {
            // Firebase Event
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle params2 = new Bundle();
            mFirebaseAnalytics.logEvent("rate_click", params2);

            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
            return true;

        } else if (id == R.id.action_o_aplikacji) {
            // Open activity
            Intent intent = new Intent(this, O_aplikacji.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    /** Mówi co robić, kiedy kliknie się DODATKOWA POMOC*/
    public void pomoc (View view){
        Intent intent = new Intent(this, Formularz.class);
        startActivity(intent);


    }

    /** Mówi co robić, kiedy kliknie się kafelek OCEŃ NAS NA 5*/
    public void  rate_click (View view){

        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params2 = new Bundle();
        mFirebaseAnalytics.logEvent("rate_click_kafelek", params2);


        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }

    }


    /** Mówi co robić, kiedy kliknie się z NEWS */
    public void news (View view){
        Intent intents = new Intent(this, News.class);
        startActivity(intents);

    }

    /** Mówi co robić, kiedy kliknie się z FlatUp */
    public void click_flatUp (View view){
        Intent intents = new Intent(this, flatUp_MainActivity.class);
        intents.putExtra(Intent.EXTRA_TEXT, "openFlatUp");
        startActivity(intents);

    }


    /**Mówi co robić, kiedy kliknie się w PORÓWNYWARKA OFERT */

    public void click_porownywarka(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://ohipo.pl/?utm_source=Aplikacja%20Mobilna&utm_medium=Android&utm_campaign=KupujeM")));

    }

    /** Mówi co robić, kiedy kliknie się z KALKULATORY */
    public void click_kalkulatory (View view){

        Intent intents = new Intent(this, Tabs_Kalkulatory.class);
        startActivity(intents);
    }
    /** Mówi co robić, kiedy kliknie się z KROKI */
    public void click_kroki (View view){
        Intent intents = new Intent(this, Tabs_Kroki.class);
        startActivity(intents);

    }

    /** Called when the user clicks the OD DEWELOPERA button */
    public void checklisty (View view){

        Intent intents = new Intent(this, Tabs_Checklisty.class);
        startActivity(intents);

    }
    /** Called when the user clicks the OD SLOWNICZEK button */
    public void slowniczek (View view) {
        Intent intent = new Intent(this, Slowniczek.class);
        startActivity(intent);


    }

    public void sugestia (View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:kupujem.app@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Moja sugestia dla rozwoju KupujeM");
        intent.putExtra(Intent.EXTRA_TEXT, "Proponuję, aby: \n\n");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        return Actions.newView("Home", "http://middlers.pl/kupujem");
    }



    //  public void polecenie (View view) {

  //      Intent sendIntent = new Intent();
  //      sendIntent.setAction(Intent.ACTION_SEND);
  //      sendIntent.putExtra(Intent.EXTRA_TEXT, "Zobacz, jaka fajna apka o kupowaniu mieszkania:\n\n" + "https://play.google.com/store/apps/details?id=pl.middlers.kupujem");
  //      sendIntent.setType("text/plain");
  //      startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
  //      }

 //   public void rate (View view) {
//        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
 //       // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
 //       goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
  //              Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
 //               Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
 //       try {
 //           startActivity(goToMarket);
  //      } catch (ActivityNotFoundException e) {
  //          startActivity(new Intent(Intent.ACTION_VIEW,
  //                  Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
  //      }
  //  }

}
