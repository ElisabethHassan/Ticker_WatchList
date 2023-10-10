package com.example.ticker_watchlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TickerListViewModel extends ViewModel {
    MutableLiveData<String> url = new MutableLiveData<>();

    public LiveData<String> getUrl(){
        if(url == null){
            setUrl();
        }
        return url;
    }

    public void setUrl(){
        if (url == null){
            String urlLink = "https://seekingalpha.com";
            url.setValue(urlLink);
        }
    }

}
