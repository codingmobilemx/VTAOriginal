package com.vta.codingmobile.vtamovil.Services;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.vta.codingmobile.vtamovil.Helpers.Constants;

import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;

public final class ApiServices {
    private static final AsyncHttpClient client = GetAsyncHttpClient();

    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }

    public static void post(String url, String jsonRequest, AsyncHttpResponseHandler responseHandler) {
        StringEntity entity = new StringEntity(jsonRequest, ContentType.APPLICATION_JSON);
        client.post(null, url, entity, "application/json", responseHandler);
    }

    private static AsyncHttpClient GetAsyncHttpClient() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/json");
        client.setConnectTimeout(Constants.TIMEOUT);
        client.setResponseTimeout(Constants.TIMEOUT);
        client.setTimeout(Constants.TIMEOUT);
        client.setMaxRetriesAndTimeout(1, 100);
        client.setMaxConnections(20);
      //  client.setLoggingEnabled(Boolean.FALSE);
        return client;
    }
}
