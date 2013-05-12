
package com.snowdream.wallpaper;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.github.snowdream.android.util.Log;
import com.loopj.android.image.SmartImageView;
import com.snowdream.wallpaper.adapter.ImageAdapter;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;
import com.snowdream.wallpaper.entity.Object;
import com.snowdream.wallpaper.task.ITaskImpl;
import com.snowdream.wallpaper.task.ITaskListener;

public class MainActivity extends Activity {
    private GridView gridView = null;

    private ImageAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.setTag("Wallpaper");
        Log.setEnabled(true);
        test1();
        // test2();
        initUI();
    }

    private void initUI() {
        gridView = (GridView) findViewById(R.id.gridview);
    }

    public void test1() {

        Albums albums = new Albums();
        albums.setId("1");
        albums.setUuid("c75dcaac-bb0d-11e2-8ef1-047d7b4d0279");
        albums.setName("影视");

        ITaskImpl iTaskImpl = new ITaskImpl(this, albums, new ITaskListener() {

            @Override
            public void onSuccess(List<Image> images) {
                Log.i("onSuccess" + images.toString());
                adapter = new ImageAdapter(MainActivity.this, images);
                gridView.setAdapter(adapter);
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
                adapter = new ImageAdapter(MainActivity.this, images);
                gridView.setAdapter(adapter);
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

    public void initData() {
        List<Object> list = new ArrayList<Object>();

        // 全部
        Albums albums = new Albums();
        albums.setId("1");
        albums.setUuid("3720a0db-a6ab-11e2-8058-026bc5020299");
        albums.setName("All");

        list.add(albums);

        // 美女
        Album beauty = new Album();
        beauty.setId("3");
        beauty.setUuid("c835b2f8-a690-11e2-8058-026bc5020299");
        beauty.setName("beauty");

        list.add(beauty);

        // 动漫
        Album anime = new Album();
        anime.setId("4");
        anime.setUuid("0db6b625-a692-11e2-8058-026bc5020299");
        anime.setName("anime");

        list.add(anime);

        // 游戏
        Album games = new Album();
        games.setId("6");
        games.setUuid("e5c17639-a694-11e2-8058-026bc5020299");
        games.setName("games");

        list.add(games);
    }

    public void OnImageClick(View v) {
        Image image = (Image) v.getTag();

        if (image == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setClass(this, ImageActivity.class);
        intent.putExtra("url", image.getUrl());
        startActivity(intent);

    }
}
