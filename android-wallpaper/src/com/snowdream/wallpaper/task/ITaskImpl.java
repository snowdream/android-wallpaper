/**
 * 
 */

package com.snowdream.wallpaper.task;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;
import com.snowdream.wallpaper.entity.Object;
import com.snowdream.wallpaper.net.INet;
import com.snowdream.wallpaper.net.INetImpl;
import com.snowdream.wallpaper.sql.ISql;
import com.snowdream.wallpaper.sql.ISqlImpl;

/**
 * @author snowdream
 */
public class ITaskImpl implements ITask {
    private int type = ITask.TASK_TYPE_ALBUM;

    private Albums albums = null;

    private Album album = null;

    private ITaskListener iTaskListener = null;

    private Context mContext = null;

    public ITaskImpl(Context context, Object obj, ITaskListener iTaskListener) {
        if (obj != null) {
            if (obj instanceof Albums) {
                type = ITask.TASK_TYPE_ALBUMS;
                albums = (Albums) obj;
            } else if (obj instanceof Album) {
                type = ITask.TASK_TYPE_ALBUM;
                album = (Album) obj;
            }
        }

        this.iTaskListener = iTaskListener;
        this.mContext = context;
    }

    @Override
    public void run() {
        // onstart
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (iTaskListener != null) {
                    iTaskListener.onStart();
                }
            }
        });

        try {
            switch (type) {
                case ITask.TASK_TYPE_ALBUM:
                    queryImagesFromAlbum(album);
                    break;
                case ITask.TASK_TYPE_ALBUMS:
                    queryImagesFromAlbums(albums);
                    break;
                default:
                    break;
            }
        } catch (final Exception e) {
            e.printStackTrace();

            // onFinish
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (iTaskListener != null) {
                        iTaskListener.onFailed(e);
                    }
                }
            });
        } finally {
            // onFinish
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (iTaskListener != null) {
                        iTaskListener.onFinish();
                    }
                }
            });
        }
    }

    private void runOnUiThread(Runnable runnable) {
        if (mContext != null && iTaskListener != null) {
            ((Activity) mContext).runOnUiThread(runnable);
        }
    }

    @Override
    public void queryImagesFromAlbum(Album album) throws Exception {
        INet inet = new INetImpl();
        ISql iSql = new ISqlImpl(mContext);

        if (album == null || TextUtils.isEmpty(album.getId())) {
            return;
        }

        final List<Image> list = iSql.queryImagesFromAlbum(album);
        
        if(list != null){
            // onSuccess
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (iTaskListener != null) {
                        iTaskListener.onSuccess(list);
                    }
                }
            });
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(INet.URL_ALBUM);
        buffer.append(album.getUuid());
        String url = buffer.toString();

        Album talbum = inet.getAlbumFromNet(url);
        if (talbum != null) {
            iSql.addAlbum(talbum);
            
            final List<Image> tlist = iSql.queryImagesFromAlbum(album);
            
            if(tlist != null){
                // onSuccess
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (iTaskListener != null) {
                            iTaskListener.onSuccess(tlist);
                        }
                    }
                });
            }
        }

    }

    @Override
    public void queryImagesFromAlbums(Albums albums) throws Exception {
        INet inet = new INetImpl();
        ISql iSql = new ISqlImpl(mContext);

        if (albums == null || TextUtils.isEmpty(albums.getId())) {
            return;
        }

        final List<Image> list = iSql.queryImagesFromAlbums(albums);
        
        if(list != null){
            // onSuccess
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (iTaskListener != null) {
                        iTaskListener.onSuccess(list);
                    }
                }
            });
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(INet.URL_ALBUMS);
        buffer.append(albums.getUuid());
        String url = buffer.toString();

        Albums talbums = inet.getAlbumsFromNet(url);
        if (talbums != null) {
            iSql.addAlbums(talbums);
            
            final List<Image> tlist = iSql.queryImagesFromAlbums(albums);
            
            if(tlist != null){
                // onSuccess
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (iTaskListener != null) {
                            iTaskListener.onSuccess(tlist);
                        }
                    }
                });
            }
        }

    }
}
