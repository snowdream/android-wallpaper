/**
 * 
 */

package com.snowdream.wallpaper.net;

import android.text.TextUtils;

import com.github.snowdream.android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;

/**
 * @author snowdream
 */
public class INetImpl implements INet {

    @Override
    public Albums getAlbumsFromNet(String url) throws Exception {
        Albums albums = null;

        String json = SyncHttpClient.get(url);

        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            albums = gson.fromJson(json, Albums.class);

            Log.i(json);
        }else{
            throw new NullPointerException("json for the albums is empty");
        }

        return albums;
    }

    @Override
    public Album getAlbumFromNet(String url) throws JsonSyntaxException {
        Album album = null;

        String json = SyncHttpClient.get(url);

        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            album = gson.fromJson(json, Album.class);

            Log.i(json);
        }else{
            throw new NullPointerException("json for the album is empty");
        }

        return album;
    }

}
