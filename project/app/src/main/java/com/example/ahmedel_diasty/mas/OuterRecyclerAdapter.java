package com.example.ahmedel_diasty.mas;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Model.Schedule;
import com.example.ahmedel_diasty.mas.Remote.ApiClient;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed El-Diasty on 24/03/2018.
 */

public class OuterRecyclerAdapter extends RecyclerView.Adapter<OuterRecyclerAdapter.MyViewHolder> {

    private ApiInterface apiInterface;
    private LayoutInflater layoutInflater;
    private Context context;
    private String[] week = {"Saturday", "Sunday", "Monday", "Tuesday",
            "Wednesday","Thursday"};

    Schedule schedule;

    public OuterRecyclerAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.outer_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final boolean[] visible = {false};
        holder.weekDay.setText(week[position]);
        holder.line.setImageResource(R.color.colorBlue);
        holder.weekDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!visible[0]){
                    visible[0] = true;
                    holder.innerList.setVisibility(View.VISIBLE);
                    holder.details.setText("-");
                    holder.details.setTextSize(40);
//                    holder.details.setPadding(30,-25,0,0);

                    holder.layoutManager = new LinearLayoutManager(context);
                    holder.innerList.setLayoutManager(holder.layoutManager);
                    holder.innerList.setHasFixedSize(true);
                    schedule = new Schedule();


                    apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<Schedule> scModelCall = apiInterface.getScheduleCall();
                    scModelCall.enqueue(new Callback<Schedule>() {
                        @Override
                        public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                            schedule = response.body();
                            holder.recyclerAdapter = new InnerRecyclerAdapter(context,week[position],schedule);
                            holder.innerList.setAdapter(holder.recyclerAdapter);
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(context, schedule.getDataSchedules().size(), Toast.LENGTH_SHORT).show();
                            Log.i("++++++++++++",schedule.getDataSchedules().get(0).getDay());
//                if (schedule.getDataSchedules().get(position).getDay().equals(day)){

//                }

                        }

                        @Override
                        public void onFailure(Call<Schedule> call, Throwable t) {

                            Toast.makeText(context, "Not Response", Toast.LENGTH_SHORT).show();
                        }
                    });


                }else{
                    visible[0] = false;
                    holder.innerList.setVisibility(View.GONE);
                    holder.details.setText("+");
                    holder.details.setTextSize(40);
//                    holder.details.setPadding(20,5,0,0);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return week.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView weekDay;
        TextView details;
        ImageView line;
        private RecyclerView innerList;
        public RecyclerView.LayoutManager layoutManager;
        public InnerRecyclerAdapter recyclerAdapter;


        TextView rowLectureName,rowStartTime,rowType;

        public MyViewHolder(View itemView) {
            super(itemView);
            weekDay = (TextView) itemView.findViewById(R.id.weekDays);
            details = (TextView) itemView.findViewById(R.id.details);
            line = (ImageView) itemView.findViewById(R.id.line);
            innerList = (RecyclerView) itemView.findViewById(R.id.innerList);


            rowLectureName = itemView.findViewById(R.id.rowLectureName);
            rowStartTime = itemView.findViewById(R.id.rowStartTime);
            rowType = itemView.findViewById(R.id.rowType);
        }
    }
}



