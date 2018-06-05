package com.example.ahmedel_diasty.mas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        APPusername = findViewById(R.id.username);
        APPpassword = findViewById(R.id.password);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        button = findViewById(R.id.btn_login);

    }
    public void test(View view){
        button.setEnabled(false);
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
                        if (response.isSuccessful()){
                            Toast.makeText(LoginForm.this, "Success", Toast.LENGTH_SHORT).show();
                            Log.i("++++++++++++++++++",response.message());
                            Intent intent = new Intent(getApplicationContext(),ManualAttendance.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginForm.this, "Username or Password is inCorrect", Toast.LENGTH_SHORT).show();
                            button.setEnabled(true);
                        }



                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Toast.makeText(LoginForm.this, "Username or Password is inCorrect", Toast.LENGTH_SHORT).show();
                        button.setEnabled(true);
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
                        button.setEnabled(true);
                    }
                });
            }
        }


    }
    private boolean ValidateLogin(String username , String Password){
        if(username ==null||username.trim().length()==0){
            Toast.makeText(this, "username is Required", Toast.LENGTH_SHORT).show();
            button.setEnabled(true);
            return false;
        }
        if(Password ==null||Password.trim().length()==0){
            Toast.makeText(this, "Password is Required", Toast.LENGTH_SHORT).show();
            button.setEnabled(true);
            return false;
        }
            return true;
    }
}
