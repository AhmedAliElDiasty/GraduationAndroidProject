package com.example.ahmedel_diasty.mas;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Interface.ItemClickListener;

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
    Dialog dialog;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        return new InnerRecyclerAdapter.MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.lectureName.setText(lectuers[position]);
        dialog = new Dialog(context.getApplicationContext());
        dialog.setContentView(R.layout.subject_information);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, "The index number "+position, Toast.LENGTH_SHORT).show();
                dialog.show();
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return lectuers.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView lectureName;

        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            lectureName = (TextView)itemView.findViewById(R.id.nameTxt);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition());
        }
    }
}
