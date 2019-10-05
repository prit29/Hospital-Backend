package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Request extends AppCompatActivity {

    EditText mail,name;
    Button mRequest;
    TextView mLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        mail = findViewById(R.id.mail);
        name = findViewById(R.id.name);
        mRequest = findViewById(R.id.request);
        mLogIn = findViewById(R.id.login);

        mRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MAIL = mail.getText().toString();
                String NAME = name.getText().toString();

                //TODO
                //SEND REQUEST
            }
        });

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Request.this,LogIn.class));
                finish();
            }
        });
    }
}
