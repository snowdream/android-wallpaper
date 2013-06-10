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

package com.snowdream.wallpaper.sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
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
    public void addAlbums(Albums albums) throws SQLException {
        if (albums == null) {
            return;
        }

        Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

        albumsDao.createOrUpdate(albums);

        Collection<Album> tAlbums = albums.getAlbums();

        for (Album album : tAlbums) {
            album.setAlbums(albums);
            addAlbum(album);
        }
    }

    @Override
    public void updateAlbums(Albums albums) throws SQLException {
        if (albums == null) {
            return;
        }

        Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

        albumsDao.update(albums);
    }

    @Override
    public void deleteAlbums(Albums albums) throws SQLException {
        if (albums == null) {
            return;
        }

        Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

        albumsDao.delete(albums);
    }

    @Override
    public Albums queryAlbums(Albums albums) throws SQLException {
        Albums talbums = null;

        if (albums == null) {
            return talbums;
        }

        Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

        talbums = albumsDao.queryForSameId(albums);

        return talbums;
    }

    @Override
    public void addImage(Image image) throws SQLException {
        if (image == null) {
            return;
        }

        Dao<Image, String> imageDao = getHelper().getImageDao();

        imageDao.createOrUpdate(image);
    }

    @Override
    public void updateImage(Image image) throws SQLException {
        if (image == null) {
            return;
        }

        Dao<Image, String> imageDao = getHelper().getImageDao();

        imageDao.update(image);
    }

    @Override
    public Image queryImage(Image image) throws SQLException {
        Image timage = null;

        if (image == null) {
            return timage;
        }

        Dao<Image, String> imageDao = getHelper().getImageDao();

        timage = imageDao.queryForSameId(image);

        return timage;
    }

    @Override
    public void deleteImage(Image image) throws SQLException {
        if (image == null) {
            return;
        }

        Dao<Image, String> imageDao = getHelper().getImageDao();

        imageDao.delete(image);
    }

    @Override
    public void addAlbum(Album album) throws SQLException {
        if (album == null) {
            return;
        }

        Dao<Album, String> albumDao = getHelper().getAlbumDao();

        albumDao.createOrUpdate(album);

        Collection<Image> tImage = album.getImages();

        for (Image image : tImage) {
            image.setAlbum(album);
            addImage(image);
        }
    }

    @Override
    public void updateAlbum(Album album) throws SQLException {
        if (album == null) {
            return;
        }

        Dao<Album, String> albumDao = getHelper().getAlbumDao();

        albumDao.update(album);
    }

    @Override
    public Album queryAlbum(Album album) throws SQLException {
        Album talbum = null;

        if (album == null) {
            return talbum;
        }

        Dao<Album, String> albumDao = getHelper().getAlbumDao();

        talbum = albumDao.queryForSameId(album);

        return talbum;
    }

    @Override
    public void deleteAlbum(Album album) throws SQLException {
        if (album == null) {
            return;
        }

        Dao<Album, String> albumDao = getHelper().getAlbumDao();

        albumDao.delete(album);
    }

    @Override
    public List<Image> queryImagesFromAlbum(Album album) throws SQLException {
        List<Image> list = null;
        Album talbum = null;

        if (album == null) {
            return list;
        }

        Dao<Album, String> albumDao = getHelper().getAlbumDao();

        talbum = albumDao.queryForSameId(album);

        if (talbum == null) {
            return list;
        }

        list = new ArrayList<Image>();

        for (Image image : talbum.getImages()) {
            list.add(image);
        }

        return list;
    }

    @Override
    public List<Image> queryImagesFromAlbums(Albums albums) throws SQLException {
        List<Image> list = null;
        Albums talbums = null;

        if (albums == null) {
            return list;
        }

        Dao<Albums, String> albumsDao = getHelper().getAlbumsDao();

        talbums = albumsDao.queryForSameId(albums);

        if (talbums == null) {
            return list;
        }

        list = new ArrayList<Image>();

        for (Album album : talbums.getAlbums()) {
            List<Image> alist = queryImagesFromAlbum(album);

            if (alist != null) {
                list.addAll(alist);
            }
        }

        return list;
    }
}
