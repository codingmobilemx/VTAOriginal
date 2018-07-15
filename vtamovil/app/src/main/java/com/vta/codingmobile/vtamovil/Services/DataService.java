package com.vta.codingmobile.vtamovil.Services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.vta.codingmobile.vtamovil.DTO.Responses.ProductsResponseDTO;
import com.vta.codingmobile.vtamovil.Helpers.Constants;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.ErrorManager;

import cz.msebera.android.httpclient.Header;

public class DataService {
    private static final String TAG = DataService.class.getSimpleName();

    public static void getProducts() {
        try {
            ApiServices.get(Constants.URI_BASE + Constants.GETALL_PRODUCTS, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    super.onSuccess(statusCode, headers, response);
                    Type type = new TypeToken<ProductsResponseDTO>() {
                    }.getType();
                    ProductsResponseDTO productsResponseDTO = new Gson().fromJson(response.toString(), type);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });

        } catch (Exception ex) {
            Log.e(TAG, "Error", ex);
        }
    }
}
