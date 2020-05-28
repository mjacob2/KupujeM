package pl.middlers.kupujem;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class Tabs_Kroki extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_kroki);

        toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Zakup M krok po kroku");


        //Pokoloruj navigation bar na biało
      //  if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //      getWindow().setNavigationBarColor(getResources().getColor(R.color.fafafa));
      //  }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Kroki_Nowe(), "nowe od dewelopera");
        adapter.addFragment(new Fragment_Kroki_Uzywane(), "używane z drugiej ręki");
        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
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

    // kiedy klikniesz żeby wysłać email
    public void sendEmail(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:kupujem.app@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Proszę o kontakt do doradcy finansowego");
        intent.putExtra(Intent.EXTRA_TEXT, "imię i nazwisko: \n\nnr. telefonu: \n\nmiasto:\n\n");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



    // kiedy klikniesz w krok o sprawdzeniu dewelopera
    public void sprawdz_dewelopera (View view){
        Intent intent = new Intent(this, Nowe_prawdz_dewelopera.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o prospekcie
    public void prospekt (View view){
        Intent intent = new Intent(this, Nowe_prospekt.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o negocjacjach
    public void negocjacje (View view){
        Intent intent = new Intent(this, Nowe_negocjacje.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o umowie deweloperskiej
    public void deweloperska (View view){
        Intent intent = new Intent(this, Nowe_deweloperska.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o kredycie hipotecznym
    public void kredyt (View view){
        Intent intent = new Intent(this, Nowe_kredyt.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o doradcy finansowym
    public void doradca (View view){
        Intent intent = new Intent(this, Formularz.class);
        startActivity(intent);
    }


    // kiedy klikniesz w krok o zapłacie
    public void zaplac (View view){
        Intent intent = new Intent(this, Nowe_zaplac.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o odbiorze mieszkania
    public void odbior (View view){
        Intent intent = new Intent(this, Nowe_odbior.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o podłączeniu prądu i mediów
    public void prad_i_media (View view){
        Intent intent = new Intent(this, Nowe_media.class);
        startActivity(intent);
    }


    // kiedy klikniesz w krok o umowie notarialnej
    public void notariusz (View view){
        Intent intent = new Intent(this, Nowe_notariusz.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o umowie wpisaniu hipoteki
    public void hipoteka (View view){
        Intent intent = new Intent(this, Nowe_hipoteka.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o ubezpieczeniu
    public void ubezpiecz (View view){
        Intent intent = new Intent(this, Nowe_ubezpiecz.class);
        startActivity(intent);
    }

    // kiedy klikniesz w krok o sprawach urzędowych
    public void sprawy_urzedowe (View view){
        Intent intent = new Intent(this, Nowe_urzad.class);
        startActivity(intent);
    }

















    // kiedy klikniesz w krok o sprawdzeniu sprzedawcy
    public void sprawdz_sprzedajacegoWtorny (View view){
        Intent intent = new Intent(this, Uzywane_sprawdz_sprzedajacego.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o negocjacjach
    public void negocjacjeWtorny (View view){
        Intent intent = new Intent(this, Uzywane_negocjacje.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o umowie przedwstępnej
    public void przedwstepnaWtorny (View view){
        Intent intent = new Intent(this, Uzywane_przedwstepna.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o kredycie hipotecznym
    public void kredytWtorny (View view){
        Intent intent = new Intent(this, Nowe_kredyt.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o sprawach urzędowych
    public void notarialnaWtorny (View view){
        Intent intent = new Intent(this, Uzywane_notariusz.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o zapłacie za mieszkanie
    public void zaplacWtorny (View view) {
        Intent intent = new Intent(this, Uzywane_zaplac.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o zapłacie za mieszkanie
    public void ubezpieczWtorny (View view) {
        Intent intent = new Intent(this, Nowe_ubezpiecz.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o umowie wpisaniu hipoteki
    public void hipotekaWtorny (View view) {
        Intent intent = new Intent(this, Nowe_hipoteka.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o umowie wpisaniu hipoteki
    public void odbiorWtorny (View view) {
        Intent intent = new Intent(this, Uzywane_odbior.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o umowie wpisaniu hipoteki
    public void prad_i_mediaWtorny (View view) {
        Intent intent = new Intent(this, Uzywane_media.class);
        startActivity(intent);
    }
    // kiedy klikniesz w krok o sprawach urzędowych
    public void sprawy_urzedoweWtorny (View view){
        Intent intent = new Intent(this, Nowe_urzad.class);
        startActivity(intent);
    }
}

