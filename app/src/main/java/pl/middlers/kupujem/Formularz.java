package pl.middlers.kupujem;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Formularz extends AppCompatActivity {

    android.webkit.WebView webview;
    private Toolbar toolbar;

    private ProgressBar mProgressBar;
    //do Firebase
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);



       // Intent intent = getIntent();
        String value = "https://forms.zohopublic.eu/jakubickim/form/abc/formperma/4CTCfBkz-6-h29FkJPHQmyeFShrErtcsuppDiUTmZOI";
        String value2 = getResources().getString(R.string.tile_kredyt_title);




        webview = findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        //włącz obsługę JAvaScriptu na stronie
        webSettings.setJavaScriptEnabled(true);
        toolbar = findViewById(R.id.toolbar2);
        toolbar.setVisibility(View.GONE);



        // mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


      //  toolbar = findViewById(R.id.toolbar2);
      //  setSupportActionBar(toolbar);
      //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
      //  getSupportActionBar().setTitle(value2);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("Formularz_open", params);


        webview.loadUrl(value);


    }
}
