package imetima.appforrunningtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class loginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText)findViewById(R.id.Username);
        etPassword = (EditText)findViewById(R.id.Password);


    }

    protected void OnLogin(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String type = "login";

        BackgroundWork bgWork = new BackgroundWork(this);
        bgWork.execute(type,username,password);
        //Toast.makeText(this, bgWork.res, Toast.LENGTH_SHORT).show();
        if(bgWork.res.equals("SuccessL")){
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        }
        else if(bgWork.res.equals("FailedL")){
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Failed to connect to server !", Toast.LENGTH_SHORT).show();
        }
    }

}
