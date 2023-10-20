package com.example.ticker_watchlist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewFragment extends Fragment {
    WebView webview;
    TickerListViewModel myViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        webview = view.findViewById(R.id.webview_id);
        return view;
    }

    //loads the webview with the url
    public void loadUrl(String url){
        webview.loadUrl(url);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewModel = new ViewModelProvider(getActivity()).get(TickerListViewModel.class);
        webview.getSettings().setJavaScriptEnabled(true);
        loadUrl("https://seekingalpha.com/");

        //loads the url based on the selected ticker
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){
                    String url = "https://seekingalpha.com/symbol/" + s;
                    loadUrl(url);
                } else {
                    loadUrl("https://seekingalpha.com/");
                }
            }
        };
        myViewModel.getSelectedTicker().observe(getViewLifecycleOwner(),observer);

    }
}