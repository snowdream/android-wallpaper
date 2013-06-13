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

package com.snowdream.wallpaper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;
import com.google.analytics.tracking.android.EasyTracker;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.snowdream.wallpaper.Constants.Extra;
import com.snowdream.wallpaper.adapter.ImagePagerAdapter;
import com.snowdream.wallpaper.entity.Image;

import eu.janmuller.android.simplecropimage.CropImage;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public class ImagePagerActivity extends SherlockActivity {

    private static final String STATE_POSITION = "STATE_POSITION";

    public static final int REQUEST_CODE_GALLERY = 0x1;

    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;

    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;

    public static final int MENU_SAVE = 0;

    public static final int MENU_SET = 1;

    DisplayImageOptions options;

    ViewPager pager;

    private Handler mHandler;

    ShareActionProvider actionProvider = null;

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

        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error).resetViewBeforeLoading().cacheOnDisc()
                .imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565)
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
        pager.setOnPageChangeListener(new OnPageChangeListener() {
            
            @Override
            public void onPageSelected(int arg0) {
                if (actionProvider != null) {
                    actionProvider.setShareIntent(createShareIntent());
                }
            }
            
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                
            }
            
            @Override
            public void onPageScrollStateChanged(int arg0) {
                
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate your menu.
        getSupportMenuInflater().inflate(R.menu.share_action_provider, menu);

        // Set file with share history to the provider and set the share intent.
        MenuItem actionItem = menu.findItem(R.id.menu_item_share_action_provider_action_bar);
        actionProvider = (ShareActionProvider) actionItem.getActionProvider();
        actionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        // Note that you can set/change the intent any time,
        // say when the user has selected an image.
        if (actionProvider != null) {
            actionProvider.setShareIntent(createShareIntent());
        }
        // menu.add(0, MENU_SAVE, 0, "Save").setShowAsAction(
        // MenuItem.SHOW_AS_ACTION_NEVER | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add(0, MENU_SET, 0, getString(R.string.menu_setaswallpaper)).setShowAsAction(
                MenuItem.SHOW_AS_ACTION_NEVER | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        getSupportActionBar().show();
        //hideActionBarDelayed(mHandler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this);
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
                break;
            case MENU_SAVE:
                break;
            case MENU_SET:
                setWallPaper();
                break;
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
        }, 30000);
    }

    private Intent createShareIntent() {
        if (pager == null || pager.getAdapter() == null
                || !(pager.getAdapter() instanceof ImagePagerAdapter)) {
            return null;
        }

        ImagePagerAdapter adapter = (ImagePagerAdapter) pager.getAdapter();
        List<Image> images = adapter.getImages();

        if (images == null || images.size() <= 0) {
            return null;
        }

        int pos = pager.getCurrentItem();

        if (pos >= images.size()) {
            return null;
        }

        Image image = images.get(pos);

        if (image == null) {
            return null;
        }

        String imageUrl = image.getUrl();

        if (imageUrl == null || imageUrl == "") {
            return null;
        }

        File file = ImageLoader.getInstance().getDiscCache().get(imageUrl);

        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        Uri uri = Uri.fromFile(file);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        //shareIntent.putExtra(Intent.EXTRA_TEXT, "来自");

        return shareIntent;
    }

    private void setWallPaper() {
        if (pager == null || pager.getAdapter() == null
                || !(pager.getAdapter() instanceof ImagePagerAdapter)) {
            return;
        }

        ImagePagerAdapter adapter = (ImagePagerAdapter) pager.getAdapter();
        List<Image> images = adapter.getImages();

        if (images == null || images.size() <= 0) {
            return;
        }

        int pos = pager.getCurrentItem();

        if (pos >= images.size()) {
            return;
        }

        Image image = images.get(pos);

        if (image == null) {
            return;
        }

        String imageUrl = image.getUrl();

        if (imageUrl == null || imageUrl == "") {
            return;
        }

        File file = ImageLoader.getInstance().getDiscCache().get(imageUrl);

        if (file == null || !file.exists() || !file.canRead()) {
            return;
        }

        Intent intent = new Intent(this, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, file.getPath());
        intent.putExtra(CropImage.SCALE, true);

        intent.putExtra(CropImage.ASPECT_X, 480);
        intent.putExtra(CropImage.ASPECT_Y, 800);

        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        Bitmap bitmap = null;
        switch (requestCode) {
            case REQUEST_CODE_CROP_IMAGE:
                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {
                    return;
                }

                bitmap = BitmapFactory.decodeFile(path);

                if (bitmap != null && !bitmap.isRecycled()) {
                    try {
                        setWallpaper(bitmap);
                        Toast.makeText(this, "Set Successful", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Set Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
