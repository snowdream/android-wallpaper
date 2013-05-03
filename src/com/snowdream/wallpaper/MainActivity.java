
package com.snowdream.wallpaper;

import com.github.snowdream.android.util.Log;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.net.INet;
import com.snowdream.wallpaper.net.INetImpl;
import com.snowdream.wallpaper.sql.ISql;
import com.snowdream.wallpaper.sql.ISqlImpl;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.setTag("Wallpaper");
        Log.setEnabled(true);
        test();
    }

    public void test() {
        new Thread() {
            public void run() {
                INet inet = new INetImpl();
                Albums albums = inet.getAlbumsFromNet("http://gallerycms.ap01.aws.af.cm/index.php/api/myfeed/json/3720a0db-a6ab-11e2-8058-026bc5020299");
                
                ISql iSql = new ISqlImpl(MainActivity.this);
                iSql.addAlbums(albums);
            };

        }.start();
    }
}
