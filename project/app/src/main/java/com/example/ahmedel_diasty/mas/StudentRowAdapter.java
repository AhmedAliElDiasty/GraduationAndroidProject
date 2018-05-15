package com.example.ahmedel_diasty.mas;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ahmed on 3/22/18.
 */

public class StudentRowAdapter extends RecyclerView.Adapter<StudentRowAdapter.RowViewHolder>{

    private CountDownTimer countDownTimer;
    private ArrayList<String> data;
    private LayoutInflater layoutInflater;
    private SparseBooleanArray itemStateArraySwitch= new SparseBooleanArray();


    // constructor
     StudentRowAdapter(Context context, ArrayList<String>data) {
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RowViewHolder viewHolder;
        View view = layoutInflater.inflate(R.layout.student_row_item,parent,false);
        viewHolder = new RowViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final RowViewHolder holder, final int position) {
        holder.StudentName.setText(data.get(position));
        holder.bindSwitch(position);
    }

    //  Item Count

    @Override
    public int getItemCount() {
        return data.size();
    }

    // Inner ViewHolder Class

     class RowViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mainRow;
        private RelativeLayout timeCounter;
        private TextView StudentName,status;
        private Switch  aSwitch;
        private Button pause;
        private Button resume;
        private TextView time;

         RowViewHolder(View itemView) {
            super(itemView);
            StudentName = itemView.findViewById(R.id.student_name);
            status = itemView.findViewById(R.id.status);
            aSwitch =  itemView.findViewById(R.id.switch1);
            pause =  itemView.findViewById(R.id.pause);
            mainRow =  itemView.findViewById(R.id.mainRow);
            timeCounter =  itemView.findViewById(R.id.timecount);
            resume =  itemView.findViewById(R.id.resume);
            time =  itemView.findViewById(R.id.time);
            pause.setEnabled(false);

            // Switch implementation

             aSwitch.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int adapterPosition = getAdapterPosition();
                     if (!itemStateArraySwitch.get(adapterPosition, false)) {
                         aSwitch.setChecked(true);
                         itemStateArraySwitch.put(adapterPosition, true);
                     }
                     else  {
                         aSwitch.setChecked(false);
                         itemStateArraySwitch.put(adapterPosition, false);
                     }

                 }
             });
             aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     if(aSwitch.isChecked()){
                         status.setTextColor(Color.rgb(0, 171, 250));
                         status.setEnabled(false);
                         pause.setEnabled(true);
                     }
                     else{
                         status.setTextColor(Color.rgb(12, 82, 114));
                         pause.setEnabled(false);
                     }
                 }
             });

            //
            // Pause Button implementation

             pause.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                         mainRow.setAlpha((float) 0.2);
                         pause.setEnabled(false);
                         aSwitch.setEnabled(false);
                         timeCounter.setVisibility(View.VISIBLE);
                         timeCounter.setAlpha(1);

                         // CountDownTimer implementation
                         int timer = 301000;
                         countDownTimer = new CountDownTimer(timer, 1000) {
                             int minutes;
                             int seconds;
                             int tempSecond;

                             @Override
                             public void onTick(long millisUntilFinished) {
                                 seconds = (int) (millisUntilFinished / (1000));
                                 minutes = seconds / 60;
                                 seconds = seconds % 60;
                                 String timeformat = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                                 time.setText(timeformat);

                                 if (tempSecond == -1) {
                                     minutes--;
                                     seconds = 59;
                                 }
                             }

                             @Override
                             public void onFinish() {
                                 aSwitch.setEnabled(true);
                                 timeCounter.setAlpha(0);
                                 mainRow.setAlpha(1);
                                 aSwitch.setChecked(false);
                             }

                         }.start();

                 }});

             // Resume Button Implementation

             resume.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                         timeCounter.setAlpha(0);
                         countDownTimer.cancel();
                         mainRow.setAlpha(1);
                         aSwitch.setEnabled(true);
                         aSwitch.setChecked(true);
                         pause.setEnabled(true);
                         timeCounter.setVisibility(View.GONE);

                    }
                });
            }

            // Switch Binding

         void bindSwitch(int position) {
             if (!itemStateArraySwitch.get(position, false)) {
                 aSwitch.setChecked(false);
             }
             else {
                 aSwitch.setChecked(true);
             }
         }

         }
    }