package com.welaika.menadi;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class CustomWebClient extends WebViewClient {
    String hashtag = "";
    public static boolean flag = false;

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try{
            hashtag = url.substring(url.indexOf("#"),url.length());
            url = url.substring(0,url.indexOf("#"));
        }catch (Exception e) {
            hashtag = "";
        }
        view.loadUrl(url);
        return true;
    }


    public void onPageFinished(WebView view, String url) {
        if (!hashtag.equalsIgnoreCase("") && flag == false) {
            view.loadUrl(url+hashtag);
            flag = true;
        } else {
            flag = false;
        }
    }
}
