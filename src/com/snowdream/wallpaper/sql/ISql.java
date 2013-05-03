
package com.snowdream.wallpaper.sql;

import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;

/**
 * @author snowdream
 */
public interface ISql {
    public void addImage(Image image);

    public void updateImage(Image image);

    public Image queryImage(Image image);

    public void deleteImage(Image image);

    public void addAlbum(Album album);

    public void updateAlbum(Album album);

    public Album queryAlbum(Album album);

    public void deleteAlbum(Album album);

    public void addAlbums(Albums albums);

    public void updateAlbums(Albums albums);

    public Albums queryAlbums(Albums albums);

    public void deleteAlbums(Albums albums);
}
