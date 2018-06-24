package com.example.ahmedel_diasty.mas.Notification;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class NotificationRowAdapter extends RecyclerView.Adapter<NotificationRowAdapter.RowViewHolder> {
    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RowViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        public RowViewHolder(View itemView) {
            super(itemView);
        }
    }
}
