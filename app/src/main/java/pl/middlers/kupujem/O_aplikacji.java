package pl.middlers.kupujem;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ProgressBar;

import com.google.firebase.analytics.FirebaseAnalytics;

public class O_aplikacji extends AppCompatActivity {

    android.webkit.WebView webview;
    private Toolbar toolbar;


    //do Firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);



        // Intent intent = getIntent();
        String value = "https://middlers.pl/o-mnie.html";
        String value2 = getResources().getString(R.string.tile_kredyt_title);




        webview = findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        //włącz obsługę JAvaScriptu na stronie
        webSettings.setJavaScriptEnabled(true);




        // mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


          toolbar = findViewById(R.id.toolbar2);
          setSupportActionBar(toolbar);
          getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
          getSupportActionBar().setTitle("O autorze aplikacji");
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        webview.loadUrl(value);
    }
}
