package com.example.ahmedel_diasty.mas;

/**
 * Created by Ahmed El-Diasty on 06/04/2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 3/22/18.
 */

public class StudentRowAdapter extends RecyclerView.Adapter<StudentRowAdapter.RowViewHolder>{
    CountDownTimer countDownTimer;
    String [] studentName = {"Ahmed","Mohamed","Ali","Sameh","Aya","Asmaa","Sara","Ebrahim"
            ,"Hazem","Hussien","Refaat","Hend","Alaa"};

    public StudentRowAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    private Context context;
    private ArrayList<String> data;
    private LayoutInflater layoutInflater;

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.student_row_item,parent,false);
        RowViewHolder viewHolder = new RowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RowViewHolder holder, int position) {

        final int itemType = getItemViewType(position);

        final String current =data.get(position);

        holder.StudentName.setText("Student"+position);
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    holder.status.setTextColor(Color.rgb(0,171,250));
                    holder.status.setText("Active");
                }
                else{
                    holder.status.setTextColor(Color.rgb(12,82,114));
                    holder.status.setText("Active");
                }
            }
        });

        holder.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.timeCount.setVisibility(View.VISIBLE);
                holder.mainRow.setVisibility(View.GONE);
                final int myTime = 5*60*1000;
                countDownTimer = new CountDownTimer(myTime,1000) {
                    int tempSecond;
                    int second;
                    int minute;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tempSecond = (int) (millisUntilFinished/1000);
                        minute = tempSecond/60;
                        second= tempSecond%60;
                        holder.timer.setText(minute+":"+second);
                        if (second == -1){
                            minute--;
                        }
                    }

                    @Override
                    public void onFinish() {
                        holder.aSwitch.setChecked(false);
                        holder.status.setTextColor(Color.rgb(12,82,114));
                        holder.timeCount.setVisibility(View.GONE);
                        holder.mainRow.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        });
        holder.resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                holder.timeCount.setVisibility(View.GONE);
                holder.mainRow.setVisibility(View.VISIBLE);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mainRow;
        private RelativeLayout timeCount;
        private TextView StudentName,status;
        private CircleImageView image;
        private Switch  aSwitch;
        private Button pause;
        private Button resume;
        private TextView timer;


        public RowViewHolder(View itemView) {
            super(itemView);
            mainRow = (RelativeLayout)itemView.findViewById(R.id.mainRow);
            timeCount = (RelativeLayout)itemView.findViewById(R.id.timecount);
            StudentName = (TextView)itemView.findViewById(R.id.student_name);
            status = (TextView)itemView.findViewById(R.id.status);
            image = (CircleImageView)itemView.findViewById(R.id.image_std);
            aSwitch = (Switch)itemView.findViewById(R.id.switch1);
            pause = (Button)itemView.findViewById(R.id.pause);
            resume = (Button)itemView.findViewById(R.id.resume);
            timer = (TextView)itemView.findViewById(R.id.time);



//            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked){
//                        status.setTextColor(Color.rgb(0,171,250));
//                        status.setText("Active");
//                    }
//                    else{
//                        status.setTextColor(Color.rgb(12,82,114));
//                        status.setText("Active");
//                    }
//                }
//            });
//
//            pause.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    timeCount.setVisibility(View.VISIBLE);
//                    mainRow.setVisibility(View.GONE);
//                    final int myTime = 5*60*1000;
//                    countDownTimer = new CountDownTimer(myTime,1000) {
//                        int tempSecond;
//                        int second;
//                        int minute;
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            tempSecond = (int) (millisUntilFinished/1000);
//                            minute = tempSecond/60;
//                            second= tempSecond%60;
//                            timer.setText(minute+":"+second);
//                            if (second == -1){
//                                minute--;
//                            }
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            aSwitch.setChecked(false);
//                            status.setTextColor(Color.rgb(12,82,114));
//                            timeCount.setVisibility(View.GONE);
//                            mainRow.setVisibility(View.VISIBLE);
//                        }
//                    }.start();
//                }
//            });
//            resume.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    countDownTimer.cancel();
//                    timeCount.setVisibility(View.GONE);
//                    mainRow.setVisibility(View.VISIBLE);
//                }
//            });
        }


    }

}
