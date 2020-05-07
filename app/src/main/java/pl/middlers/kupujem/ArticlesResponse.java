package pl.middlers.kupujem;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ArticlesResponse {
    private String title;
    private String domain;
    private String date;
    private String image;
    private String url;



    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl (String url){
        this.url = url;
    }
}