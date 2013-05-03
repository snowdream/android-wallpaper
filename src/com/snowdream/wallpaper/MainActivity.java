
package com.snowdream.wallpaper;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.github.snowdream.android.util.Log;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;
import com.snowdream.wallpaper.task.ITaskImpl;
import com.snowdream.wallpaper.task.ITaskListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.setTag("Wallpaper");
        Log.setEnabled(true);
        //test1();
        test2();
    }

    public void test1() {
        
        Albums albums = new Albums();
        albums.setId("1");
        albums.setUuid("3720a0db-a6ab-11e2-8058-026bc5020299");
        albums.setName("全部");

        ITaskImpl iTaskImpl = new ITaskImpl(this, albums, new ITaskListener() {
            
            @Override
            public void onSuccess(List<Image> images) {
                Log.i("onSuccess" + images.toString());
            }
            
            @Override
            public void onStart() {
                Log.i("onStart");
            }
            
            @Override
            public void onFinish() {
                Log.i("onFinish");
            }
            
            @Override
            public void onFailed(Exception e) {
                Log.e("onFailed :" + e.toString());
            }
        });
        
        new Thread(iTaskImpl).start();
        
    }
    
  public void test2() {
        
        Album album = new Album();
        album.setId("9");
        album.setUuid("1bf862e3-a6a7-11e2-8058-026bc5020299");

        ITaskImpl iTaskImpl = new ITaskImpl(this, album, new ITaskListener() {
            
            @Override
            public void onSuccess(List<Image> images) {
                Log.i("onSuccess" + images.toString());
            }
            
            @Override
            public void onStart() {
                Log.i("onStart");
            }
            
            @Override
            public void onFinish() {
                Log.i("onFinish");
            }
            
            @Override
            public void onFailed(Exception e) {
                Log.e("onFailed :" + e.toString());
            }
        });
        
        new Thread(iTaskImpl).start();
        
    }
}
