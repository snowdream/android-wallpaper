
package com.snowdream.wallpaper.sql;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.snowdream.android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.snowdream.wallpaper.R;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something
    // appropriate for your app
    private static final String DATABASE_NAME = "snowdream-wallpaper.db";

    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the table
    private Dao<Albums, String> albumsDao = null;

    private Dao<Album, String> albumDao = null;
    
    private Dao<Image, String> imageDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,R.raw.ormlite_config);
    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create the tables that will store
     * your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i( "onCreate");
            TableUtils.createTable(connectionSource, Album.class);
            TableUtils.createTable(connectionSource, Albums.class);
            TableUtils.createTable(connectionSource, Image.class);
        } catch (SQLException e) {
            Log.e("Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher
     * version number. This allows you to adjust the various data to match the
     * new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
            int newVersion) {
        try {
            Log.i("onUpgrade");
            TableUtils.dropTable(connectionSource, Album.class, true);
            TableUtils.dropTable(connectionSource, Albums.class, true);
            TableUtils.dropTable(connectionSource, Image.class, true);

            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e("Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our Albums class. It
     * will create it or just give the cached value.
     */
    public Dao<Albums, String> getAlbumsDao() throws SQLException {
        if (albumsDao == null) {
            albumsDao = getDao(Albums.class);
        }
        return albumsDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our Album class. It
     * will create it or just give the cached value.
     */
    public Dao<Album, String> getAlbumDao() throws SQLException {
        if (albumDao == null) {
            albumDao = getDao(Album.class);
        }
        return albumDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our Image class. It
     * will create it or just give the cached value.
     */
    public Dao<Image, String> getImageDao() throws SQLException {
        if (imageDao == null) {
            imageDao = getDao(Image.class);
        }
        return imageDao;
    }
    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        albumsDao = null;
        albumDao = null;
        imageDao = null;
    }
}
