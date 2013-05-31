
package com.snowdream.wallpaper.net;

import com.loopj.android.http.RequestParams;

public class SyncHttpClient {
    public static final CustomHttpClient client = new CustomHttpClient();

    private static class CustomHttpClient extends com.loopj.android.http.SyncHttpClient {
        @Override
        public String onRequestFailed(Throwable arg0, String arg1) {
            return null;
        }

    }

    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, RequestParams params) {
        return client.get(url, params);
    }

    public static String post(String url) {
        return post(url, null);
    }

    public static String post(String url, RequestParams params) {
        return client.post(url, params);
    }
}
