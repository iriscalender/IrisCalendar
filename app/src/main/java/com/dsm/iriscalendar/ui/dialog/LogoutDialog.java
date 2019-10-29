package com.dsm.iriscalendar.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.ui.login.LoginActivity;

import java.util.Objects;

public class LogoutDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_logout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("pref", Context.MODE_PRIVATE);

        view.findViewById(R.id.tv_yes).setOnClickListener(v -> {
            pref.edit().remove("UUID_KEY").apply();
            pref.edit().remove("TOKEN_KEY").apply();

            getActivity().finish();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        view.findViewById(R.id.tv_no).setOnClickListener(v -> dismiss());
    }
}
