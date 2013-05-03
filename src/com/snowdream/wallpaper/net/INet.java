package com.snowdream.wallpaper.net;

import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;

/**
 * @author snowdream
 */
public interface INet {

    public Albums getAlbumsFromNet(String url);
    
    public Album getAlbumFromNet(String url);
}
