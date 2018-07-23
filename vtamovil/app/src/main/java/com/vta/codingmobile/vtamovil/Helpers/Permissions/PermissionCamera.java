package com.vta.codingmobile.vtamovil.Helpers.Permissions;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.vta.codingmobile.vtamovil.Helpers.Constants;
import com.vta.codingmobile.vtamovil.MainActivity;
import com.vta.codingmobile.vtamovil.R;

public class PermissionCamera {

    private static final int currentAPIVersion = Build.VERSION.SDK_INT;

    public static boolean checkPermissionLocation(final Context context) {
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constants.P_FINE_LOCATION);
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public static boolean checkPermissionCamera() {

        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) MainActivity.context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    android.app.AlertDialog.Builder alertBuilder = new android.app.AlertDialog.Builder(MainActivity.context);
                    alertBuilder.setCancelable(false);
                    alertBuilder.setTitle(R.string.app_name);
                    alertBuilder.setMessage(R.string.permission_camera);
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) MainActivity.context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.P_READ_EXTERNAL_STORAGE);
                        }
                    });
                    android.app.AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) MainActivity.context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.P_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }

    }

}
