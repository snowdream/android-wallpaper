/**
 * 
 */

package com.snowdream.wallpaper.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.github.snowdream.android.util.Log;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.snowdream.wallpaper.R;
import com.snowdream.wallpaper.entity.Image;

/**
 * @author snowdream
 */
public class ImageGridAdapter extends BaseAdapter {
    private List<Image> list = null;

    private Context mContext = null;
    
    private DisplayImageOptions options = null;

    public ImageGridAdapter(Context context, List<Image> list,DisplayImageOptions options) {
        if (list != null && list.size() > 0) {
            this.list = list;
        }

        this.mContext = context;
        this.options = options;
    }

    @Override
    public int getCount() {
        int size = 0;

        if (list != null) {
            size = list.size();
        }

        return size;
    }

    @Override
    public Image getItem(int position) {
        Image image = null;

        if (list != null && position < list.size()) {
            image = list.get(position);
        }

        return image;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Image image = getItem(position);

        if (image == null) {
            Log.w("The Image is null!");
            return null;
        }

        final ImageView imageView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            imageView = (ImageView) inflater.inflate(R.layout.item_grid_image, parent, false);
        } else {
            imageView = (ImageView) convertView;
        }

        ImageLoader.getInstance().displayImage(image.getUrl(), imageView, options);

        return imageView;
    }


}
