package com.example.ahmedel_diasty.mas;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ahmed El-Diasty on 24/03/2018.
 */

    public class InnerRecyclerAdapter extends RecyclerView.Adapter<InnerRecyclerAdapter.MyViewHolder> {
    String lectuers[] = {"Computer Science","Information System","Artificial Intellegence", "Data Mining"
            ,"Neural Network","Graduation project"};
    Context context;
    public InnerRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new InnerRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.lectureName.setText(lectuers[position]);
    }

    @Override
    public int getItemCount() {
        return lectuers.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView lectureName;

        public MyViewHolder(View itemView) {
            super(itemView);
            lectureName = (TextView)itemView.findViewById(R.id.nameTxt);
        }
    }
}
