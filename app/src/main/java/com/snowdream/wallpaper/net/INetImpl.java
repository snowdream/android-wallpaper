/*******************************************************************************
 * Copyright (C) 2013 Snowdream Mobile
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.snowdream.wallpaper.net;

import android.text.TextUtils;

import com.github.snowdream.android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
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
        } else {
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
        } else {
            throw new NullPointerException("json for the album is empty");
        }

        return album;
    }

}
