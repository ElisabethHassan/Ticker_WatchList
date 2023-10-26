package com.example.ticker_watchlist;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class TickerListViewModel extends ViewModel {
    MutableLiveData<LinkedList<String>> tickers = new MutableLiveData<>();
    LinkedList<String> tickerlist = new LinkedList<>();
    MutableLiveData<String> selectedTicker = new MutableLiveData<>();
    int currentIndex = 0;


    //sets the ticker that is selected
    public void setSelectedTicker(String ticker){
        selectedTicker.setValue(ticker);
    }

    //returns the ticker that is selected
    public LiveData<String> getSelectedTicker(){
        return selectedTicker;
    }


    public LiveData<LinkedList<String>> getTickers(){
        if (tickerlist.size() == 0) setTickers();

        return tickers;
    }

    public void setTickers(){
        if(tickerlist.size() == 0){
            tickerlist.add("AAPL");
            tickerlist.add("TSLA");
            tickerlist.add("SBUX");
            tickers.setValue(tickerlist);
        }
    }

    //once list hits 6 entries changes the 6th to the new update
//    public void addTickers(String ticker){
//        if(tickerlist.size() >= 6 && !tickerlist.contains(ticker)){
//            tickerlist.set(5, ticker);
//        } else if(tickerlist.size() < 6 && !tickerlist.contains(ticker)) {
//            tickerlist.add(ticker);
//            tickers.setValue(tickerlist);
//        }
//    }

    //Round Robin Implementation
    public void addTickers(String ticker){

        if(tickerlist.size() >= 6 && !tickerlist.contains(ticker)){
            tickerlist.set(currentIndex, ticker);
            currentIndex = (currentIndex + 1) % 6;
        } else if(tickerlist.size() < 6 && !tickerlist.contains(ticker)) {
            tickerlist.add(ticker);
            tickers.setValue(tickerlist);
        }
    }


}
