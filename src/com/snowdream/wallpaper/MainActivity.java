
package com.snowdream.wallpaper;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.net.AsyncHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    public void test(){
        AsyncHttpClient.get("http://gallerycms.ap01.aws.af.cm/index.php/api/myfeed/json/3720a0db-a6ab-11e2-8058-026bc5020299", null, new AsyncHttpResponseHandler(){

            @Override
            public void onFailure(Throwable arg0, String arg1) {
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
            public void onSuccess(String json) {
                Log.i("", json);
                Gson gson = new Gson();
                /*Album album = gson.fromJson(json, Album.class);
                Log.i("", album.toString());*/
                Albums albums = gson.fromJson(json, Albums.class);
                Log.i("", albums.toString());
            }
        });
    }
}
