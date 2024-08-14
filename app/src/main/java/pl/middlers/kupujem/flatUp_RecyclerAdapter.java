package pl.middlers.kupujem;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;


public class flatUp_RecyclerAdapter extends RecyclerView.Adapter<flatUp_RecyclerAdapter.ViewHolder> {

    private FirebaseAnalytics mFirebaseAnalytics;

    private static List<flatUp_glownaLista> cardInfoArrayList;

   // public List<String> listaNotatek = new ArrayList<String>();

    Context context;

    private Activity activity;

    TextView instrukcja;
    Button button;

    public static int positionX;

    com.github.clans.fab.FloatingActionMenu floatingActionMenu;


    flatUp_RecyclerAdapter(Activity activity, List<flatUp_glownaLista> friends) {
        this.cardInfoArrayList = friends;
        this.activity = activity;

   //     listaNotatek.add("taki automatyczny adres1");
   //     listaNotatek.add("taki automatyczny adres2");

        Integer ileItemowWidac = getItemCount();
        instrukcja = (TextView) activity.findViewById(R.id.instrukcja_TextView);
        button = (Button) activity.findViewById(R.id.button2);



        // jesli jest krótszy lub równy 3
        if (ileItemowWidac < 1){
            instrukcja.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);

        }
        else {
            instrukcja.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
        }

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {



        //inflate your layout and pass it to view holder
        LayoutInflater inflater = activity.getLayoutInflater();


        View itemView = inflater.inflate(R.layout.single_card, viewGroup, false);
        return new ViewHolder(itemView);




    }


    @Override
    public void onBindViewHolder(flatUp_RecyclerAdapter.ViewHolder viewHolder, final int position) {

        //setting data to view holder elements
        viewHolder.tytul.setText(cardInfoArrayList.get(viewHolder.getAdapterPosition()).getName());
        viewHolder.domena.setText(cardInfoArrayList.get(viewHolder.getAdapterPosition()).getJob());
        viewHolder.imageView.setImageBitmap(cardInfoArrayList.get(viewHolder.getAdapterPosition()).getImage());
        viewHolder.notatki.setText(cardInfoArrayList.get(viewHolder.getAdapterPosition()).getMoj_wiersz());
        viewHolder.adres.setText(cardInfoArrayList.get(viewHolder.getAdapterPosition()).getAdres());
        viewHolder.rateNumber.setText(cardInfoArrayList.get(viewHolder.getAdapterPosition()).getMratingSCORE());
        viewHolder.rate_star.setRating(Float.parseFloat(cardInfoArrayList.get(viewHolder.getAdapterPosition()).getMratingSCORE()));


        //dodaj tag to edit text
        viewHolder.notatki.setTag(viewHolder.getAdapterPosition());
        viewHolder.adres.setTag(viewHolder.getAdapterPosition());
        //  viewHolder.mratingSCORE.setTag(viewHolder.getAdapterPosition());




    }



    @Override
    public int getItemCount() {
        return (null != cardInfoArrayList ? cardInfoArrayList.size() : 0);
    }


    /**
     * View holder to display each RecylerView item
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, deleteItemButton, pinezka, rateBarCover_ImageView ;
        private RatingBar rate_star;
        private TextView tytul;
        private TextView rateNumber;
        private TextView domena;
        private EditText notatki;
        private EditText adres;






        ViewHolder(final View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.zdjecie);
            pinezka = (ImageView) view.findViewById(R.id.pinezka_ImageView);
            deleteItemButton = (ImageView) view.findViewById(R.id.deleteItem);
            tytul = (TextView) view.findViewById(R.id.tytul);
            domena = (TextView) view.findViewById(R.id.domena);
            adres = (EditText) view.findViewById(R.id.adres_editText);
            notatki = (EditText) view.findViewById(R.id.notatkiEditText);
            rate_star = (RatingBar) view.findViewById(R.id.ratingBarIndicator_View);
            rateNumber = (TextView) view.findViewById(R.id.rateNumber_TextView);
            rateBarCover_ImageView = (ImageView) view.findViewById(R.id.rateBarCover_ImageView);




            //zrób coś, kiedy ktoś kliknie w notatki
            notatki.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                public void afterTextChanged(Editable editable) {
                    if(notatki.getTag()!=null){

                        String mojaNotatka = editable.toString();
                        flatUp_glownaLista cardInfoZamiana = new flatUp_glownaLista(cardInfoArrayList.get(getAdapterPosition()).getName(), cardInfoArrayList.get(getAdapterPosition()).getJob(), mojaNotatka, cardInfoArrayList.get(getAdapterPosition()).getUrl(), cardInfoArrayList.get(getAdapterPosition()).getAdres(), cardInfoArrayList.get(getAdapterPosition()).getMratingSCORE(), cardInfoArrayList.get(getAdapterPosition()).getImageString());
                        cardInfoArrayList.set(getAdapterPosition(), cardInfoZamiana);

                     //   Toast.makeText(view.getContext(), "zmieniłeś notatkę " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    }}

                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            });

            //zrób coś kiedy ktoś kliknie w adres
            adres.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                public void afterTextChanged(Editable editable) {
                    if(adres.getTag()!=null){

                        String mojAdres = editable.toString();
                        flatUp_glownaLista cardInfoZamiana = new flatUp_glownaLista(cardInfoArrayList.get(getAdapterPosition()).getName(), cardInfoArrayList.get(getAdapterPosition()).getJob(), cardInfoArrayList.get(getAdapterPosition()).getMoj_wiersz(), cardInfoArrayList.get(getAdapterPosition()).getUrl(), mojAdres, cardInfoArrayList.get(getAdapterPosition()).getMratingSCORE(), cardInfoArrayList.get(getAdapterPosition()).getImageString());
                        cardInfoArrayList.set(getAdapterPosition(), cardInfoZamiana);

                      //  Toast.makeText(view.getContext(), "zmieniłeś adres  " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    }}

                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            });

            //jak klikniesz w zdjęcie, to otwórz dany link w przeglądarce
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  Toast.makeText(view.getContext(), "kliknąłeś numer karty: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    //weź właściwy url
                    String url = cardInfoArrayList.get(getAdapterPosition()).getUrl();

                    //jesli url istnieje otwórz go w przeglądarce
                    if (url != null) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        //dodaj flagi, żeby jak wrócisz z przeglądarki to otwórz z powrotem aplikację tę
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                        view.getContext().startActivity(i);

                        //jesli url nie istnieje
                    } else
                        Toast.makeText(view.getContext(), "nie ma prawidłowego linku  " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }




            });


            //jak klikniesz w pinezkę, to otwórz adres wGoogle Maps
            pinezka.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  Toast.makeText(view.getContext(), "kliknąłeś pinezkę nr : " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    //jesli url istnieje otwórz go w wybranej aplikacji
                    if (adres != null) {
                        //weź właściwy adres
                        String adres = cardInfoArrayList.get(getAdapterPosition()).getAdres();
                        String map = "http://maps.google.co.in/maps?q=" + adres;
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                        view.getContext().startActivity(i);
                        //jesli adres nie istnieje
                    } else
                        Toast.makeText(view.getContext(), "nie ma adresu  " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });



            //jak klikniesz w śmietnik to usuń zdjecie
            deleteItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Toast.makeText(view.getContext(), "kliknąłeś deleteItemButton: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    //Otwórz dialog potwierdzający usuwanie!
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                    builder1.setTitle("Usunąć wybrany element?");
                    builder1.setMessage("UWAGA! Tej operacji nie można cofnąć!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Usuń",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {




                                    //i usun dany kafelek
                                    cardInfoArrayList.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    notifyItemRangeChanged(getAdapterPosition(), cardInfoArrayList.size());
                                    dialog.cancel();


                                    //Firebase Event
                                    mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
                                    Bundle params4 = new Bundle();
                                    mFirebaseAnalytics.logEvent("flatUp_karta_usunieto", params4);

                                    //wyświetl FAB, jeśli na ekranie po skasowaniu karty, zostało 2 lub mniej kart
                                    Integer liczbaKart = getItemCount();
                                    if (liczbaKart <=2){
                             //           floatingActionMenu = (com.github.clans.fab.FloatingActionMenu) activity.findViewById(R.id.fab00);
                              //          floatingActionMenu.showMenuButton(true);
                                    }
                                    if (liczbaKart <1 ) {
                                        instrukcja.setVisibility(View.VISIBLE);
                                        button.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                    builder1.setNegativeButton(
                            "Anuluj",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                }
            });


            //jak klikniesz w śmietnik to usuń zdjecie
            rateBarCover_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(view.getContext(), "kliknąłeś gwiazdkę: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    // otworz rate dialog
                    LayoutInflater li = LayoutInflater.from(activity);
                    final View rank_dialog = li.inflate(R.layout.rank_dialog, null);

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);

                    //pokaż gdzie są ratingBary i je nazwij
                    final RatingBar mBar1 = (RatingBar) rank_dialog.findViewById(R.id.ratingBar1);
                    final RatingBar mBar2 = (RatingBar) rank_dialog.findViewById(R.id.ratingBar2);
                    final RatingBar mBar3 = (RatingBar) rank_dialog.findViewById(R.id.ratingBar3);
                    final RatingBar mBar4 = (RatingBar) rank_dialog.findViewById(R.id.ratingBar4);
                    final RatingBar mBar5 = (RatingBar) rank_dialog.findViewById(R.id.ratingBar5);
                  //  final RatingBar mBar6 = (RatingBar) rank_dialog.findViewById(R.id.ratingBar6);

                    builder1.setView(rank_dialog);
                 //   builder1.setTitle("Oczeń poszczególne elementy");
                 //   builder1.setMessage("uśrednioną oce");

                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "zapisz",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //weź ratingi wszystkich rate bar
                                    final Float ratingBarValue1 = mBar1.getRating();
                                    final Float ratingBarValue2 = mBar2.getRating();
                                    final Float ratingBarValue3 = mBar3.getRating();
                                    final Float ratingBarValue4 = mBar4.getRating();
                                    final Float ratingBarValue5 = mBar5.getRating();
                                //    final Float ratingBarValue6 = mBar6.getRating();

                                    //oblicz śrenią wszystkich ocen
                                    final Float ratingScore = (ratingBarValue1 + ratingBarValue2 + ratingBarValue3 + ratingBarValue4 + ratingBarValue5 ) / 5;

                                    //zamień Float ratingScore na String ratingBarValue1String
                                    String ratingBarValue1String = ratingScore.toString();

                                    //przygotuj String wynikUcinania
                                    String wynikUcinania = "";

                                    //Policz ile znaków ma string ratingBarValue1String
                                    int DlugoscStringa = ratingBarValue1String.length();

                                    // jesli jest krótszy lub równy 3
                                        if (DlugoscStringa <= 3){
                                            //to nic nie rób i weź jaki jest (np. 3.3 lub 2.4, lub 5.0)
                                            wynikUcinania = ratingBarValue1String;
                                        //    Toast.makeText(activity, "wynik ucinania <4 " + wynikUcinania, Toast.LENGTH_SHORT).show();
                                            //jeśli jest dłuższy niż 4
                                        } else {
                                            //to go utnij i zostaw tylko 3 znaki
                                            wynikUcinania = ratingBarValue1String.substring(0,3);
                                       //     Toast.makeText(activity, "wynik ucinania >4 " + wynikUcinania, Toast.LENGTH_SHORT).show();

                                        }


                                            rate_star.setRating(Float.parseFloat(ratingBarValue1String));
                                            // String mojAdres = editable.toString();

                                            rateNumber.setText(wynikUcinania);

                                            flatUp_glownaLista cardInfoZamiana = new flatUp_glownaLista(cardInfoArrayList.get(getAdapterPosition()).getName(), cardInfoArrayList.get(getAdapterPosition()).getJob(), cardInfoArrayList.get(getAdapterPosition()).getMoj_wiersz(), cardInfoArrayList.get(getAdapterPosition()).getUrl(), cardInfoArrayList.get(getAdapterPosition()).getAdres(), wynikUcinania, cardInfoArrayList.get(getAdapterPosition()).getImageString());
                                            cardInfoArrayList.set(getAdapterPosition(), cardInfoZamiana);


                                }
                            });

                    builder1.setNegativeButton(
                            "Anuluj",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                }
            });


            //jak długo klikniesz w zdjęcie, to pokaz text ze dlugo kliknales i usun dany kafelek
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(view.getContext(), "długo kliknąłeś numer karty: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    //jest true, zeby po długim kliku nie wzywać krótkiego klika
                    return true;
                }

            });




        }










    }


}