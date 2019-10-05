package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    private EditText mID,mPassword;
    private Button mProceed;
    private TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mID = findViewById(R.id.id);
        mPassword = findViewById(R.id.password);
        mProceed = findViewById(R.id.proceed);
        mRegister = findViewById(R.id.register);

        mProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mID.getEditableText().toString();
                String password = mPassword.getEditableText().toString();

                //Toast.makeText(getApplicationContext(),id+"\t"+password,Toast.LENGTH_LONG).show();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,Request.class);
                startActivity(intent);
            }
        });
    }

}
