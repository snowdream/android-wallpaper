/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
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
package com.snowdream.wallpaper;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.github.snowdream.android.util.Log;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.snowdream.wallpaper.Constants.Extra;
import com.snowdream.wallpaper.adapter.ImageGridAdapter;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;
import com.snowdream.wallpaper.task.ITaskImpl;
import com.snowdream.wallpaper.task.ITaskListener;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImageGridActivity extends AbsListViewBaseActivity {

	String[] imageUrls;

	DisplayImageOptions options;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_grid);
		
	    Log.setTag("Wallpaper");
        Log.setEnabled(true);
        test1();

		options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_stub)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.cacheInMemory()
			.cacheOnDisc()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();

/*		listView = (GridView) findViewById(R.id.gridview);
		((GridView) listView).setAdapter(new ImageAdapter());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});*/
	}

	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ImageView imageView;
			if (convertView == null) {
				imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
			} else {
				imageView = (ImageView) convertView;
			}

			imageLoader.displayImage(imageUrls[position], imageView, options);

			return imageView;
		}
	}
	

    public void test1() {

        Albums albums = new Albums();
        albums.setId("1");
        albums.setUuid("c75dcaac-bb0d-11e2-8ef1-047d7b4d0279");
        albums.setName("影视");

        ITaskImpl iTaskImpl = new ITaskImpl(this, albums, new ITaskListener() {

            @Override
            public void onSuccess(List<Image> images) {
                Log.i("onSuccess" + images.toString());
                listView = (GridView) findViewById(R.id.gridview);
                ((GridView) listView).setAdapter(new ImageGridAdapter(ImageGridActivity.this, images,options));
                listView.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startImagePagerActivity(position);
                    }
                });
            }

            @Override
            public void onStart() {
                Log.i("onStart");
            }

            @Override
            public void onFinish() {
                Log.i("onFinish");
            }

            @Override
            public void onFailed(Exception e) {
                Log.e("onFailed :" + e.toString());
            }
        });

        new Thread(iTaskImpl).start();

    }
}