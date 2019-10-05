package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityMedicine extends AppCompatActivity {

    EditText e1;
    CheckBox c1;
    CheckBox c2;
    CheckBox c3;
    Button b1;
    Button b2;

    ArrayList<Medicine> medical ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width*.8),(int)(width*1.2));

        e1 = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        c1 = findViewById(R.id.checkBox);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);

        medical = new ArrayList<>();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=0,b=0,c=0;
                if(c1.isChecked())
                        a=1;
                if(c2.isChecked())
                        b=1;
                if(c3.isChecked())
                        c=1;

                medical.add(new Medicine(e1.getText().toString(),a,b,c));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityUser.class);

                Gson gson = new Gson();
                String data = gson.toJson(medical);
                intent.putExtra("array",data);
                startActivity(intent);
            }
        });

    }
}
