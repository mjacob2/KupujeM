package pl.middlers.kupujem;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.analytics.FirebaseAnalytics;




public class SendEmail extends AppCompatActivity implements View.OnClickListener {

    //Declaring Toolbar
    private Toolbar toolbar;
    //Declaring EditText
    private EditText editTextPreferowany;
    private EditText editTextImieNazwisko;
    private EditText editTextNrTelefonu;
    private EditText editTextEmail;
    private EditText editTextMiasto;


    private FirebaseAnalytics mFirebaseAnalytics;


    //Information to disclaimer
    private CheckBox checkbox;

    //Send button
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_email);



        //creating toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.tile_kredyt_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Czy pokazywać cofającą strzałkę?

        //Pokoloruj navigation bar na biało
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.fafafa));
        }

        //Initializing the views
        editTextPreferowany = (EditText) findViewById(R.id.preferowany_czas_kontaktu_editText);
        editTextImieNazwisko = (EditText) findViewById(R.id.imie_i_nazwisko_editText);
        editTextNrTelefonu = (EditText) findViewById(R.id.nr_telefonu_editText);
        //editTextEmail = (EditText) findViewById(R.id.email_editText);
        editTextMiasto = (EditText) findViewById(R.id.miasto_editText);
        buttonSend = (Button) findViewById(R.id.emailSend_button);
        checkbox = (CheckBox) findViewById(R.id.checkbox_przetwarzanie_danych);
        //Adding click listener
        buttonSend.setOnClickListener(this);



        //Firebase Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("tile_kredyt_open", params);

    }




    @Override
    public void onClick(View v) {

        //sprawdź czy jest zaznaczony box o wyrażeniu zgodny na przetwarzanie danych, jesli tak - wyślij email, jesli nie - wyświetl powiadomienie

        if (checkbox.isChecked())//checked then
        {
           composeEmail();





            //Firebase Event
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle params = new Bundle();
            mFirebaseAnalytics.logEvent("formularz_wsylano_klientWlasny", params);



        } else{
            Toast.makeText(this, "brak zgodny na przetwarzanie danych",
                    Toast.LENGTH_LONG).show();
        }


        // Check if no view has focus and turn off keyboard
        View r = this.getCurrentFocus();
        if (r != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    }



    // Wysyła email używając Klienta poczty wbudowanego w telefon użytkownika
    private void composeEmail() {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:kupujem.app@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Prośba o kontakt do doradcy kredytowego " + editTextImieNazwisko.getText().toString().trim());
        intent.putExtra(Intent.EXTRA_TEXT,
                         "WSTĘPNA KWOTA KREDYTU:\n"
                        + editTextPreferowany.getText().toString().trim()
                        + "\n\nIMIĘ I NAZWISKO:\n"
                        + editTextImieNazwisko.getText().toString().trim()
                        + "\n\nMIASTO:\n"
                        + editTextMiasto.getText().toString().trim()
                        + "\n\nNR. TELEFONU:\n"
                        + editTextNrTelefonu.getText().toString().trim());


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

    //public void call_doradca(View view) {

     //   Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "692409120"));
    //    startActivity(intent);

  //  }

   // public void  mail_doradca (View view){

   //     Intent intent = new Intent(Intent.ACTION_SENDTO);
   //     intent.setData(Uri.parse("mailto:kupujem.app@gmail.com")); // only email apps should handle this
   //     if (intent.resolveActivity(getPackageManager()) != null) {
   //         startActivity(intent);
   //     }
  //  }

}
