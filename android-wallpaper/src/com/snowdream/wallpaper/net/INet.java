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

import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public interface INet {
    public static final String URL_ALBUM = "http://192.168.1.100:8080/gallerycms/index.php/api/feed/json/";

    public static final String URL_ALBUMS = "http://192.168.1.100:8080/gallerycms/index.php/api/myfeed/json/";

    public Albums getAlbumsFromNet(String url) throws Exception;

    public Album getAlbumFromNet(String url) throws Exception;
}
