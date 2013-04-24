
package com.snowdream.wallpaper.net;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsyncHttpClient {
    private static final String BASE_URL = "http://gallerycms.ap01.aws.af.cm/index.php/api/feed/json/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        String url = (relativeUrl.contains(BASE_URL))  ? relativeUrl : (BASE_URL + relativeUrl);

        return url;
    }
}
