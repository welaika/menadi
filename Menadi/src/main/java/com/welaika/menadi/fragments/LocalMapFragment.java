package com.welaika.menadi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.welaika.menadi.CustomWebClient;
import com.welaika.menadi.JsInterface;
import com.welaika.menadi.MainActivity;
import com.welaika.menadi.R;

public class LocalMapFragment extends Fragment {

    private String arg;
    private View view;
    private WebView webView;
    private boolean mIsWebViewAvailable;

    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        arg = getArguments().getString("map1");
        view = inflater.inflate(R.layout.fragment_local_map, container, false);

        webView = (WebView) view.findViewById(R.id.local_map_webview);

        setMap();

        return view;
    }

    public void setMap() {
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new CustomWebClient());
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new JsInterface((MainActivity) getActivity()), "Android");
        webView.loadUrl("file:///android_asset/map/html_map.html");
    }


    //Gets the WebView.
    public WebView getWebView() {
        return mIsWebViewAvailable ? webView : null;
    }
}
