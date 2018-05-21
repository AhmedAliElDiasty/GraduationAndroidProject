package com.example.ahmedel_diasty.mas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Model.Data;
import com.example.ahmedel_diasty.mas.Model.Model;
import com.example.ahmedel_diasty.mas.Remote.ApiClient;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginForm extends AppCompatActivity {
    ApiInterface apiInterface;
    EditText APPusername,APPpassword;
    Model model;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        APPusername = findViewById(R.id.username);
        APPpassword = findViewById(R.id.password);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

    }
    public void test(View view){
        if (type.equals("student")){
            final String username = APPusername.getText().toString();
            final String password = APPpassword.getText().toString();
            model = new Model();
            if(ValidateLogin(username,password)){
                apiInterface = ApiClient.getClient().create(ApiInterface.class);


                Call<Model> studentModelCall = apiInterface.setData(username,password);
                studentModelCall.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Toast.makeText(LoginForm.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ManualAttendance.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Toast.makeText(LoginForm.this, "Username or Password is inCorrect", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        else{
            final String username = APPusername.getText().toString();
            final String password = APPpassword.getText().toString();
            model = new Model();
            if(ValidateLogin(username,password)){
                apiInterface = ApiClient.getClient().create(ApiInterface.class);


                Call<Model> instructorModelCall = apiInterface.setInstructorDta(username,password);
                instructorModelCall.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Toast.makeText(LoginForm.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ManualAttendance.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Toast.makeText(LoginForm.this, "Username or Password is inCorrect", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }
    private boolean ValidateLogin(String username , String Password){
        if(username ==null||username.trim().length()==0){
            Toast.makeText(this, "username is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Password ==null||Password.trim().length()==0){
            Toast.makeText(this, "Password is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }
}
