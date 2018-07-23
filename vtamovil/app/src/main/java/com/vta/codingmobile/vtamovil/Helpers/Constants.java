package com.vta.codingmobile.vtamovil.Helpers;

import android.net.http.SslCertificate;

public final class Constants {

    public static final Integer TIMEOUT = 60000;
    public static final String DB_NAME = "VTA";
    public static final Integer DB_VERSION = 1;

    public static final String URI_BASE = "http://portalsyltu.azurewebsites.net/api/";
    public static final String GETALL_PRODUCTS = "Products/GetAll?count=%s&typeImage=%s";

    public static final int CAMERA = 1;
    public static final int GALLERY = 2;
    public static final int CROP_IMAGE_CAMERA = 3;
    public static final int P_READ_EXTERNAL_STORAGE = 4;
    public static final int P_FINE_LOCATION = 5;
}
