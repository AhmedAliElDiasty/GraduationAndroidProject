package com.example.ahmedel_diasty.mas;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        APPusername = (EditText)findViewById(R.id.username);
        APPpassword = (EditText)findViewById(R.id.password);

    }
    public void test(View view){
        final String username = APPusername.getText().toString();
        final String password = APPpassword.getText().toString();
        model = new Model();
        if(ValidateLogin(username,password)){
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Model> studentModelCall = apiInterface.getData();
            studentModelCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {
                    model = response.body();
                    if( username == model.getData().get(0).getUsername()&&password == model.getData().get(0).getPassword()){
                        Toast.makeText(LoginForm.this, "Success", Toast.LENGTH_SHORT).show();;
                    }
                    else{
                        Toast.makeText(LoginForm.this, "Username or Password is inCorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(LoginForm.this, " Connection Error", Toast.LENGTH_SHORT).show();
                }
            });

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
