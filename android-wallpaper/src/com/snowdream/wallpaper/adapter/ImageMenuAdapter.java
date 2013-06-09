/**
 * 
 */

package com.snowdream.wallpaper.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.snowdream.android.util.Log;
import com.snowdream.wallpaper.entity.Album;

/**
 * @author snowdream
 */
public class ImageMenuAdapter extends BaseAdapter {
	private List<Album> list = null;
	private LayoutInflater mInflater = null;

	public ImageMenuAdapter(LayoutInflater inflater, List<Album> list) {
		if (list != null && list.size() > 0) {
			this.list = list;
		}

		this.mInflater = inflater;
	}

	public List<Album> getList() {
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
	public Album getItem(int position) {
		Album album = null;

		if (list != null && position < list.size()) {
			album = list.get(position);
		}

		return album;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Album album = getItem(position);

		if (album == null) {
			Log.w("The Album is null!");
			return null;
		}

		final TextView textView;
		if (convertView == null) {
			textView = (TextView) mInflater.inflate(
					android.R.layout.simple_list_item_1, parent, false);
		} else {
			textView = (TextView) convertView;
		}

		textView.setText(album.getName());
		textView.setTag(album);

		return textView;
	}
}
