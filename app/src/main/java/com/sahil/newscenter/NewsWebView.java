package com.sahil.newscenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.Objects;

public class NewsWebView extends AppCompatActivity {
    Toolbar toolbar;
    WebView webView;
    TextView notAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web_view);
        toolbar = findViewById(R.id.toolbar_show2);
        notAvailable = findViewById(R.id.notAvailable);
        webView = findViewById(R.id.webView);
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_chevron_left));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String title = bundle.getString("newsTitle");
            if(title != null && !title.isEmpty()) {
                getSupportActionBar().setTitle(title);
            }else{
                getSupportActionBar().setTitle("Unknown title...");
            }
            String url = bundle.getString("webUrl");
            if(url != null && !url.isEmpty()) {
                webView.loadUrl(url);
            }else{
                webView.setVisibility(View.GONE);
                notAvailable.setVisibility(View.VISIBLE);
                notAvailable.setText("404 not found...");
            }
        }else {
            getSupportActionBar().setTitle("No results");
            webView.setVisibility(View.GONE);
            notAvailable.setVisibility(View.VISIBLE);
        }
    }
}