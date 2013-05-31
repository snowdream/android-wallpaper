
package com.snowdream.wallpaper.sql;

import java.sql.SQLException;
import java.util.List;

import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;

/**
 * @author snowdream
 */
public interface ISql {
    public void addImage(Image image) throws SQLException;

    public void updateImage(Image image)throws SQLException;

    public Image queryImage(Image image)throws SQLException;
    
    public List<Image> queryImagesFromAlbum(Album album)throws SQLException;

    public void deleteImage(Image image)throws SQLException;

    public void addAlbum(Album album)throws SQLException;

    public void updateAlbum(Album album)throws SQLException;

    public Album queryAlbum(Album album)throws SQLException;

    public void deleteAlbum(Album album)throws SQLException;

    public void addAlbums(Albums albums)throws SQLException;

    public void updateAlbums(Albums albums)throws SQLException;

    public Albums queryAlbums(Albums albums)throws SQLException;

    public void deleteAlbums(Albums albums)throws SQLException;
    
    public List<Image> queryImagesFromAlbums(Albums albums) throws SQLException;
}
