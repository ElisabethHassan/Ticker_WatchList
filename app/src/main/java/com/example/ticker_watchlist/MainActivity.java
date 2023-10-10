package com.example.ticker_watchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fg = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fg.beginTransaction().replace(R.id.topTicker_id, new TickerList()).commit();
            fg.beginTransaction().replace(R.id.bottomWeb_id, new WebViewFragment()).commit();
        }
    }
}