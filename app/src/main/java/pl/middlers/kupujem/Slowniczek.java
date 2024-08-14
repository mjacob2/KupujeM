package pl.middlers.kupujem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Slowniczek extends AppCompatActivity {

    private Toolbar toolbar;
    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slowniczek);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        toolbar = (Toolbar) findViewById(R.id.toolbarSlowniczek);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Słowniczek");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //Pokoloruj navigation bar na biało
      //  if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
       //     getWindow().setNavigationBarColor(getResources().getColor(R.color.fafafa));
       // }



        // Listview Data
        String products[] = {"Zdolność kredytowa", "Raty równe", "Raty malejące", "Krajowy Rejestr Sądowy KRS", "Hipoteka",
                "Transza", "Wspólnota mieszkaniowa", "Spółdzielnia", "Rękojmia wiary publicznej Ksiąg Wieczystych KW", "LTV" , "Gospodarstwo domowe",
                "Protokół odbioru", "Umowa deweloperska", "Umowa ostateczna", "Księga Wieczysta KW", "Doradca finansowy" , "Zadatek", "Zaliczka", "Powierzchnia użytkowa", "Powierzchnia całkowita"};
        Arrays.sort(products);
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);



        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String products = String.valueOf(parent.getItemAtPosition(position));
                        if (products.equals("Zdolność kredytowa")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Zdolność kredytowa");
                            builder.setMessage(R.string.Zdolnosc_kredytowa);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();



                        }else if (products.equals("Powierzchnia użytkowa")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Powierzchnia użytkowa");
                            builder.setMessage(R.string.Powierzchnia_uzytkowa);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();


                        }else if (products.equals("Powierzchnia całkowita")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Powierzchnia całkowita");
                            builder.setMessage(R.string.Powierzchnia_calkowita);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Raty równe")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Raty równe");
                            builder.setMessage(R.string.Raty_rowne);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();


                        }else if (products.equals("Raty malejące")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Raty malejące");
                            builder.setMessage(R.string.Raty_malejace);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Krajowy Rejestr Sądowy KRS")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Krajowy Rejestr Sądowy KRS");
                            builder.setMessage(R.string.KRS);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Hipoteka")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Hipoteka");
                            builder.setMessage(R.string.Hipoteka);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Transza")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Transza");
                            builder.setMessage(R.string.Transza);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();


                        }else if (products.equals("Protokół odbioru")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Protokół odbioru");
                            builder.setMessage(R.string.Protokol_odbioru);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Umowa deweloperska")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Umowa deweloperska");
                            builder.setMessage(R.string.Umowa_deweloperska);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Umowa ostateczna")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Umowa ostateczna");
                            builder.setMessage(R.string.Umowa_ostateczna);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Księga Wieczysta KW")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Księga Wieczysta KW");
                            builder.setMessage(R.string.Ksiega_Wieczysta_KW);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Doradca finansowy")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Doradca finansowy");
                            builder.setMessage(R.string.Doradca_finasowy);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Wspólnota mieszkaniowa")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Wspólnota mieszkaniowa");
                            builder.setMessage(R.string.wspolnota_mieszkaniowa);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Spółdzielnia")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Spółdzielnia");
                            builder.setMessage(R.string.spoldzielnia);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Rękojmia wiary publicznej Ksiąg Wieczystych KW")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Rękojmia wiary publicznej Ksiąg Wieczystych KW");
                            builder.setMessage(R.string.rekojmia);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("LTV")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("LTV");
                            builder.setMessage(R.string.ltv);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Gospodarstwo domowe")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Gospodarstwo domowe");
                            builder.setMessage(R.string.gospodarstwo_domowe);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Zaliczka")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Zaliczka");
                            builder.setMessage(R.string.zaliczka);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }else if (products.equals("Zadatek")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Slowniczek.this);
                            builder.setTitle("Zadatek");
                            builder.setMessage(R.string.zadatek);
                            builder.setPositiveButton("zamknij", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }






                    }
                }
        );













        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                Slowniczek.this.adapter.getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });



    }

}
