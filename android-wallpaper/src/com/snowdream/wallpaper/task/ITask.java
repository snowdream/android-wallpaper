
package com.snowdream.wallpaper.task;

import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;

/**
 * @author snowdream
 */
public interface ITask extends Runnable {
    public static final int TASK_TYPE_ALBUM = 0;

    public static final int TASK_TYPE_ALBUMS = 1;

    public void queryImagesFromAlbum(Album album)
            throws Exception;

    public void queryImagesFromAlbums(Albums albums)
            throws Exception;
}
