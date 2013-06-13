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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.snowdream.android.util.Log;
import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Object;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public class ImageMenuAdapter extends BaseAdapter {
    private List<Object> list = null;

    private LayoutInflater mInflater = null;

    public ImageMenuAdapter(LayoutInflater inflater, List<Object> list) {
        if (list != null && list.size() > 0) {
            this.list = list;
        }

        this.mInflater = inflater;
    }

    public List<Object> getList() {
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
    public Object getItem(int position) {
    	Object object = null;

        if (list != null && position < list.size()) {
        	object = list.get(position);
        }

        return object;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	Object object = getItem(position);

        if (object == null) {
            Log.w("The Album is null!");
            return null;
        }

        final TextView textView;
        if (convertView == null) {
            textView = (TextView) mInflater.inflate(android.R.layout.simple_list_item_1, parent,
                    false);
        } else {
            textView = (TextView) convertView;
        }
        
        if (object instanceof Album) {
        	Album album = (Album)object;
            textView.setText(album.getName());
		}else if (object instanceof Albums) {
        	Albums albums = (Albums)object;
            textView.setText(albums.getName());
		}else{
            Log.w("Invalid Data!");
            return null;
		}

        textView.setTag(object);

        return textView;
    }
}
