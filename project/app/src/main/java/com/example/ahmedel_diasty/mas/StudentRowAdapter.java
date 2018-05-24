package com.example.ahmedel_diasty.mas;

/*
 * Created by Ahmed El-Diasty on 06/04/2018.
 */
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
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Model.StudentsInLocation;

import java.util.ArrayList;
import java.util.Locale;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 3/22/18.
 */

public class StudentRowAdapter extends RecyclerView.Adapter<StudentRowAdapter.RowViewHolder>{

    public StudentRowAdapter(Context context, StudentsInLocation students) {
        this.context = context;
        this.students = students;
        layoutInflater = LayoutInflater.from(context);

    }
    private CountDownTimer countDownTimer;
    Context context;
    StudentsInLocation students;
    private LayoutInflater layoutInflater;
    private SparseBooleanArray itemStateArray= new SparseBooleanArray();

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RowViewHolder viewHolder;
        View view = layoutInflater.inflate(R.layout.student_row_item,parent,false);
        viewHolder = new RowViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final RowViewHolder holder, final int position) {
        holder.StudentName.setText(students.getData().get(position).getName());
        holder.bind(position);
        holder.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mainRow.setAlpha((float)0.2);
                holder.pause.setEnabled(false);
                holder.aSwitch.setEnabled(false);
                holder.timeCounter.setAlpha(1);
                int timer = 301000;

                countDownTimer = new CountDownTimer(timer,1000){
                    int minutes ;
                    int seconds;
                    int tempSecond;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        seconds = (int) (millisUntilFinished/(1000));
                        minutes = seconds/60;
                        seconds = seconds%60;
                        String Timeformat = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
                        holder.time.setText(Timeformat);

                        if (tempSecond == -1){
                            minutes--;
                            seconds = 59;
                        }
                    }


                    @Override
                    public void onFinish() {
                        holder.aSwitch.setEnabled(true);
                        holder.timeCounter.setAlpha(0);
                        holder.mainRow.setAlpha(1);
                        holder.aSwitch.setChecked(false);
                    }

                }.start();
            }});


        holder.resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.timeCounter.setAlpha(0);
                countDownTimer.cancel();
                holder.mainRow.setAlpha(1);
                holder.aSwitch.setEnabled(true);
                holder.aSwitch.setChecked(true);
                holder.pause.setEnabled(true);
            }
        });
    }


    @Override
    public int getItemCount() {
        return students.getData().size();
    }
    public void filterList(StudentsInLocation students){
        this.students = students;
        notifyDataSetChanged();
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mainRow;
        private RelativeLayout timeCounter;
        private TextView StudentName,status;
        private CircleImageView image;
        private Switch  aSwitch;
        private Button pause;
        private Button resume;
        private TextView time;

        public RowViewHolder(View itemView) {
            super(itemView);
            StudentName = itemView.findViewById(R.id.student_name);
            status = itemView.findViewById(R.id.status);
            image =  itemView.findViewById(R.id.image_std);
            aSwitch =  itemView.findViewById(R.id.switch1);
            pause =  itemView.findViewById(R.id.pause);
            mainRow =  itemView.findViewById(R.id.mainRow);
            timeCounter =  itemView.findViewById(R.id.timecount);
            resume =  itemView.findViewById(R.id.resume);
            time =  itemView.findViewById(R.id.time);
            pause.setEnabled(false);

            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (!itemStateArray.get(adapterPosition, false)) {
                        aSwitch.setChecked(true);
                        itemStateArray.put(adapterPosition, true);
                    }
                    else  {
                        aSwitch.setChecked(false);
                        itemStateArray.put(adapterPosition, false);
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

        }
        void bind(int position) {
            if (students.getData().get(position).getStatus().equals("0")) {
                aSwitch.setChecked(false);
            }
            else {
                aSwitch.setChecked(true);
            }
        }
    }}