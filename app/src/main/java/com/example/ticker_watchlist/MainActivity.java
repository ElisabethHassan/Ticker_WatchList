package com.example.ticker_watchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;
    TickerListViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String message = intent.getStringExtra("sms");

        //sets up fragments
        fg = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fg.beginTransaction().replace(R.id.topTicker_id, new TickerList()).commit();
            fg.beginTransaction().replace(R.id.bottomWeb_id, new WebViewFragment()).commit();
        }
        myViewModel = new ViewModelProvider(this).get(TickerListViewModel.class);

        //sets up SMS permissions
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){
            String[] perms = new String[]{android.Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this,perms, 101);
        }
    }

    @Override //will execute code whenever you get new intent (allow work with single task activities)
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("sms");

        //SMS without the correct format
        if(!message.contains("Ticker:<<") || !message.contains(">>")){
            recreate();
            Toast.makeText(this, "No valid watchlist entry was found", Toast.LENGTH_SHORT).show();
        } else {
            int tickerBegin = message.lastIndexOf('<');
            int tickerEnd = message.indexOf('>');
            String ticker = message.substring(tickerBegin + 1, tickerEnd).toUpperCase();
            if (isValidTicker(ticker) == false){
                recreate();
                Toast.makeText(this, "The ticker was invalid", Toast.LENGTH_SHORT).show();
            } else { //The ticker is valid and in the correct format
                recreate();
                myViewModel.addTickers(ticker);
                myViewModel.setSelectedTicker(ticker);
            }
        }
    }

    //takes in ticker and checks if it is valid
    public boolean isValidTicker(String ticker){
        for (int i = 0; i < ticker.length(); i++){
            if((Character.isLetter(ticker.charAt(i)) == false)) return false;
        }
        return true;
    }

}