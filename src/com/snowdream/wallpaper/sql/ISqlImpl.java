
package com.snowdream.wallpaper.sql;

import java.sql.SQLException;
import java.util.Collection;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;

/**
 * @author snowdream
 */
public class ISqlImpl implements ISql {
    private DatabaseHelper databaseHelper = null;

    private Context mContext = null;

    public ISqlImpl(Context context) {
        mContext = context;
    }

    /*
     * You'll need this in your class to release the helper when done.
     */
    public void Release() {

        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    /**
     * You'll need this in your class to get the helper from the manager once
     * per class.
     */
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void addAlbums(Albums albums) {
        if (albums == null) {
            return;
        }

        try {
            Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

            albumsDao.createOrUpdate(albums);

            Collection<Album> tAlbums = albums.getAlbums();

            for (Album album : tAlbums) {
                album.setAlbums(albums);
                addAlbum(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAlbums(Albums albums) {
        if (albums == null) {
            return;
        }

        try {
            Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

            albumsDao.update(albums);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAlbums(Albums albums) {
        if (albums == null) {
            return;
        }

        try {
            Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

            albumsDao.delete(albums);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Albums queryAlbums(Albums albums) {
        Albums talbums = null;

        try {
            Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

            talbums = albumsDao.queryForSameId(albums);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return talbums;
    }

    @Override
    public void addImage(Image image) {
        if (image == null) {
            return;
        }

        try {
            Dao<Image, String> imageDao = getHelper().getImageDao();

            imageDao.createOrUpdate(image);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateImage(Image image) {
        if (image == null) {
            return;
        }

        try {
            Dao<Image, String> imageDao = getHelper().getImageDao();

            imageDao.update(image);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image queryImage(Image image) {
        Image timage = null;

        if (image == null) {
            return timage;
        }

        try {
            Dao<Image, String> imageDao = getHelper().getImageDao();

            timage = imageDao.queryForSameId(image);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return timage;
    }

    @Override
    public void deleteImage(Image image) {
        if (image == null) {
            return;
        }

        try {
            Dao<Image, String> imageDao = getHelper().getImageDao();

            imageDao.delete(image);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAlbum(Album album) {
        if (album == null) {
            return;
        }

        try {
            Dao<Album, String> albumDao = getHelper().getAlbumDao();

            albumDao.createOrUpdate(album);

            Collection<Image> tImage = album.getImages();

            for (Image image : tImage) {
                image.setAlbum(album);
                addImage(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAlbum(Album album) {
        if (album == null) {
            return;
        }

        try {
            Dao<Album, String> albumDao = getHelper().getAlbumDao();

            albumDao.update(album);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Album queryAlbum(Album album) {
        Album talbum = null;

        try {
            Dao<Album, String> albumDao = getHelper().getAlbumDao();

            talbum = albumDao.queryForSameId(album);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return talbum;
    }

    @Override
    public void deleteAlbum(Album album) {
        if (album == null) {
            return;
        }

        try {
            Dao<Album, String> albumDao = getHelper().getAlbumDao();

            albumDao.delete(album);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
