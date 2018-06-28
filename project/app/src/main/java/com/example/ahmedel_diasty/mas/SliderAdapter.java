package com.example.ahmedel_diasty.mas;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ahmedel_diasty.mas.Model.Question;

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


    public int slide_image = R.drawable.feedback;



    @Override
    public int getCount() {
        return questions.size() ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);


        TextView SlideDescription = (TextView) view.findViewById(R.id.slide_desc);
        ImageView SlideImageView = (ImageView) view.findViewById(R.id.slide_image);


        SlideDescription.setText(questions.get(position).getQuestion());
       SlideImageView.setImageResource(slide_image);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((ConstraintLayout) object);

    }
}
