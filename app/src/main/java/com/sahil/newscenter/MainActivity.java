package com.sahil.newscenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<NewsObj> allNews;
    TextView loading;
    // private  RequestQueue requestQueue;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_show);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("News Center");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_web));
        recyclerView = findViewById(R.id.newsRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loading = findViewById(R.id.loading);
        allNews = new ArrayList<NewsObj>();
        // requestQueue = Volley.newRequestQueue(MainActivity.this);
        getEverything();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /*
    public void  getAllNews(){
        String url = "https://newsapi.org/v2/everything?q=tesla&from=2021-05-06&sortBy=publishedAt&apiKey=e1627118b2f64705b1a374faed7bbe67";
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray newsArray = response.getJSONArray("articles");
                    for(int i = 0; i < newsArray.length(); i++){
                        JSONObject newsItem = newsArray.getJSONObject(i);
                        NewsObj newsObj = new NewsObj();
                        newsObj.setTitle(newsItem.getString("title"));
                        newsObj.setAuthor(newsItem.getString("author"));
                        newsObj.setDescription(newsItem.getString("description"));
                        newsObj.setPublishDate(newsItem.getString("publishedAt"));
                        newsObj.setImgUrl(newsItem.getString("urlToImage").toString());
                        newsObj.setWebUrl(newsItem.getString("url").toString());
                        allNews.add(newsObj);
                    }
                } catch (JSONException e) {
                    Log.i("JSON Error >> ", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error >> ", error.toString());
            }
        });
        //adding the string request to request queue
        requestQueue.add(jsObjectRequest);
    }
*/

    public  void getEverything(){
        NewsApiClient newsApiClient = new NewsApiClient("e1627118b2f64705b1a374faed7bbe67");
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("tesla")
                        .from("2021-05-06")
                        .sortBy("publishedAt")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        Log.i("Response size ", String.valueOf(response.getArticles().size()));
                        for(int i = 0; i < response.getArticles().size(); i++){
                            NewsObj newsObj = new NewsObj();
                            newsObj.setTitle(response.getArticles().get(i).getTitle());
                            newsObj.setAuthor(response.getArticles().get(i).getAuthor());
                            newsObj.setDescription(response.getArticles().get(i).getDescription());
                            newsObj.setPublishDate(response.getArticles().get(i).getPublishedAt());
                            newsObj.setImgUrl(response.getArticles().get(i).getUrlToImage());
                            newsObj.setWebUrl(response.getArticles().get(i).getUrl());
                            allNews.add(newsObj);
                        }
                        Log.i("AllNews size ", String.valueOf(allNews.size()));
                        if(allNews.size() > 0) {
                            newsAdapter = new NewsAdapter(MainActivity.this, allNews);
                            recyclerView.setAdapter(newsAdapter);
                            loading.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }else {
                            loading.setText("No news available...");
                        }
                    }
                    @Override
                    public void onFailure(Throwable throwable) {
                        loading.setText("No news available...");
                        Log.i("News Error","sgsd");
                    }
                }
        );
    }

}