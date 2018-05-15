package com.example.ahmedel_diasty.mas;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ahmed El-Diasty on 24/03/2018.
 */

public class OuterRecyclerAdapter extends RecyclerView.Adapter<OuterRecyclerAdapter.MyViewHolder> {

    LayoutInflater layoutInflater;
    Context context;
        String[] week = {"Saturday", "Sunday", "Monday", "Tuesday",
            "Wednesday","Thursday"};

    public OuterRecyclerAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outer_row,parent,false);
        View view = layoutInflater.inflate(R.layout.outer_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
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
                    holder.recyclerAdapter = new InnerRecyclerAdapter(context);
                    holder.innerList.setAdapter(holder.recyclerAdapter);
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
        public MyViewHolder(View itemView) {
            super(itemView);
            weekDay = (TextView)itemView.findViewById(R.id.weekDays);
            details = (TextView) itemView.findViewById(R.id.details);
            line = (ImageView)itemView.findViewById(R.id.line);
            innerList = (RecyclerView)itemView.findViewById(R.id.innerList);
        }
    }
}



