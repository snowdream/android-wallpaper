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

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.github.snowdream.android.util.Log;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.snowdream.wallpaper.ImagePagerActivity;
import com.snowdream.wallpaper.R;
import com.snowdream.wallpaper.entity.Image;
import uk.co.senab.photoview.PhotoView;

import java.util.List;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @version v1.0
 * @date 2013-6-10
 */
public class ImagePagerAdapter extends PagerAdapter {
    private static final String TEST_DEVICE_ID = "INSERT_YOUR_TEST_DEVICE_ID_HERE";

    private List<Image> images;

    private DisplayImageOptions options = null;

    private Context mContext = null;

    public ImagePagerAdapter(Context context, List<Image> images, DisplayImageOptions options) {
        this.mContext = context;
        this.images = images;
        this.options = options;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object != null && object instanceof PhotoView) {
            PhotoView photoView = (PhotoView) object;
            ImageLoader.getInstance().cancelDisplayTask(photoView);
        }

        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public void finishUpdate(View container) {
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public List<Image> getImages() {
        return images;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);

        // The "loadAdOnCreate" and "testDevices" XML attributes no longer available.
        AdView adView = (AdView) imageLayout.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)
                .build();
        adView.loadAd(adRequest);

        PhotoView photoView = (PhotoView) imageLayout.findViewById(R.id.image);
        photoView.setScaleType(ScaleType.FIT_CENTER);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext == null) {
                    return;
                }

                Activity activity = (Activity) mContext;

                if (!(activity instanceof ImagePagerActivity)) {
                    return;
                }

                ((ImagePagerActivity) activity).onImageClick(v);

            }
        });

        final RelativeLayout spinner = (RelativeLayout) imageLayout.findViewById(R.id.rl_loading);
        final String imgUrl = images.get(position).getUrl();

        ImageLoader.getInstance().displayImage(imgUrl, photoView, options,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        spinner.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        String message = null;
                        switch (failReason.getType()) {
                            case IO_ERROR:
                                message = "Input/Output error";
                                break;
                            case DECODING_ERROR:
                                message = "Image can't be decoded";
                                break;
                            case NETWORK_DENIED:
                                message = "Downloads are denied";
                                break;
                            case OUT_OF_MEMORY:
                                message = "Out Of Memory error";
                                break;
                            case UNKNOWN:
                                message = "Unknown error";
                                break;
                        }
                        // Toast.makeText(ImagePagerActivity.this, message,
                        // Toast.LENGTH_SHORT).show();
                        Log.e("onLoadingFailed" + message);

                        spinner.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        spinner.setVisibility(View.GONE);
                    }
                });

        ((ViewPager) view).addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View container) {
    }
}
