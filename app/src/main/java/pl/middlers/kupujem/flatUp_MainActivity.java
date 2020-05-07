package pl.middlers.kupujem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.leocardz.link.preview.library.LinkPreviewCallback;
import com.leocardz.link.preview.library.SourceContent;
import com.leocardz.link.preview.library.TextCrawler;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;


public class flatUp_MainActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {


    public boolean savedIsSubscribed;

    private FirebaseAnalytics mFirebaseAnalytics;

    //Declaring Toolbar
    private Toolbar toolbar;

    TextCrawler textCrawler;

    TextView instrukcja;
    Button button;

    RecyclerView recyclerView;
    flatUp_RecyclerAdapter adapter;
    ArrayList<flatUp_glownaLista> cardInfoArrayList;
    Context context;


    String tytul = "Przykładowy tytuł";
    String link = "";
    String opis = "";
    String adres = "", mratingSCORE;
    ImageView mZdjecie;
    Bitmap zdjecie, bitmap;
    String url = "";
    String url2, copiedUrl, poprawionyUrl;
    String finalUrl;

    Boolean http;
    String shareVia;

    Integer liczbaKart;
    //progress bar
    ProgressDialog mProgressDialog;


    //Floating Action Button with menu
   // com.github.clans.fab.FloatingActionMenu floatingActionMenu;
   // com.github.clans.fab.FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;


    //zaczerpnij variable czy jest subskrypcja z innejm wspolnej klasy
    public boolean mIsSubscribed;


    //do zakupu subskrypcji
    BillingProcessor bp;


    //limit darmowych klików
    int limit = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flat_up_activity_main);
        context = this;

        //Pokoloruj navigation bar na biało
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.fafafa));
        }

        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("flatUp_tile_open", params);


        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pobrane oferty sprzedaży M");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Czy pokazywać cofającą strzałkę?

        //stworz progress dialog
        mProgressDialog = new ProgressDialog(this);

        //odzyskaj zapisaną arrayliste w shared preferences
        read_ArrayList();


        instrukcja = (TextView) findViewById(R.id.instrukcja_TextView);
        button = (Button) findViewById(R.id.button2);


        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new flatUp_RecyclerAdapter(this, cardInfoArrayList);
        recyclerView.setAdapter(adapter);


        liczbaKart = adapter.getItemCount();


        //to jest imageview tylk odla picu umieszczony po to zeby byl bo UrlImageViewHelper.setUrlDrawable go potrzebuje
        mZdjecie = (ImageView) findViewById(R.id.zdjecieX);

        //metoda wyciągająca informacje z linka?
        textCrawler = new TextCrawler();

        //Floating Action Button with menu
       // floatingActionMenu = (com.github.clans.fab.FloatingActionMenu) findViewById(R.id.fab);
       // floatingActionButton1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fa);
        //floatingActionButton2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab22);


        //nie pokazuj w ogóle nigdy buttona
        //floatingActionMenu.hideMenu(true);


/**
 * załaduj procesor zakupów w aplikacji
 */
        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApS4vZ9XdOBwy3zkK66qVzx+9ZFIC3UB8+ryHwjY3ZHBuwOQXpMP7hx3tGO0zQKH1moumQHU1hrLJlZivBl233h7+n9G5ruDx8hZ9jGgiiVRbifJ8S2uVXRTwI+W9c4jGguMtL9tjZeSZ7f2+WEVR5WlRZeTCgWy/xaHav4xyfwDE7iEveLyn+lKr6gA/n4RH+RC4uYwgTff6p6T+lRezQ+uqXzh5OhGecnSvavmPWoo8PYyMgEBLNQl//vX9Gr2ghc3UCGCTeLMFN8vacI412ZD8KmnDmDAJQZ0z8Wfgi/7BgPxNZWeIwq5466Lp6FBDl5ahJzxjNhfEwC4QuJ2YvwIDAQAB", this);

        //załaduj zakupioną subskrypcję z pamięci
        LoadBoolean();
        // mIsSubscribed = savedIsSubscribed;
        mIsSubscribed = savedIsSubscribed;


        //ukryj FAB kiedy scroll down i kiedy jest na samym dole
        //    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        //        @Override
        //        public void onScrolled(RecyclerView recyclerView, int dx,int dy){
        //            super.onScrolled(recyclerView, dx, dy);

        //znajdź ostatni całkowicie widoczny item
        //           int LAST_POSITION = layoutManager.findLastCompletelyVisibleItemPosition();


        //           if ((dy >0)) {
        // ... to schowaj FAB
        //   floatingActionMenu.hideMenuButton(true);
        //                if
        //                       (liczbaKart < 2) {
        //                   floatingActionMenu.showMenuButton(true);
        //               }

        //               if
        //jeśli ostani całkowicie widoczny item jest widoczny...
        //                  (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == LAST_POSITION) {

        // ... to schowaj FAB
        //                   floatingActionMenu.hideMenuButton(true);
        //                  }

        //           }

        //jesli scroll idzie do góry...
        //           else if (dy <0) {

        //...to pokaż FAB
        //               floatingActionMenu.showMenuButton(true);
        //            }
        //           }
        //       });


        //złap intent, kiedy ktoś sheruje link do tej aplikacji
        if (getIntent().getExtras() != null) {
            shareVia = (String) getIntent().getExtras().get(Intent.EXTRA_TEXT);
            assert shareVia != null;

            //jeśli share intent zawiera http, to zacznij analizować link, jeśli nie, to znaczy, żeby tylko otworzyć activity
            if (shareVia.contains("http")) {

                //URL to tekst z share intent
                url = shareVia;

                //usuń wszystkie znaki z url które występują przed htt
                String s1 = url.substring(url.indexOf("htt"));
                url = s1.trim();


                //pokaż progress bar na czas analizowania linku
                mProgressDialog.setMessage("Analizuję link...");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                Toast.makeText(getApplication(), "Przekazany link: " + url + mIsSubscribed, Toast.LENGTH_LONG).show();

                //Firebase Event
                mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
                Bundle params2 = new Bundle();
                mFirebaseAnalytics.logEvent("flatUp_przekazano_ShareIntent", params2);


                //główna metoda - wydobąć wszystkie potrzebne dane z linku
                textCrawler.makePreview(callback, url);

            }


        }
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_flatup, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_samouczek:


                Intent intents = new Intent(this, Samouczek.class);
                startActivity(intents);

                //Firebase Event
                mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
                Bundle params3 = new Bundle();
                mFirebaseAnalytics.logEvent("flatUp_menu_samouczek_open", params3);

                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Kidy klikniesz w przycisk żeby obejrzeć samouczek
     */
    public void samouczek(View view) {

        Intent intents = new Intent(this, Samouczek.class);
        startActivity(intents);

        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params3 = new Bundle();
        mFirebaseAnalytics.logEvent("flatUp_samouczek_open", params3);

    }


    /**
     * Kidy klikniesz w przycisk floatingActionButton2
     */
    public void fab_22_click(View v) {
        Toast.makeText(getApplication(), "Nad tą opcją jeszcze pracujemy :)", Toast.LENGTH_LONG).show();

    }


    /**
     * Kidy klikniesz w przycisk floatingActionButton1
     */
    public void fab_12_click(View v) {

        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("flatUp_FAB_analizuj_link_open", params);

        //pokaż progress bar na czas analizowania linku
        mProgressDialog.setMessage("Analizuję link...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();


        // Wklej url ze schowka
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // If it does contain data, decide if you can handle the data.
        if (!(clipboard.hasPrimaryClip())) {
        } else if (!(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {
            // since the clipboard has data but it is not plain text
        } else {
            //since the clipboard contains plain text.
            ClipData.Item copiedItem = clipboard.getPrimaryClip().getItemAt(0);
            // Gets the clipboard as text.
            copiedUrl = copiedItem.getText().toString();


            //jesli skopiowany url zaczyna się od http:// to nic nie rób
            if (copiedUrl.startsWith("http")) {
                finalUrl = copiedUrl;

            } else {
                //jesli nie zaczyna się od http://, to dodaj http:// na początku
                finalUrl = "http://" + copiedUrl;
            }
        }


        //główna metoda - wydobąć wszystkie potrzebne dane z linku
        textCrawler.makePreview(callback, finalUrl);

    }








    /* Callback to update your view. Totally customizable. */
    /* onPre() will be called before the crawling. onPos() after. */
    /**
     * You can customize this to update your view
     */
    private LinkPreviewCallback callback = new LinkPreviewCallback() {


        @Override
        public void onPre() {
        }

        @Override
        public void onPos(final SourceContent sourceContent, final boolean isNull) {


            //Tutaj sprawdź, czy jest już wykupiona subskrypcja czy nie


            //  if (liczbaKart >= 4) {

            if (mIsSubscribed == true) {

                //pokaż okno dialogowe, ze wyczerpany limit
                //    show_dialog_wyczerpales_limit();

                //zamknij brogress bar kiedy przekroczyłeś limit
                //    mProgressDialog.dismiss();
                // dodajNowaKarte();

                //Jesli link jest prawidłowy, wydobądź wszystkie potrzebne dane
                /*
                  Wydobądź wszystkie potrzebne dane z linku
                 */
                tytul = sourceContent.getTitle();
                //tylko jesli tytuł jest pusty, zastap go tekstem: tytuł niedostepny
                if (tytul.equals("")) {
                    tytul = "Tytuł niedostępny";
                }
                link = sourceContent.getCannonicalUrl();
                //opis = sourceContent.getDescription();
                adres = "";
                mratingSCORE = "0.0";
                //nie pokazuj opisu
                // opis = null;
                url = sourceContent.getFinalUrl();
                UrlImageViewHelper.setUrlDrawable(mZdjecie, sourceContent.getImages().get(0), new UrlImageViewCallback() {
                    @Override
                    public void onLoaded(ImageView imageView, Bitmap bitmap, String string2, boolean blh) {
                        //jeśli wydobyto zdjecie
                        if (bitmap != null) {
                            //to zastosuj to zdjęcie
                            zdjecie = bitmap;
                        } else {
                            //w przeciwnym wypadku wstaw pusta bitpama
                            zdjecie = BitmapFactory.decodeResource(getResources(), R.drawable.pusta_bitmapa);
                        }
                        //zamien zdjecie bitmap na string
                        final int COMPRESSION_QUALITY = 100;
                        String encodedImage;
                        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
                        zdjecie.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                                byteArrayBitmapStream);
                        byte[] b = byteArrayBitmapStream.toByteArray();
                        //zwroc zdjecie bitmap zamienione na stringa
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        //Zaktualizuj kartę o wydobyte informacje z linku
                        flatUp_glownaLista cardInfo = new flatUp_glownaLista(tytul, link, opis, url, adres, mratingSCORE, encodedImage);
                        //dodaj nowy obiekt do cardInfoArrayList
                        cardInfoArrayList.add(cardInfo);
                        //Firebase Event
                        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
                        Bundle params4 = new Bundle();
                        mFirebaseAnalytics.logEvent("flatUp_karta_dodano", params4);
                        //powiedz adapterowi że zmienily sie informacje
                        adapter.notifyDataSetChanged();
                        //zapisz Array List
                        save_ArrayList();
                        //to znaczy, żeby pomału przewinąć w dół i pokazac nowopowstały item
                        recyclerView.smoothScrollToPosition(cardInfoArrayList.size() - 1);
                        //zamknij brogress bar po załadowaniu posta
                        mProgressDialog.dismiss();
                        instrukcja.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.INVISIBLE);
                        //zamknij FAB bo załadowaniu posta
                     //   floatingActionMenu.close(true);

                    }
                });


            } else if (liczbaKart >= limit) {

                //zamknij brogress bar kiedy przekroczyłeś limit
                mProgressDialog.dismiss();

                //pokaż okno dialogowe, ze wyczerpany limit
                show_dialog_wyczerpales_limit();



            } else if (isNull || sourceContent.getFinalUrl().equals("")) {

                linkUszkodzony();


            } else {

                // dodajNowaKarte();

                //Jesli link jest prawidłowy, wydobądź wszystkie potrzebne dane
                /*
                  Wydobądź wszystkie potrzebne dane z linku
                 */
                tytul = sourceContent.getTitle();

                //tylko jesli tytuł jest pusty, zastap go tekstem: tytuł niedostepny
                if (tytul.equals("")) {
                    tytul = "Tytuł niedostępny";
                }
                link = sourceContent.getCannonicalUrl();
                //opis = sourceContent.getDescription();
                adres = "";
                mratingSCORE = "0.0";

                //nie pokazuj opisu
                // opis = null;
                url = sourceContent.getFinalUrl();


                UrlImageViewHelper.setUrlDrawable(mZdjecie, sourceContent.getImages().get(0), new UrlImageViewCallback() {


                    @Override
                    public void onLoaded(ImageView imageView, Bitmap bitmap, String string2, boolean blh) {

                        //jeśli wydobyto zdjecie
                        if (bitmap != null) {
                            //to zastosuj to zdjęcie
                            zdjecie = bitmap;
                        } else {
                            //w przeciwnym wypadku wstaw pusta bitpama
                            zdjecie = BitmapFactory.decodeResource(getResources(), R.drawable.pusta_bitmapa);
                        }

                        //zamien zdjecie bitmap na string
                        final int COMPRESSION_QUALITY = 100;
                        String encodedImage;
                        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
                        zdjecie.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                                byteArrayBitmapStream);
                        byte[] b = byteArrayBitmapStream.toByteArray();
                        //zwroc zdjecie bitmap zamienione na stringa
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);


                        //Zaktualizuj kartę o wydobyte informacje z linku
                        flatUp_glownaLista cardInfo = new flatUp_glownaLista(tytul, link, opis, url, adres, mratingSCORE, encodedImage);

                        //dodaj nowy obiekt do cardInfoArrayList
                        cardInfoArrayList.add(cardInfo);


                        //Firebase Event
                        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
                        Bundle params4 = new Bundle();
                        mFirebaseAnalytics.logEvent("flatUp_karta_dodano", params4);


                        //powiedz adapterowi że zmienily sie informacje
                        adapter.notifyDataSetChanged();


                        //zapisz Array List
                        save_ArrayList();

                        //to znaczy, żeby pomału przewinąć w dół i pokazac nowopowstały item
                        recyclerView.smoothScrollToPosition(cardInfoArrayList.size() - 1);


                        //zamknij brogress bar po załadowaniu posta
                        mProgressDialog.dismiss();

                        instrukcja.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.INVISIBLE);

                        //zamknij FAB bo załadowaniu posta
                       // floatingActionMenu.close(true);

                    }
                });


            }
        }






    };

    private void dodajNowaKarte() {
    }

    private void linkUszkodzony() {
        Toast.makeText(getApplication(), "link uszkodzony", Toast.LENGTH_LONG).show();

        //zamknij brogress bar kiedy link uszkodzony
        mProgressDialog.dismiss();

        //zamknij FAB kiedy link uszkodzony
      //  floatingActionMenu.close(true);


        // otworz fix_url_dialog
        LayoutInflater li = LayoutInflater.from(context);
        final View fix_url_dialog = li.inflate(R.layout.fix_url_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(flatUp_MainActivity.this);

        // set fix_url_dialog.xml to alertdialog builder
        alertDialogBuilder.setView(fix_url_dialog);

        final EditText DialogEtitText = (EditText) fix_url_dialog.findViewById(R.id.editTextDialog);
        // DialogEtitText.setText(copiedUrl);
        DialogEtitText.setText(url);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setTitle("Link nieprawidłowy")
                .setMessage("Wpisz prawidłowy adres strony")
                .setPositiveButton("Zrobione",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                poprawionyUrl = DialogEtitText.getText().toString();


                                //jesli skopiowany url zaczyna się od http:// to nic nierób
                                if (poprawionyUrl.startsWith("http")){
                                    finalUrl = finalUrl;

                                }else{
                                    //jesli nie zaczyna się od http://, to dodaj http:// na początku
                                    poprawionyUrl = "http://" + poprawionyUrl;
                                }

                                textCrawler.makePreview(callback, poprawionyUrl);

                                //pokaż progress bar na czas analizowania linku
                                mProgressDialog.setMessage("Analizuję link...");
                                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                mProgressDialog.setCancelable(false);
                                mProgressDialog.show();
                            }
                        })
                .setNegativeButton("Anuluj",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    private void show_dialog_wyczerpales_limit() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("Wyczerpałeś bezpłatny limit: " + limit);
        builder.setCancelable(true);
        builder.setMessage(R.string.wyczerpales_limit_kalkulatory_obliczZdolnosc);
        builder.setPositiveButton("dalej", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                //metoda zamawiania subskrypcji
                bp.subscribe(flatUp_MainActivity.this, "kupujem_premium1");

            }

        }

        );

        //   builder.setNegativeButton("Zamknij", new DialogInterface.OnClickListener() {
        //        public void onClick(DialogInterface dialog, int id) {
        //            dialog.cancel();
        //        }

        //    });

        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();


        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("SUB_dialog_show_flatup", params);
    }

    public  void save_ArrayList (){


        //dodaj Array Liste do shared preferences
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(flatUp_MainActivity.this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cardInfoArrayList); //Convert the array to json
        editor.putString("", json); //Put the variable in memory
        editor.apply();


    }


    public void read_ArrayList(){
        //odzyskaj zapisaną arrayliste w shared preferences
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(flatUp_MainActivity.this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("", null); //Retrieve previously saved data
        if (json != null) {
            Type type = new TypeToken<ArrayList<flatUp_glownaLista>>() {}.getType();
            cardInfoArrayList = gson.fromJson(json, type); //Restore previous data
        }
        else cardInfoArrayList = new ArrayList<>();
    }





    //zapisz cardInfoArrayList kiedy wyskakuje okno dialogowe
    @Override
    public void onPause() {
        super.onPause();
        save_ArrayList();
    }

    //zapisz cardInfoArrayList kiedy przestaje byc widoczna na ekranie
    @Override
    public void onStop() {
        super.onStop();
        save_ArrayList();
    }

    @Override
    public void onResume() {
        super.onResume();
        read_ArrayList();
        adapter = new flatUp_RecyclerAdapter(this, cardInfoArrayList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        Toast.makeText(this, "Dziękuję za zakup:  " + productId, Toast.LENGTH_LONG).show();


        mIsSubscribed = true;
        //Zapisz subskrybcję w Shared Preferences
        SaveBoolean("isSubscribed", mIsSubscribed);


        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("Subskrypcja_zakup_flatup", params);
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(this, "Błąd nr: " + errorCode, Toast.LENGTH_LONG).show();
        if (errorCode != 7) {
            Toast.makeText(this, "Wystapił błąd nr " + errorCode +". Spróbuj ponownie", Toast.LENGTH_LONG).show();

            //Firebase Event
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle params = new Bundle();
            mFirebaseAnalytics.logEvent("SUB_zakup_flatup_error_" + errorCode, params);

        }else{
            mIsSubscribed = true;
            Toast.makeText(this, "Dziękuję za subskrypcję :)", Toast.LENGTH_LONG).show();
            //Zapisz subskrybcję w Shared Preferences
            SaveBoolean("isSubscribed", mIsSubscribed);


            //Firebase Event
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle params = new Bundle();
            mFirebaseAnalytics.logEvent("SUB_zakup_juz_byl_flatup", params);
        }
    }

    @Override
    public void onBillingInitialized() {

        }


    //metoda odczytu SharedPreferences
    public void LoadBoolean(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        savedIsSubscribed = sharedPreferences.getBoolean("isSubscribed", false);
    }

    //metoda zapisu BOOLEAN do SharedPreferences
    public void SaveBoolean(String key, boolean value){

        SharedPreferences  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //Wykiluj bilingprocessor on Distroy
    @Override
    public void onDestroy() { if (bp != null) {
        bp.release();
    }
        super.onDestroy();
    }



}
