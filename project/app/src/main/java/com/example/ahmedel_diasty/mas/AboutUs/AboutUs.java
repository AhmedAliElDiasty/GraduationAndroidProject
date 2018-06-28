package com.example.ahmedel_diasty.mas.AboutUs;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ahmedel_diasty.mas.R;

public class AboutUs extends AppCompatActivity {
    TextView name , textView ,made ,team,version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        name = (TextView)findViewById(R.id.aboutus_name);
        textView = (TextView)findViewById(R.id.aboutus_text);
        made = (TextView)findViewById(R.id.made);
        team = (TextView)findViewById(R.id.team);
        version = (TextView)findViewById(R.id.version);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"MTCORSVA.TTF");
        textView.setTypeface(typeface);
        name.setTypeface(typeface);
        made.setTypeface(typeface);
        version.setTypeface(typeface);
        team.setTypeface(typeface);

    }
}
