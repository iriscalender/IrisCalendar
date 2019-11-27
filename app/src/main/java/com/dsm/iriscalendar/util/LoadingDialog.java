package com.dsm.iriscalendar.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.dsm.iriscalendar.R;

public class LoadingDialog extends DialogFragment {

    private static LoadingDialog dialog = new LoadingDialog();

    public static void show(FragmentManager fragmentManager) {
        dialog.setCancelable(false);
        dialog.show(fragmentManager, "");
    }

    public static void hide() {
        dialog.dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_loading, container, false);
    }
}