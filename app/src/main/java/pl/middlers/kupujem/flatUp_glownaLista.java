package pl.middlers.kupujem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class flatUp_glownaLista {

    private String name;
    private String job;
    private String moj_wiersz;
    private String image;
    private String url;
    private String adres;
    private String mratingSCORE;
    private String encodedImage;

    flatUp_glownaLista(String name, String job, String moj_wiersz, String url, String adres, String mratingSCORE, String encodedImage) {
        this.name = name;
        this.job = job;
        this.moj_wiersz = moj_wiersz;
        this.url = url;
        this.image = encodedImage;
        this.adres = adres;
        this.mratingSCORE = mratingSCORE;
        this.encodedImage = encodedImage;
    }

    String getName() {
        return name;
    }

    String getJob() {
        return job;
    }

    String getMoj_wiersz(){
        return moj_wiersz;
    }

    String getUrl(){
        return url;
    }

    String getAdres (){
        return adres;
    }

    String getMratingSCORE (){
        return mratingSCORE;
    }

    String getImageString(){
        return encodedImage;
    }

    Bitmap getImage(){
        //odkoduj zapisane zdjecie w postaci stringa
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //zwroc zdjecie odzyskane ze stringa
        return decodedImage;

    }



}