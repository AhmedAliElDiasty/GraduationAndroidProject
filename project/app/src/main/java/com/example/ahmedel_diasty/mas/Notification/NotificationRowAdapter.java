package com.example.ahmedel_diasty.mas.Notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmedel_diasty.mas.R;
import com.example.ahmedel_diasty.mas.Sqlite.DBConnection;

public class NotificationRowAdapter extends RecyclerView.Adapter<NotificationRowAdapter.RowViewHolder> {
    private Context context;
    DBConnection dbConnection;

    NotificationRowAdapter(Context context) {
        this.context = context;
        dbConnection = new DBConnection(context);
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifiation_items_list,parent,false);
        return new NotificationRowAdapter.RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RowViewHolder holder, int position) {

        holder.lectureName.setText(dbConnection.retrieveData().getRowObjects().get(position).getActivityName());
        holder.description.setText(dbConnection.retrieveData().getRowObjects().get(position).getDescription());
        holder.time.setText(dbConnection.retrieveData().getRowObjects().get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return dbConnection.retrieveData().getRowObjects().size();
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView lectureName, description, time;

        public RowViewHolder(View itemView) {

            super(itemView);
            lectureName = itemView.findViewById(R.id.CL_name);
            description = itemView.findViewById(R.id.CL_message);
            time =        itemView.findViewById(R.id.CL_time);

        }
    }
}
