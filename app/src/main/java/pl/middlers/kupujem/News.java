package pl.middlers.kupujem;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class News extends AppCompatActivity {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    @BindView(R.id.article_list)
    RecyclerView mRecyclerView;

    public String value, value2;

    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    private Toolbar toolbar;

    //do Firebase
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        init();
        getFriendList();


        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Pokoloruj navigation bar na biało
       // if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
       //     getWindow().setNavigationBarColor(getResources().getColor(R.color.fafafa));
       // }


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        mFirebaseAnalytics.logEvent("News_tile_open", params);

    }

    private void init(){
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        db = FirebaseFirestore.getInstance();
    }

    private void getFriendList(){



        Query query = db.collection("articles").orderBy("nr", Query.Direction.DESCENDING);



        FirestoreRecyclerOptions<ArticlesResponse> response = new FirestoreRecyclerOptions.Builder<ArticlesResponse>()
                .setQuery(query, ArticlesResponse.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ArticlesResponse, FriendsHolder>(response) {
            @Override
            public void onBindViewHolder(FriendsHolder holder, int position, ArticlesResponse model) {
                progressBar.setVisibility(View.GONE);
                holder.textTitle.setText(model.getTitle());
                holder.textDomain.setText(model.getDomain());
                holder.textDate.setText(model.getDate());
                holder.textUrl.setText(model.getUrl());
                Glide.with(getApplicationContext())
                        .load(model.getImage())
                        .into(holder.imageView);



                //on click item
                holder.itemView.setOnClickListener((View v) -> {



                    //    Snackbar.make(mRecyclerView, model.getTitle()+", "+model.getDomain()+" at "+model.getDate(), Snackbar.LENGTH_LONG)
                    //            .setAction("Action", null).show();


                    //   openWebView();


                    //   Uri uri = Uri.parse("https://www.google.com");
                    //   Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    //   startActivity(intent);


                    //open activity
                    value = model.getUrl();
                    value2 = model.getTitle();
                    Intent myIntent = new Intent(News.this, WebView.class);
                    myIntent.putExtra("key", value); //Optional parameters
                    myIntent.putExtra("title", value2);
                    //  myIntent.putExtra("key2", value2);
                    startActivity(myIntent);



                });
            }

            //kiedy zmieni się liczba dokumentów, wróć na pozycję pocztkową (na samą górę)
            @Override
            public void onDataChanged() {
                mRecyclerView.smoothScrollToPosition(0);
            }


            @Override
            public FriendsHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.single_news_item, group, false);

                return new FriendsHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };

        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);
    }



    public class FriendsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView textTitle;
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.domain)
        TextView textDomain;
        @BindView(R.id.date)
        TextView textDate;
        @BindView(R.id.url)
        TextView textUrl;

        public FriendsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}

