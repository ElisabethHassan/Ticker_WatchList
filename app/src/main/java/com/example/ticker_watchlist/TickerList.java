package com.example.ticker_watchlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);
        listview = (ListView) view.findViewById(R.id.listview_id);
        LinkedList<String> tickerlist = new LinkedList<>();
        tickerlist.add("AAPL");
        tickerlist.add("TSLA");
        tickerlist.add("SBUX");
        tickerlist.add("NKE");
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tickerlist);
        listview.setAdapter(adapter);
        return view;
    }
}