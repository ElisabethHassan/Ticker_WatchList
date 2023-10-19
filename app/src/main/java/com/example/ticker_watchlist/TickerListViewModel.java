package com.example.ticker_watchlist;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class TickerListViewModel extends ViewModel {
    MutableLiveData<String> url = new MutableLiveData<>();

    public MutableLiveData<String> getUrl(String s){
        if(url == null){
            setUrl(s);
        }
        return url;
    }

    public void setUrl(String s){
        if (url == null){
            String urlLink = "https://seekingalpha.com/symbol/" + s;
            url.setValue(urlLink);
        }
    }

}
