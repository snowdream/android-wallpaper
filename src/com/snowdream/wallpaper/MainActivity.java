
package com.snowdream.wallpaper;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.snowdream.wallpaper.net.AsyncHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    public void test(){
        AsyncHttpClient.get("1bf862e3-a6a7-11e2-8058-026bc5020299", null, new AsyncHttpResponseHandler(){

            @Override
            public void onFailure(Throwable arg0, String arg1) {
                // TODO Auto-generated method stub
                super.onFailure(arg0, arg1);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int arg0, String arg1) {
                super.onSuccess(arg0, arg1);
            }

            @Override
            public void onSuccess(String arg0) {
                Log.i("", arg0);
            }
        });
    }
}
