package com.dsm.iriscalendar.ui.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsm.iriscalendar.R;
import com.dsm.iriscalendar.data.model.MappedCalendarSchedule;
import com.dsm.iriscalendar.ui.dialog.ScheduleDialog;
import com.dsm.iriscalendar.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {

    private List<MappedCalendarSchedule> listItems = new ArrayList<>();

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
            MappedCalendarSchedule item = listItems.get(getAdapterPosition());
            tvTitle.setText(item.getCalendarName());
            tvTime.setText(item.getStartTime().split(" ")[1] + " " + item.getEndTime().split(" ")[1]);
            switch (item.getCategory()) {
                case "purple":
                    viewCircle.setBackgroundResource(R.drawable.bg_circle_purple);
                    break;
                case "blue":
                    viewCircle.setBackgroundResource(R.drawable.bg_round_blue);
                    break;
                case "pink":
                    viewCircle.setBackgroundResource(R.drawable.bg_circle_red);
                    break;
                default:
                    viewCircle.setBackgroundResource(R.drawable.bg_circle_orange);
                    break;
            }
            itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("name", item.getCalendarName());
                bundle.putInt("id", item.getId());
                bundle.putBoolean("isAuto", item.isAuto());
                bundle.putInt("position", getAdapterPosition());
                ScheduleDialog dialog = new ScheduleDialog();
                dialog.setArguments(bundle);
                dialog.show(((MainActivity)itemView.getContext()).getSupportFragmentManager(), "");
            });
        }
    }

    public void setItems(List<MappedCalendarSchedule> items) {
        listItems = items;
        notifyDataSetChanged();
    }

    public void deletePosition(int position) {
        listItems.remove(position);
        notifyItemRemoved(position);
    }
}
