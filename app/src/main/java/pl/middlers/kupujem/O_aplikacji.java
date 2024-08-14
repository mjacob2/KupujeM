package pl.middlers.kupujem;

import android.os.Bundle;
import android.webkit.WebSettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
