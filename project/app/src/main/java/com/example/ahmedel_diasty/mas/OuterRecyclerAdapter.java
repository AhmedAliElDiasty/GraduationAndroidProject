package com.example.ahmedel_diasty.mas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Model.DataSchedule;
import com.example.ahmedel_diasty.mas.Model.Schedule;
import com.example.ahmedel_diasty.mas.Remote.ApiClient;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed El-Diasty on 24/03/2018.
 */

public class OuterRecyclerAdapter extends RecyclerView.Adapter<OuterRecyclerAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private String[] week = {"Saturday", "Sunday", "Monday", "Tuesday",
            "Wednesday","Thursday"};

    public RecyclerView.LayoutManager innerLayoutManager;

    Schedule schedule;
    Schedule schedule2;
    ArrayList<DataSchedule> subjects;

    public OuterRecyclerAdapter(Context context,Schedule schedule) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.schedule = schedule;
        subjects = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.outer_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView")final int position) {


        holder.setIsRecyclable(false
        );
        final boolean[] visible = {false};
        holder.weekDay.setText(week[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!visible[0]){
                    visible[0] = true;
                    holder.weekDay.setTextColor(Color.RED);
                    holder.innerList.setVisibility(View.VISIBLE);
                    holder.details.setImageResource(R.drawable.caret_up);
                    holder.indecator.setVisibility(View.VISIBLE);
                    holder.layoutManager = new LinearLayoutManager(context);
                    holder.innerList.setLayoutManager(holder.layoutManager);
                    holder.innerList.setHasFixedSize(true);
                    schedule2 = new Schedule();


                    subjects.clear();

                    Toast.makeText(context, "Fetch", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < schedule.getDataSchedules().size(); i++) {
                        if (schedule.getDataSchedules().get(i).getDay().equals(week[position])){
                            subjects.add(schedule.getDataSchedules().get(i));
                            Log.i("type",schedule.getDataSchedules().get(i).getType());
                        }
                    }
                    schedule2.setDataSchedules(subjects);

                    innerLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
                    holder.innerList.setLayoutManager(innerLayoutManager);
                    holder.recyclerAdapter = new InnerRecyclerAdapter(context,schedule2);
                    holder.innerList.setAdapter(holder.recyclerAdapter);
                    Log.i("++++++++++++Subject",""+subjects);



                }else{
                    visible[0] = false;
                    holder.innerList.setVisibility(View.GONE);
                    holder.weekDay.setTextColor(Color.WHITE);
                    holder.details.setImageResource(R.drawable.caret_down);
                    holder.indecator.setVisibility(View.INVISIBLE);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return week.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView weekDay;
        ImageView details;
        ImageView indecator;
        private RecyclerView innerList;
        public RecyclerView.LayoutManager layoutManager;
        public InnerRecyclerAdapter recyclerAdapter;


        TextView rowLectureName,rowStartTime,rowType;

        public MyViewHolder(View itemView) {
            super(itemView);
            weekDay = itemView.findViewById(R.id.weekDays);
            details = itemView.findViewById(R.id.details);
            indecator = itemView.findViewById(R.id.indecator);
            innerList = itemView.findViewById(R.id.innerList);
            rowLectureName = itemView.findViewById(R.id.rowLectureName);
            rowStartTime = itemView.findViewById(R.id.rowStartTime);
            rowType = itemView.findViewById(R.id.rowType);


        }
    }
}



