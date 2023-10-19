package com.example.ticker_watchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;
    TickerListViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fg = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fg.beginTransaction().replace(R.id.topTicker_id, new TickerList()).commit();
            fg.beginTransaction().replace(R.id.bottomWeb_id, new WebViewFragment()).commit();
        }

        myViewModel = new ViewModelProvider(this).get(TickerListViewModel.class);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION)
                != PackageManager.PERMISSION_GRANTED){
            String[] perms = new String[]{Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION};
            ActivityCompat.requestPermissions(this,perms, 101);
        }
    }
}