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
import com.snowdream.find.sexygirls.R;
import com.snowdream.wallpaper.entity.Image;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public class ImageGridAdapter extends BaseAdapter {
    private List<Image> list = null;

    private Context mContext = null;

    private DisplayImageOptions options = null;

    public ImageGridAdapter() {
    }

    public ImageGridAdapter(Context context, List<Image> list, DisplayImageOptions options) {
        if (list != null && list.size() > 0) {
            this.list = list;
        }

        this.mContext = context.getApplicationContext();
        this.options = options;
    }

    public List<Image> getList() {
        return list;
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

        ImageLoader.getInstance().displayImage(image.getThumb(), imageView, options);
        imageView.setTag(image);

        return imageView;
    }

}
