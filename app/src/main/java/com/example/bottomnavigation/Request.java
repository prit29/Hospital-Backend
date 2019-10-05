package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Request extends AppCompatActivity {

    EditText mail,name;
    Button mRequest;
    TextView mLogIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        mail = findViewById(R.id.mail);
        name = findViewById(R.id.name);
        mRequest = findViewById(R.id.request);
        mLogIn = findViewById(R.id.login);

        mAuth = FirebaseAuth.getInstance();

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
