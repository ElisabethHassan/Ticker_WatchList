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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        webview = (WebView) view.findViewById(R.id.webview_id);

//        webview.loadUrl("https://seekingalpha.com/symbol/TSLA");

        return view;
    }

    Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            webview.loadUrl(myViewModel.getUrl(s).getValue());
            webview.getSettings().setJavaScriptEnabled(true);
        }
    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewModel = new ViewModelProvider(getActivity()).get(TickerListViewModel.class);
        myViewModel.url.observe(getViewLifecycleOwner(),observer);


    }
}