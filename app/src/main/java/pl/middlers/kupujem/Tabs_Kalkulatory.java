package pl.middlers.kupujem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import static pl.middlers.kupujem.R.id.oprocentowanie;

//import static pl.middlers.kupujem.R.id.wysokosc_podatku;
//import static pl.middlers.kupujem.R.id.wysokosc_taksy_brutto;

public class Tabs_Kalkulatory extends AppCompatActivity implements BillingProcessor.IBillingHandler{
    //do komendy oblicz zdolnosc
    private EditText dochod, raty, karty, debety, wnioskujacych, wkladWlasnyProcent;
    private TextView maxKredyt, wkladWlasnyKwota, cenaM, rataTania, rataDroga, roznicaDoSplatyMiesiecznie, roznicaDoSplatyRocznie, roznicaDoSplaty30lat;

    private Button mPrzycisk_oblicz;

    //do komendy oblicz raty
    private EditText mKwotaKredytu, mLiczbaLat, mOprocentowanie;
    private TextView mRRWysokoscRaty, mRRLacznieDoSplaty, mRRSumaOdsetek, mRM_pierwsza, mRM_ostatnia, mRM_sumaOdsetek, mRM_lacznieDoSplaty;

    //do komendy oblicz notariusz
    private EditText mCenaZakupu;
    private TextView mMaxTaksa, mWysokoscPodatku, mWysokoscTaksyBrutto, mMaxTaksaPrzedwstepna, mWysokoscPodatkuPrzedwstepna, mWysokoscTaksyBruttoPrzedwstepna, mWysokoscPCC;

    //do tabsów
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    int mClickCount = 0;

    //limit darmowych klików
    int limit = 100;

    //zapisana wartość w SharedPreferences
    int savedValue = 0;

    public boolean savedIsSubscribed;

    //do Firebase
    private FirebaseAnalytics mFirebaseAnalytics;

    //do zakupu subskrypcji
    BillingProcessor bp;

    //do sprawdzania, czy jest zakupiona subskrypcja
   // public boolean mIsSubscribed = false;


    //zaczerpnij variable czy jest subskrypcja z innej wspolnej klasy
    public  boolean mIsSubscribed = Subscription.newIsSubscribed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_kalkulatory);
        //getSupportActionBar().hide(); //ukrj AppBar

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kalkulatory");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Czy pokazywać cofającą strzałkę?

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        //nie pokazuj klawiatury na wstępie
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Pokoloruj navigation bar na biało
       // if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
       //     getWindow().setNavigationBarColor(getResources().getColor(R.color.fafafa));
       // }


        /**
         * załaduj procesor zakupów w aplikacji
         */
        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApS4vZ9XdOBwy3zkK66qVzx+9ZFIC3UB8+ryHwjY3ZHBuwOQXpMP7hx3tGO0zQKH1moumQHU1hrLJlZivBl233h7+n9G5ruDx8hZ9jGgiiVRbifJ8S2uVXRTwI+W9c4jGguMtL9tjZeSZ7f2+WEVR5WlRZeTCgWy/xaHav4xyfwDE7iEveLyn+lKr6gA/n4RH+RC4uYwgTff6p6T+lRezQ+uqXzh5OhGecnSvavmPWoo8PYyMgEBLNQl//vX9Gr2ghc3UCGCTeLMFN8vacI412ZD8KmnDmDAJQZ0z8Wfgi/7BgPxNZWeIwq5466Lp6FBDl5ahJzxjNhfEwC4QuJ2YvwIDAQAB", this);

                    //testowy procesor
                // bp = new BillingProcessor(this, null, this);

        //załaduj zakupioną subskrypcję z pamięci
        LoadBoolean();
        // mIsSubscribed = savedIsSubscribed;
        mIsSubscribed = savedIsSubscribed;


       // oblicz_zdolnosc1();
    }




    public void oblicz_zdolnosc_button_click (View view) {

        //Załaduj SharedPreferences
        LoadInt();
        mClickCount = savedValue;

        //Dodaj kliknięcie
        mClickCount = mClickCount + 1;

        //Zapisz kliknięcie w Shared Preferences
        SaveInt("key", mClickCount);





        //jeśli jest zapisana wykupiona subskrypcja to oblicz zdolność normalnie

        if (mIsSubscribed == true) {

            oblicz_zdolnosc1();


            // Jesli nie ma zapisanej subskrypcji, sprawdź ile razy było już kliknięte oblicz zdolność
        } else if (mClickCount >= limit) {
            show_dialog_wyczerpales_limit();


        } else {

            oblicz_zdolnosc1();


        }
    }



    private void show_dialog_wyczerpales_limit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wyczerpałeś bezpłatny limit: " + limit);
        builder.setCancelable(true);
        builder.setMessage(R.string.wyczerpales_limit_kalkulatory_obliczZdolnosc);
        builder.setNegativeButton("anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("dalej", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                //metoda zamawiania subskrypcji
                bp.subscribe(Tabs_Kalkulatory.this, "kupujem_premium1");
            }



        });

        //   builder.setNegativeButton("Zamknij", new DialogInterface.OnClickListener() {
        //        public void onClick(DialogInterface dialog, int id) {
        //            dialog.cancel();
        //        }

        //    });

        AlertDialog alert = builder.create();
        alert.show();


        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("SUB_dialog_show_zdoln", params);
    }




    // kiedy klikniesz żeby wysłać email
    public void pomoc_click_doradca(View view) {

        Intent intents = new Intent(this, SendEmail.class);
        startActivity(intents);
    }


    public void oblicz_zdolnosc(View view) {




        //Jeśli kliknąłeś >= 1 to pokaż toast i nie licz dalej
      //  if (mClickCount >= 1) {






            //Dodaj kliknięcie
            mClickCount = 1;

            //Zapisz kliknięcie w Shared Preferences
            SaveInt("key", mClickCount);





        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("oblicz_zdolnosc_button", params);





        // Check if no view has focus and turn off keyboard
  //      View r = this.getCurrentFocus();
   //     if (r != null) {
  //          InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
  //          imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
   //     }

        dochod = (EditText) findViewById(R.id.dochod);
        wnioskujacych = (EditText) findViewById(R.id.wnioskujacych);
        raty = (EditText) findViewById(R.id.raty);
        karty = (EditText) findViewById(R.id.karty);
      //  debety = (EditText) findViewById(R.id.debety);


        maxKredyt = (TextView) findViewById(R.id.maxKredyt);

        wkladWlasnyProcent = (EditText) findViewById(R.id.wkladWlasnyProcent);
        wkladWlasnyKwota = (TextView) findViewById(R.id.wkladWlasnyKwota);
        cenaM = (TextView) findViewById(R.id.cenaM);

        rataTania = (TextView) findViewById(R.id.rataTania);
        rataDroga = (TextView) findViewById(R.id.rataDroga);

        roznicaDoSplatyMiesiecznie = (TextView) findViewById(R.id.roznicaDoSplatyMiesiecznie);
        roznicaDoSplatyRocznie = (TextView) findViewById(R.id.roznicaDoSplatyRocznie);
        roznicaDoSplaty30lat = (TextView) findViewById(R.id.roznicaDoSplatyLacznie);

        int vdochod = 0;
        int vwnioskujacych = 0;
        int vraty = 0;
        double vkarty = 0;
        int vdebety = 0;
        int vinne = 0;
        double vrata;

        double vWkladWlasnyProcent = 10;
        double vWkladWlasnyKwota = 0;
        double vCenaM = 0;


        if (dochod.getText().length() > 0) {
            vdochod = Integer.parseInt(dochod.getText().toString());
        }
        if (wnioskujacych.getText().length() > 0) {
            vwnioskujacych = Integer.parseInt(wnioskujacych.getText().toString());
        }
        if (raty.getText().length() > 0) {
            vraty = Integer.parseInt(raty.getText().toString());
        }
        if (karty.getText().length() > 0) {
            vkarty = Integer.parseInt(karty.getText().toString());
        }
   //     if (debety.getText().length() > 0) {
   //         vdebety = Integer.parseInt(debety.getText().toString());
    //    }
        if (wkladWlasnyProcent.getText().length() > 0) {
            vWkladWlasnyProcent = Integer.parseInt(wkladWlasnyProcent.getText().toString());
        } else {
            vWkladWlasnyProcent = 10;
            wkladWlasnyProcent.setText("10");
            Toast.makeText(this, "minimalny wkład własny to 10%",
                    Toast.LENGTH_LONG).show();
        }


        /**
         * policz DD (dochód dyspozycyjny) (ten związany z liczbą osób)
         */

        int vKosztyUtrzymania;

        if (vwnioskujacych >= 5) {
            vKosztyUtrzymania = 1084 + 707 + 864 + 775 + 775;
        } else if (vwnioskujacych >= 4) {
            vKosztyUtrzymania = 1084 + 707 + 864 + 775;
        } else if (vwnioskujacych >= 3) {
            vKosztyUtrzymania = 1084 + 707 + 864;
        } else if (vwnioskujacych >= 2) {
            vKosztyUtrzymania = 1084 + 707;
        } else {
            vKosztyUtrzymania = 1084;
        }

        double vKosztyKredytowe = vraty + vinne + (vkarty * 0.021) ;


        double vMnoznikRaty;
        double vDD = vdochod - vKosztyUtrzymania - vKosztyKredytowe;

        if (vDD >= 8916) {
            vMnoznikRaty = 0.589;
        } else if (vDD >= 8416) {
            vMnoznikRaty = 0.592;
        } else if (vDD >= 7916) {
            vMnoznikRaty = 0.597;
        } else if (vDD >= 7416) {
            vMnoznikRaty = 0.602;
        } else if (vDD >= 6916) {
            vMnoznikRaty = 0.607;
        } else if (vDD >= 6416) {
            vMnoznikRaty = 0.614;
        } else if (vDD >= 5916) {
            vMnoznikRaty = 0.621;
        } else if (vDD >= 5416) {
            vMnoznikRaty = 0.630;
        } else if (vDD >= 4916) {
            vMnoznikRaty = 0.641;
        } else if (vDD >= 4416) {
            vMnoznikRaty = 0.654;
        } else if (vDD >= 3916) {
            vMnoznikRaty = 0.670;
        } else {
            vMnoznikRaty = 0.7;
        }


        if (vdochod >= 1500) {
            vrata = (vDD * vMnoznikRaty);


        } else {
            vrata = 0;
            Toast.makeText(this, "minimalny dochód: 1500 zł",
                    Toast.LENGTH_LONG).show();
        }


        /**
         * policz wszystkie dane do wzoru na max kredyt
         */


        double voprocentowanie = 3.86;
        int viloscrat = 360;
        double vq = 1 + (voprocentowanie / 1200);
        double vqDoEntej = Math.pow(vq, viloscrat);
        double vnadkreska = vrata * (vqDoEntej - 1);
        double vpodkreska = vqDoEntej * (vq - 1);
        double vMaxDostepnyKredyt = vnadkreska / vpodkreska;


        /**
         * policz cene mieszkania
         */
        if (vWkladWlasnyProcent < 10) {
            vWkladWlasnyProcent = 10;
            //wkladWlasnyProcent.setText("10");
            Toast.makeText(this, "minimalny wkład własny to 10%",
                    Toast.LENGTH_LONG).show();
        } else {
            vCenaM = vMaxDostepnyKredyt / (1 - (vWkladWlasnyProcent / 100));


            /**
             * policz wklad wlasny
             */

            vWkladWlasnyKwota = vCenaM - vMaxDostepnyKredyt;

            /**
             * policz ratę tanią
             */
            double oprocentowanieTanie = 3.52;
            double q = 1 + (oprocentowanieTanie / 1200);
            double iloscRat = 360;
            double qDoEntej = Math.pow(q, iloscRat);
            double vRataTania = (vMaxDostepnyKredyt * qDoEntej) * ((q - 1) / (qDoEntej - 1));


            /**
             * policz ratę drogą
             */
            double oprocentowanieDrogie = 5.03;
            double q2 = 1 + (oprocentowanieDrogie / 1200);
            double iloscRat2 = 360;
            double qDoEntej2 = Math.pow(q2, iloscRat2);
            double vRataDroga = (vMaxDostepnyKredyt * qDoEntej2) * ((q2 - 1) / (qDoEntej2 - 1));


            /**
             * policz roznice do splaty
             */

            double vrataDrogaDoSplaty = vRataDroga * 360;
            double vrataTaniaDoSplaty = vRataTania * 360;
            double vroznicaDoSplatyMiesiecznie = vRataDroga - vRataTania;
            double vroznicaDoSplatyRocznie = vroznicaDoSplatyMiesiecznie * 12;
            double vroznicaDoSplatyLacznie = vrataDrogaDoSplaty - vrataTaniaDoSplaty;


            /**
             * nie pokazuj ujemnej zdolności
             */

            if (vMaxDostepnyKredyt < 0) {
                //vMaxDostepnyKredyt = 0;
                maxKredyt.setText("brak zdolności");
            } else {
                /**
                 * This method displays the given text on the screen.
                 */
                maxKredyt.setText(new java.text.DecimalFormat(" #,###" + " zł").format(vMaxDostepnyKredyt));
                cenaM.setText(new java.text.DecimalFormat(" #,###" + " zł").format(vCenaM));
                wkladWlasnyKwota.setText(new java.text.DecimalFormat(" #,###" + " zł").format(vWkladWlasnyKwota));
                rataTania.setText(new java.text.DecimalFormat("rata" + " ####" + " zł").format(vRataTania));

                rataDroga.setText(new java.text.DecimalFormat("rata" + " ####" + " zł").format(vRataDroga));

                roznicaDoSplatyMiesiecznie.setText(new java.text.DecimalFormat("  #,###" + " zł").format(vroznicaDoSplatyMiesiecznie));

                roznicaDoSplatyRocznie.setText(new java.text.DecimalFormat("  #,###" + " zł").format(vroznicaDoSplatyRocznie));

                roznicaDoSplaty30lat.setText(new java.text.DecimalFormat("  #,###" + " zł").format(vroznicaDoSplatyLacznie));


                // minKredyt.setText(new java.text.DecimalFormat(" #,###" + " zł").format(vMaxDostepnyKredyt * 0.535545));


                /**
                 * scroll to the bottom of screen
                 */
                //ScrollView sv = (ScrollView) findViewById(R.id.scroll);
                //sv.scrollTo(0, sv.getBottom());
            }


        }
    }


    private void oblicz_zdolnosc1() {


        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("oblicz_zdolnosc_button", params);


        // Check if no view has focus and turn off keyboard

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        dochod = (EditText) findViewById(R.id.dochod);
        wnioskujacych = (EditText) findViewById(R.id.wnioskujacych);
        raty = (EditText) findViewById(R.id.raty);
        karty = (EditText) findViewById(R.id.karty);
        //  debety = (EditText) findViewById(R.id.debety);


        maxKredyt = (TextView) findViewById(R.id.maxKredyt);

        wkladWlasnyProcent = (EditText) findViewById(R.id.wkladWlasnyProcent);
        wkladWlasnyKwota = (TextView) findViewById(R.id.wkladWlasnyKwota);
        cenaM = (TextView) findViewById(R.id.cenaM);

        rataTania = (TextView) findViewById(R.id.rataTania);
        rataDroga = (TextView) findViewById(R.id.rataDroga);

        roznicaDoSplatyMiesiecznie = (TextView) findViewById(R.id.roznicaDoSplatyMiesiecznie);
        roznicaDoSplatyRocznie = (TextView) findViewById(R.id.roznicaDoSplatyRocznie);
        roznicaDoSplaty30lat = (TextView) findViewById(R.id.roznicaDoSplatyLacznie);

        int vdochod = 0;
        int vwnioskujacych = 0;
        int vraty = 0;
        double vkarty = 0;
        int vdebety = 0;
        int vinne = 0;
        double vrata;

        double vWkladWlasnyProcent = 10;
        double vWkladWlasnyKwota = 0;
        double vCenaM = 0;


        if (dochod.getText().length() > 0) {
            vdochod = Integer.parseInt(dochod.getText().toString());
        }
        if (wnioskujacych.getText().length() > 0) {
            vwnioskujacych = Integer.parseInt(wnioskujacych.getText().toString());
        }
        if (raty.getText().length() > 0) {
            vraty = Integer.parseInt(raty.getText().toString());
        }
        if (karty.getText().length() > 0) {
            vkarty = Integer.parseInt(karty.getText().toString());
        }
        //     if (debety.getText().length() > 0) {
        //         vdebety = Integer.parseInt(debety.getText().toString());
        //    }
        if (wkladWlasnyProcent.getText().length() > 0) {
            vWkladWlasnyProcent = Integer.parseInt(wkladWlasnyProcent.getText().toString());
        } else {
            vWkladWlasnyProcent = 10;
            wkladWlasnyProcent.setText("10");
            Toast.makeText(this, "minimalny wkład własny to 10%",
                    Toast.LENGTH_LONG).show();
        }


        /**
         * policz DD (dochód dyspozycyjny) (ten związany z liczbą osób)
         */

        int vKosztyUtrzymania;

        if (vwnioskujacych >= 5) {
            vKosztyUtrzymania = 1084 + 707 + 864 + 775 + 775;
        } else if (vwnioskujacych >= 4) {
            vKosztyUtrzymania = 1084 + 707 + 864 + 775;
        } else if (vwnioskujacych >= 3) {
            vKosztyUtrzymania = 1084 + 707 + 864;
        } else if (vwnioskujacych >= 2) {
            vKosztyUtrzymania = 1084 + 707;
        } else {
            vKosztyUtrzymania = 1084;
        }

        double vKosztyKredytowe = vraty + vinne + (vkarty * 0.021);


        double vMnoznikRaty;
        double vDD = vdochod - vKosztyUtrzymania - vKosztyKredytowe;

        if (vDD >= 8916) {
            vMnoznikRaty = 0.589;
        } else if (vDD >= 8416) {
            vMnoznikRaty = 0.592;
        } else if (vDD >= 7916) {
            vMnoznikRaty = 0.597;
        } else if (vDD >= 7416) {
            vMnoznikRaty = 0.602;
        } else if (vDD >= 6916) {
            vMnoznikRaty = 0.607;
        } else if (vDD >= 6416) {
            vMnoznikRaty = 0.614;
        } else if (vDD >= 5916) {
            vMnoznikRaty = 0.621;
        } else if (vDD >= 5416) {
            vMnoznikRaty = 0.630;
        } else if (vDD >= 4916) {
            vMnoznikRaty = 0.641;
        } else if (vDD >= 4416) {
            vMnoznikRaty = 0.654;
        } else if (vDD >= 3916) {
            vMnoznikRaty = 0.670;
        } else {
            vMnoznikRaty = 0.7;
        }


        if (vdochod >= 1500) {
            vrata = (vDD * vMnoznikRaty);


        } else {
            vrata = 0;
            Toast.makeText(this, "minimalny dochód: 1500 zł",
                    Toast.LENGTH_LONG).show();
        }


        /**
         * policz wszystkie dane do wzoru na max kredyt
         */


        double voprocentowanie = 3.86;
        int viloscrat = 360;
        double vq = 1 + (voprocentowanie / 1200);
        double vqDoEntej = Math.pow(vq, viloscrat);
        double vnadkreska = vrata * (vqDoEntej - 1);
        double vpodkreska = vqDoEntej * (vq - 1);
        double vMaxDostepnyKredyt = vnadkreska / vpodkreska;


        /**
         * policz cene mieszkania
         */
        if (vWkladWlasnyProcent < 10) {
            vWkladWlasnyProcent = 10;
            //wkladWlasnyProcent.setText("10");
            Toast.makeText(this, "minimalny wkład własny to 10%",
                    Toast.LENGTH_LONG).show();
        } else {
            vCenaM = vMaxDostepnyKredyt / (1 - (vWkladWlasnyProcent / 100));


            /**
             * policz wklad wlasny
             */

            vWkladWlasnyKwota = vCenaM - vMaxDostepnyKredyt;

            /**
             * policz ratę tanią
             */
            double oprocentowanieTanie = 3.52;
            double q = 1 + (oprocentowanieTanie / 1200);
            double iloscRat = 360;
            double qDoEntej = Math.pow(q, iloscRat);
            double vRataTania = (vMaxDostepnyKredyt * qDoEntej) * ((q - 1) / (qDoEntej - 1));


            /**
             * policz ratę drogą
             */
            double oprocentowanieDrogie = 5.03;
            double q2 = 1 + (oprocentowanieDrogie / 1200);
            double iloscRat2 = 360;
            double qDoEntej2 = Math.pow(q2, iloscRat2);
            double vRataDroga = (vMaxDostepnyKredyt * qDoEntej2) * ((q2 - 1) / (qDoEntej2 - 1));


            /**
             * policz roznice do splaty
             */

            double vrataDrogaDoSplaty = vRataDroga * 360;
            double vrataTaniaDoSplaty = vRataTania * 360;
            double vroznicaDoSplatyMiesiecznie = vRataDroga - vRataTania;
            double vroznicaDoSplatyRocznie = vroznicaDoSplatyMiesiecznie * 12;
            double vroznicaDoSplatyLacznie = vrataDrogaDoSplaty - vrataTaniaDoSplaty;


            /**
             * nie pokazuj ujemnej zdolności
             */

            if (vMaxDostepnyKredyt < 0) {
                //vMaxDostepnyKredyt = 0;
                maxKredyt.setText("brak zdolności");
            } else {
                /**
                 * This method displays the given text on the screen.
                 */
                maxKredyt.setText(new java.text.DecimalFormat(" #,###" + " zł").format(vMaxDostepnyKredyt));
                cenaM.setText(new java.text.DecimalFormat(" #,###" + " zł").format(vCenaM));
                wkladWlasnyKwota.setText(new java.text.DecimalFormat(" #,###" + " zł").format(vWkladWlasnyKwota));
                rataTania.setText(new java.text.DecimalFormat("rata" + " ####" + " zł").format(vRataTania));

                rataDroga.setText(new java.text.DecimalFormat("rata" + " ####" + " zł").format(vRataDroga));

                roznicaDoSplatyMiesiecznie.setText(new java.text.DecimalFormat("  #,###" + " zł").format(vroznicaDoSplatyMiesiecznie));

                roznicaDoSplatyRocznie.setText(new java.text.DecimalFormat("  #,###" + " zł").format(vroznicaDoSplatyRocznie));

                roznicaDoSplaty30lat.setText(new java.text.DecimalFormat("  #,###" + " zł").format(vroznicaDoSplatyLacznie));


                // minKredyt.setText(new java.text.DecimalFormat(" #,###" + " zł").format(vMaxDostepnyKredyt * 0.535545));


                /**
                 * scroll to the bottom of screen
                 */
                //ScrollView sv = (ScrollView) findViewById(R.id.scroll);
                //sv.scrollTo(0, sv.getBottom());
            }
        }
    }

    public void porownaj_oferty(View view) {
        Intent intent = new Intent(this, Formularz.class);
        startActivity(intent);

        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("porownaj_oferty_button", params);


    }


    public void oblicz_raty(View clickedButton) {

        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("oblicz_raty_button", params);





        mKwotaKredytu = (EditText) findViewById(R.id.kwota_kredytu);
        mLiczbaLat = (EditText) findViewById(R.id.liczba_lat);
        mOprocentowanie = (EditText) findViewById(oprocentowanie);
        mRRWysokoscRaty = (TextView) findViewById(R.id.RR_wysokosc_raty);
        mRRLacznieDoSplaty = (TextView) findViewById(R.id.RR_lacznie_do_splaty);
        mRRSumaOdsetek = (TextView) findViewById(R.id.RR_suma_odsetek);
        mRM_pierwsza = (TextView) findViewById(R.id.RM_pierwsza);
        mRM_ostatnia = (TextView) findViewById(R.id.RM_ostatnia);
        mRM_sumaOdsetek = (TextView) findViewById(R.id.RM_suma_odsetek);
        mRM_lacznieDoSplaty = (TextView) findViewById(R.id.RM_lacznie_do_splaty);


        // Check if no view has focus and turn off keyboard

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


        double kwotaKredytu = 0;
        double liczbaLat = 0;
        double oprocentowanie = 0.00001;

        if (mKwotaKredytu.getText().length() > 0) {
            kwotaKredytu = Double.parseDouble(mKwotaKredytu.getText().toString());
        }

        if (mLiczbaLat.getText().length() > 0) {
            liczbaLat = Double.parseDouble(mLiczbaLat.getText().toString());
        }

        if (mOprocentowanie.getText().length() > 0.00001) {
            oprocentowanie = Double.parseDouble(mOprocentowanie.getText().toString());
        }


        double q = 1 + (oprocentowanie / 1200);
        double iloscRat = liczbaLat * 12;
        double qDoEntej = Math.pow(q, iloscRat);

        double RRwysokoscRaty = (kwotaKredytu * qDoEntej) * ((q - 1) / (qDoEntej - 1));
        double RRLacznieDoSplaty = (RRwysokoscRaty * iloscRat);
        double RRSumaOdsetek = (RRLacznieDoSplaty - kwotaKredytu);
        double RM_pierwsza = (kwotaKredytu / iloscRat) + (kwotaKredytu * (oprocentowanie / 1200));
        double RM_ostatnia = kwotaKredytu / iloscRat;
        double do_odsetek = (kwotaKredytu * (oprocentowanie / 1200)) + ((kwotaKredytu / iloscRat) * (oprocentowanie / 1200));
        double RM_sumaOdsetek = (do_odsetek / 2) * iloscRat;
        double RM_lacznieDoSplaty = kwotaKredytu + RM_sumaOdsetek;


        /**
         * This method displays the given text on the screen.
         */
        mRRWysokoscRaty.setText(new java.text.DecimalFormat("  #,###" + " zł").format(RRwysokoscRaty));
        mRRSumaOdsetek.setText(new java.text.DecimalFormat("  #,###" + " zł").format(RRSumaOdsetek));
        mRRLacznieDoSplaty.setText(new java.text.DecimalFormat("  #,###" + " zł").format(RRLacznieDoSplaty));
        mRM_pierwsza.setText(new java.text.DecimalFormat("  #,###" + " zł").format(RM_pierwsza));
        mRM_ostatnia.setText(new java.text.DecimalFormat("  #,###" + " zł").format(RM_ostatnia));
        mRM_sumaOdsetek.setText(new java.text.DecimalFormat("  #,###" + " zł").format(RM_sumaOdsetek));
        mRM_lacznieDoSplaty.setText(new java.text.DecimalFormat("  #,###" + " zł").format(RM_lacznieDoSplaty));


    }


    public void oblicz_notariusz(View clickedButton) {


        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("oblicz_notariusz_button", params);



        mCenaZakupu = (EditText) findViewById(R.id.cena_zakupu);
        mMaxTaksa = (TextView) findViewById(R.id.wysokosc_taksy);
        mWysokoscPodatku = (TextView) findViewById(R.id.wysokosc_podatku);
        mWysokoscTaksyBrutto = (TextView) findViewById(R.id.wysokosc_taksy_brutto);

        mMaxTaksaPrzedwstepna = (TextView) findViewById(R.id.wysokosc_taksy_przedwstepna);
        mWysokoscPodatkuPrzedwstepna = (TextView) findViewById(R.id.wysokosc_podatku_przedwstepna);
        mWysokoscTaksyBruttoPrzedwstepna = (TextView) findViewById(R.id.wysokosc_taksy_brutto_przedwstepna);

        mWysokoscPCC = (TextView) findViewById(R.id.wysokosc_PCC);

        // Check if no view has focus and turn off keyboard

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        double cenaZakupu = 0;
        double maxTaksa = 0;
        if (mCenaZakupu.getText().length() > 0) {
            cenaZakupu = Double.parseDouble(mCenaZakupu.getText().toString());
        }

        if (cenaZakupu >= 2000000) {
            maxTaksa = 6770 + (cenaZakupu - 2000000) * 0.0025;
        } else if (cenaZakupu >= 1000000) {
            maxTaksa = 4770 + (cenaZakupu - 1000000) * 0.002;
        } else if (cenaZakupu >= 60000) {
            maxTaksa = 1010 + (cenaZakupu - 60000) * 0.004;
        } else if (cenaZakupu >= 30000) {
            maxTaksa = 710 + (cenaZakupu - 30000) * 0.01;
        } else if (cenaZakupu >= 10000) {
            maxTaksa = 310 + (cenaZakupu - 10000) * 0.02;
        } else if (cenaZakupu >= 3000) {
            maxTaksa = 100 + (cenaZakupu - 3000) * 0.03;
        } else if (cenaZakupu > 0) {
            maxTaksa = 100;
        }


        double wysokoscPodatku = maxTaksa * 0.23;
        double wysokoscTaksyBrutto = maxTaksa + wysokoscPodatku;


        //double maxTaksaPrzedwstepna = maxTaksa / 2;
        //double wysokoscPodatkuPrzedwstepna = wysokoscPodatku / 2;
        //double wysokoscTaksyBruttoPrzedwstepna = wysokoscTaksyBrutto / 2;

        //Oblicz wysokość maksymalnej taksy da umowy ostatecznej (końcowej)
        mMaxTaksa.setText(new java.text.DecimalFormat("  #,###" + " zł").format(maxTaksa));
        mWysokoscPodatku.setText(new java.text.DecimalFormat("  #,###" + " zł").format(wysokoscPodatku));
        mWysokoscTaksyBrutto.setText(new java.text.DecimalFormat("  #,###" + " zł").format(wysokoscTaksyBrutto));


        //Oblicz wysokość maksymalnej taksy da umowy przedwstępnej
        mMaxTaksaPrzedwstepna.setText(new java.text.DecimalFormat("  #,###" + " zł").format(maxTaksa / 2));
        mWysokoscPodatkuPrzedwstepna.setText(new java.text.DecimalFormat("  #,###" + " zł").format(wysokoscPodatku / 2));
        mWysokoscTaksyBruttoPrzedwstepna.setText(new java.text.DecimalFormat("  #,###" + " zł").format(wysokoscTaksyBrutto / 2));

        //Oblicz wysokość podatku PCC
        mWysokoscPCC.setText(new java.text.DecimalFormat("  #,###" + " zł").format(cenaZakupu * 0.02));
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Kalkulatory_Zdolnosc(), "zdolność kredytowa");
        adapter.addFragment(new Fragment_Kalkulatory_Raty(), "raty i koszty");
        adapter.addFragment(new Fragment_Kalkulatory_Notariusz(), "notariusz");
        viewPager.setAdapter(adapter);
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
        mFirebaseAnalytics.logEvent("Subskrypcja_zakup_zdolnosc", params);


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
            mFirebaseAnalytics.logEvent("SUB_zakup_zdol_error_" + errorCode, params);

        }else{
            mIsSubscribed = true;
            Toast.makeText(this, "Dziękuję za subskrypcję :)", Toast.LENGTH_LONG).show();
            //Zapisz subskrybcję w Shared Preferences
            SaveBoolean("isSubscribed", mIsSubscribed);

            //Firebase Event
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle params = new Bundle();
            mFirebaseAnalytics.logEvent("SUB_zakup_juz_byl_zdolnosc", params);
        }


    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    //metoda zapisu INT do SharedPreferences
    public void SaveInt(String key, int value){
      SharedPreferences  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    //metoda zapisu BOOLEAN do SharedPreferences
    public void SaveBoolean(String key, boolean value){

        SharedPreferences  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    //metoda odczytu INT SharedPreferences
    public void LoadInt(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        savedValue = sharedPreferences.getInt("key", 0);
    }

    //metoda odczytu SharedPreferences
    public void LoadBoolean(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        savedIsSubscribed = sharedPreferences.getBoolean("isSubscribed", false);
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
