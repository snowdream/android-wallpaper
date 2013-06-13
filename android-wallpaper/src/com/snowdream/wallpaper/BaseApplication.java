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

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.github.snowdream.android.util.Log;
import com.google.analytics.tracking.android.EasyTracker;
import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.snowdream.wallpaper.Constants.Config;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public class BaseApplication extends Application {
    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        if (Config.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll()
                    .penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath()
                    .build());
        }

        super.onCreate();

        initImageLoader(getApplicationContext());
        initGoogleAnalytics(getApplicationContext());
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/wallpaper";
        File cacheDir = StorageUtils.getIndividualCacheDirectory(context);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCache(new WeakMemoryCache()).memoryCacheSize(3 * 1024 * 1024)
                .memoryCacheExtraOptions(480, 800).threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .discCache(new LimitedAgeDiscCache(cacheDir, new FileNameGenerator() {

                    @Override
                    public String generate(String url) {
                        if (url == null || url == "") {
                            Log.w("url is null!");
                            return "";
                        }
                        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
                        return fileName;
                    }
                }, 2 * 24 * 60 * 60)).threadPoolSize(1)
                .tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging().build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    public void initGoogleAnalytics(Context context) {
        // Set Context before using EasyTracker. Note that the SDK will
        // use the application context.
        EasyTracker.getInstance().setContext(this);

        // EasyTracker is now ready for use.
    }
}
