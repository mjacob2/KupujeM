package pl.middlers.kupujem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.graphics.Bitmap;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

import butterknife.BindView;

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



        // mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        getSupportActionBar().setTitle(value2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("Formularz_open", params);


        webview.loadUrl(value);


    }
}
