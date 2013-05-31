package com.snowdream.wallpaper.task;

import java.util.List;

import com.snowdream.wallpaper.entity.Image;

/**
 * @author snowdream
 *
 */
public interface ITaskListener {
    public void onStart();
    public void onSuccess(List<Image> images);
    public void onFailed(Exception e);
    public void onFinish();
}
