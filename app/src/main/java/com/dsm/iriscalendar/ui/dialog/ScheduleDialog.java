package com.dsm.iriscalendar.ui.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.data.TokenInterceptor;
import com.dsm.iriscalendar.data.local.PrefHelperImpl;
import com.dsm.iriscalendar.ui.modifyFixedSchedule.ModifyFixedScheduleActivity;
import com.dsm.iriscalendar.ui.modifySchedule.ModifyScheduleActivity;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.sdusb.libs.messagebus.MessageBus;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Api api = new Retrofit.Builder()
                .baseUrl("http://iriscalendar.ap-northeast-2.elasticbeanstalk.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addNetworkInterceptor(new TokenInterceptor(new PrefHelperImpl(Objects.requireNonNull(getContext()))))
                        .build())
                .build()
                .create(Api.class);

        assert getArguments() != null;
        String name = getArguments().getString("name");
        int id = getArguments().getInt("id");
        boolean isAuto = getArguments().getBoolean("isAuto");
        int position = getArguments().getInt("position");

        ((TextView)view.findViewById(R.id.tv_name)).setText(name);
        view.findViewById(R.id.tv_complete).setOnClickListener(v -> {
            if (isAuto) {
                api.completeAuto(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                t -> {
                                    dismiss();
                                    MessageBus.getInstance().handle(1, position);
                                },
                                throwable -> Log.d("DEBUGLOG", throwable.getMessage())
                        );
            } else {
                api.completeManual(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                t -> {
                                    dismiss();
                                    MessageBus.getInstance().handle(1, position);
                                },
                                throwable -> Log.d("DEBUGLOG", throwable.getMessage())
                        );
            }
        });

        view.findViewById(R.id.tv_modify).setOnClickListener(v -> {
            if (isAuto) {
                Intent intent = new Intent(getContext(), ModifyScheduleActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getContext(), ModifyFixedScheduleActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}
