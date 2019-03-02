package imetima.appforrunningtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = (EditText)findViewById(R.id.Username);
        etPassword = (EditText)findViewById(R.id.Password);
    }
    protected void OnRegister(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String type = "register";

        BackgroundWork bgWork = new BackgroundWork(this);
        bgWork.execute(type,username,password);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(bgWork.res.equals("SuccessR")){
            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
            //TODO: skok na pocetnu stranu aplikacije
        }
        else if(bgWork.res.equals("FailedR")){
            Toast.makeText(this, "Username is already taken, try another one.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Failed to connect to server !", Toast.LENGTH_SHORT).show();
        }
    }
}
