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

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.snowdream.wallpaper.Constants.Extra;
import com.snowdream.wallpaper.adapter.ImagePagerAdapter;
import com.snowdream.wallpaper.entity.Image;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImagePagerActivity extends SherlockActivity {

	private static final String STATE_POSITION = "STATE_POSITION";

	DisplayImageOptions options;

	ViewPager pager;

	private Handler mHandler;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initUI();
		initData(savedInstanceState);
	}

	private void initData(Bundle savedInstanceState) {
		Bundle bundle = getIntent().getExtras();
		List<Image> images = bundle.getParcelableArrayList(Extra.IMAGES);
		int pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);

		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).resetViewBeforeLoading()
				.cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		mHandler = new Handler();

		pager.setAdapter(new ImagePagerAdapter(this, images, options));
		pager.setCurrentItem(pagerPosition);
	}

	private void initUI() {

		setTitle(R.string.app_name);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		ColorDrawable color = new ColorDrawable(Color.BLACK);
		color.setAlpha(128);
		getSupportActionBar().setBackgroundDrawable(color);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		this.getWindow().setBackgroundDrawableResource(android.R.color.black);
		setContentView(R.layout.activity_image_pager);

		pager = (ViewPager) findViewById(R.id.pager);

	}

	@Override
	public void onResume() {
		super.onResume();
		getSupportActionBar().show();
		hideActionBarDelayed(mHandler);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onImageClick(View view) {
		if (getSupportActionBar().isShowing()) {
			getSupportActionBar().hide();
		} else {
			getSupportActionBar().show();
			hideActionBarDelayed(mHandler);
		}
	}

	private void hideActionBarDelayed(Handler handler) {
		handler.postDelayed(new Runnable() {
			public void run() {
				getSupportActionBar().hide();
			}
		}, 10000);
	}
}