package com.example.ahmedel_diasty.mas;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmedel_diasty.mas.model.Schedule;

import java.util.List;

/**
 * Created by Ahmed El-Diasty on 24/03/2018.
 */

    public class InnerRecyclerAdapter extends RecyclerView.Adapter<InnerRecyclerAdapter.MyViewHolder> {

        List<Schedule> schedules;

//    String lectuers[] = {"Computer Science","Information System","Artificial Intellegence", "Data Mining"
//            ,"Neural Network","Graduation project"};

    int image [] = {R.drawable.student2 ,R.drawable.student2,R.drawable.attendance_place
            ,R.drawable.attendance_place,R.drawable.attendance_place ,R.drawable.attendance_place};

    Context context;
    Dialog myDialog;
    public InnerRecyclerAdapter(Context context,List<Schedule>schedules) {
        this.context = context;
        this.schedules = schedules;
    }

    public InnerRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_recycler);

        return new InnerRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.lectureName.setText(schedules.get(position).getSubjectName());
        holder.Place.setText(schedules.get(position).getType());
        holder.Time_Lecture.setText(schedules.get(position).getStartTime());
        holder.SpacecraftImage.setImageResource(image[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView dialog_name = myDialog.findViewById(R.id.dialog_name);
                dialog_name.setText(schedules.get(position).getSubjectName());

                TextView dialog_time = myDialog.findViewById(R.id.dialog_time);
                dialog_time.setText(schedules.get(position).getStartTime());

                TextView dialog_place = myDialog.findViewById(R.id.dialog_place);
                dialog_place.setText(schedules.get(position).getLocation());

                myDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView lectureName;
        ImageView SpacecraftImage;
        TextView Place;
        TextView Time_Lecture;

        public MyViewHolder(View itemView) {
            super(itemView);
            lectureName = itemView.findViewById(R.id.nameTxt);
            SpacecraftImage = itemView.findViewById(R.id.spacecraftImage);
            Place = itemView.findViewById(R.id.place);
            Time_Lecture=itemView.findViewById(R.id.time_Lecture);
        }
    }
}
