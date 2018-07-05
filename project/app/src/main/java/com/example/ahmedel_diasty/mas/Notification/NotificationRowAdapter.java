package com.example.ahmedel_diasty.mas.Notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.R;
import com.example.ahmedel_diasty.mas.Sqlite.DBConnection;

public class NotificationRowAdapter extends RecyclerView.Adapter<NotificationRowAdapter.RowViewHolder> {
    private DBConnection dbConnection;

    NotificationRowAdapter(Context context) {
        dbConnection = new DBConnection(context);
        if (dbConnection.retrieveData().getRowObjects().size() == 0){
            Toast.makeText(context, "There are no notification to show", Toast.LENGTH_SHORT).show();
        }
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

    class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView lectureName, description, time;

        RowViewHolder(View itemView) {

            super(itemView);
            lectureName = itemView.findViewById(R.id.CL_name);
            description = itemView.findViewById(R.id.CL_message);
            time =        itemView.findViewById(R.id.CL_time);

        }
    }
}
