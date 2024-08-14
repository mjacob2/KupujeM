package pl.middlers.kupujem;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.analytics.FirebaseAnalytics;

public class WebView extends AppCompatActivity {

    android.webkit.WebView webview;
    private Toolbar toolbar;

    private ProgressBar mProgressBar;
    //do Firebase
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.
        String value2 = intent.getStringExtra("title");

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
        mFirebaseAnalytics.logEvent("Single_NEWS_open", params);


        webview.loadUrl(value);


    }
}