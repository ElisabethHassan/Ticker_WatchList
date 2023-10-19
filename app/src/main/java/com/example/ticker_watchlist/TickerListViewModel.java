package com.example.ticker_watchlist;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class TickerListViewModel extends ViewModel {
    MutableLiveData<String> url = new MutableLiveData<>();
    MutableLiveData<LinkedList<String>> tickers = new MutableLiveData<>();
    LinkedList<String> tickerlist = new LinkedList<>();


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

    public MutableLiveData<LinkedList<String>> getTickers(){
        if (tickers == null){
            setTickers();
        }
        return tickers;
    }

    public void setTickers(){
        if(tickers == null){
            tickerlist.add("AAPL");
            tickerlist.add("TSLA");
            tickerlist.add("SBUX");
            tickers.setValue(tickerlist);
        }
    }

    public void addTickers(String ticker){
        if(tickerlist.size() <= 6){
            tickerlist.add(ticker);
            tickers.setValue(tickerlist);
        }
        //TODO: Let entries past 6, replace the beginning
    }


}
