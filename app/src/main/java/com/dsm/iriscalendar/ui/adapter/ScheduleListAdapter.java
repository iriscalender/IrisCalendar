package com.dsm.iriscalendar.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.data.model.CalendarSchedule;
import com.dsm.iriscalendar.ui.modifyFixedSchedule.ModifyFixedScheduleActivity;
import com.dsm.iriscalendar.ui.modifySchedule.ModifyScheduleActivity;

import java.util.ArrayList;
import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {

    private List<CalendarSchedule> listItems = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View viewCircle;
        TextView tvTitle, tvTime;

        ViewHolder(View itemView) {
            super(itemView);
            viewCircle = itemView.findViewById(R.id.view_circle);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
        }

        @SuppressLint("SetTextI18n")
        private void bind() {
            CalendarSchedule item = listItems.get(getAdapterPosition());
            tvTitle.setText(item.getCalendarName());
            tvTime.setText(item.getStartTime().split(" ")[1] + " " + item.getEndTime().split(" ")[1]);
            itemView.setOnClickListener(v -> {
                Context context = itemView.getContext();
                if (item.isAuto()) {
                    Intent intent = new Intent(context, ModifyScheduleActivity.class);
                    intent.putExtra("id", item.getId());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ModifyFixedScheduleActivity.class);
                    intent.putExtra("id", item.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void setItems(List<CalendarSchedule> items) {
        listItems = items;
        notifyDataSetChanged();
    }
}
