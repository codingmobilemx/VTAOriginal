package com.vta.codingmobile.vtamovil.Helpers;

import android.app.ProgressDialog;

import com.vta.codingmobile.vtamovil.MainActivity;


public class LoadingDialog {
    static ProgressDialog progressDialog = new ProgressDialog(MainActivity.context);
    private static LoadingDialog instance = null;

    public static synchronized LoadingDialog getInstance() {
        if (instance == null) {
            instance = new LoadingDialog();
        }
        return instance;
    }

    public void show(String mensaje) {

        progressDialog.setMessage(mensaje);
        progressDialog.setIndeterminate(Boolean.FALSE);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(Boolean.FALSE);
        progressDialog.show();
    }
    public void updateMessage(String mensaje) {

        progressDialog.setMessage(mensaje);
    }
    public void dismiss() {
        progressDialog.dismiss();
    }
}