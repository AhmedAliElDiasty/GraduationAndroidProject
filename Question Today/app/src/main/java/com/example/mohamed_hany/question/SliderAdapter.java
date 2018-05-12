package com.example.mohamed_hany.question;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.mohamed_hany.question.model.Question;

import java.util.List;


/**
 * Created by Mohamed_Hany on 3/23/2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public String[] slide_descs;
    List<Question> questions;
    String question_text;

    public SliderAdapter(Context context ,List<Question>questions) {
        this.context = context;
        this.questions =questions ;

    }


    //            "What do you think about this Lecture ?",
//            "Do you think you can make lecture better",
//            "What do you think about the Next Lecture ?",
//            "do you think about the Next Lecture ?",
//            "What do you think about the Next Lecture ?"
//    public void get_Questions() {
//        AndroidNetworking.get("https://virtserver.swaggerhub.com/DreamTeam33/GraduationProject_MSA/1.0.0/api/questionsByAdmin")
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsObjectList(new Question().getClass(),
//                        new ParsedRequestListener<List<Question>>() {
//
//
//                            @Override
//                            public void onResponse(List<Question> response) {
//                                questions = response;
//                                for (int i = 0; i < questions.size(); i++) {
//                                    question_text = questions.get(i).getQuestion();
//
//                                    slide_descs[i] = question_text;
//                                }
//
//                                Toast.makeText(context, "Get Data Succussefuly ", Toast.LENGTH_LONG).show();
//                            }
//
//                            @Override
//                            public void onError(ANError anError) {
//                                Log.e("Recived_Error", anError.toString());
//                            }
//                        });
//    }
//};

    public int[] slide_image = {
            R.drawable.question1,
            R.drawable.question2,
            R.drawable.question3,
            R.drawable.question4,
            R.drawable.question5

    };


    @Override
    public int getCount() {
        return questions.size() ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);


        TextView SlideDescription = (TextView) view.findViewById(R.id.slide_desc);
        ImageView SlideImageView = (ImageView) view.findViewById(R.id.slide_image);


        SlideDescription.setText(questions.get(position).getQuestion());
       SlideImageView.setImageResource(slide_image[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout) object);

    }
}
