package com.dsm.iriscalendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsm.iriscalendar.R;

import java.util.ArrayList;
import java.util.List;

import github.hellocsl.cursorwheel.CursorWheelLayout;

public class WheelClockAdapter extends CursorWheelLayout.CycleWheelAdapter {

    private LayoutInflater inflater;
    private List<String> times;

    public WheelClockAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        times = new ArrayList<>();
        times.add("12");
        for (int i = 1; i < 12; i++) {
            times.add(String.valueOf(i));
        }
    }

    @Override
    public int getCount() {
        return times.size();
    }

    @Override
    public View getView(View parent, int position) {
        String time = getItem(position);
        View root = inflater.inflate(R.layout.item_time, (ViewGroup) parent, false);
        TextView tvTime = root.findViewById(R.id.tv_time);
        tvTime.setText(time);
        return root;
    }

    @Override
    public String getItem(int position) {
        return times.get(position);
    }
}
