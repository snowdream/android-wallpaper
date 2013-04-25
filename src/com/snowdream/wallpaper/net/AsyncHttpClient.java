
package com.snowdream.wallpaper.net;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsyncHttpClient {
    private static com.loopj.android.http.AsyncHttpClient client = new com.loopj.android.http.AsyncHttpClient();

    public static void get(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }
}
