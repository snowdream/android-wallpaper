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
import com.loopj.android.image.SmartImageView;
import com.snowdream.wallpaper.R;
import com.snowdream.wallpaper.entity.Image;

/**
 * @author snowdream
 */
public class BrowseAdapter extends BaseAdapter {
    private List<Image> list = null;

    private Context mContext = null;

    public BrowseAdapter(Context context, List<Image> list) {
        if (list != null && list.size() > 0) {
            this.list = list;
        }

        mContext = context;
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

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main, null);
            
            holder = new ViewHolder();
            holder.imageview = (SmartImageView) convertView.findViewById(R.id.my_image);
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageview.setAdjustViewBounds(true);
        holder.imageview.setImageUrl(image.getUrl());
        holder.imageview.setTag(image);

        return convertView;
    }

    static class ViewHolder {
        SmartImageView imageview;
    }

}
