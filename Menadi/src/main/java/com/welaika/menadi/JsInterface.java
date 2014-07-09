package com.welaika.menadi;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.welaika.menadi.MainActivity;


public class JsInterface {
    Context mContext;
    MainActivity activity;


    public JsInterface(MainActivity activity) {
        mContext = activity.getApplicationContext();
        this.activity = activity;
    }

    @JavascriptInterface
    public void showToast(String toast) {
    Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void goToRoom(String s) {

        showToast(s);

        activity.replaceWithRoomActivity(s);
    }
}
