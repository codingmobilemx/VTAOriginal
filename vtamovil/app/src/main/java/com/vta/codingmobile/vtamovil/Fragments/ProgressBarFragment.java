package com.vta.codingmobile.vtamovil.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.vta.codingmobile.vtamovil.MainActivity;
import com.vta.codingmobile.vtamovil.R;

import java.util.Objects;

public class ProgressBarFragment extends DialogFragment {

    public ProgressBarFragment() {

    }

    private static ProgressBarFragment instance = null;

    public static synchronized ProgressBarFragment getInstance() {
        if (instance == null) {
            instance = new ProgressBarFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.progress_bar, container, false);

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().setCanceledOnTouchOutside(false);
    }
    public void show() {
        Dialog dialog = this.getDialog();
        if (dialog != null) {
            this.dismiss();
        }
        android.app.FragmentManager fm = ((MainActivity) MainActivity.context).getFragmentManager();
        if (fm != null) {
            this.show(fm, "Progress");
        }
    }
}