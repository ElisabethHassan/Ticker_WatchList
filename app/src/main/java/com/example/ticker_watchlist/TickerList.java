package com.example.ticker_watchlist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;


public class TickerList extends Fragment {
    ListView listview;
    ArrayAdapter<String> adapter;
    TickerListViewModel myViewModel;


    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String ticker = (String) adapterView.getItemAtPosition(i);
            myViewModel.getUrl(ticker);
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewModel = new ViewModelProvider(getActivity()).get(TickerListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);
        listview = view.findViewById(R.id.listview_id);
        LinkedList<String> tickerlist = new LinkedList<>();
        tickerlist.add("AAPL");
        tickerlist.add("TSLA");
        tickerlist.add("SBUX");
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tickerlist);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(clickListener);
        return view;
    }


}