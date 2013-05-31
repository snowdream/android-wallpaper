package com.snowdream.wallpaper.net;

import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;

/**
 * @author snowdream
 */
public interface INet {
    public static final String URL_ALBUM = "http://gcms2.gallerycms.com/index.php/api/feed/json/";
    public static final String URL_ALBUMS = "http://gcms2.gallerycms.com/index.php/api/myfeed/json/";

    public Albums getAlbumsFromNet(String url) throws Exception;
    
    public Album getAlbumFromNet(String url) throws Exception;
}
