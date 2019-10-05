package com.example.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;

import com.example.bottomnavigation.Adapters.Adapter2;
import com.example.bottomnavigation.pojo_classes.Medicine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ActivityUser extends AppCompatActivity {

    RecyclerView r1;
    Adapter2 adapter;
    ArrayList<Medicine> medical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        r1 = findViewById(R.id.r1);
        r1.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String data = intent.getStringExtra("array");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Medicine>>() {}.getType();
        medical = new ArrayList<>();
        try{
            medical = gson.fromJson(data,type);
            adapter=new Adapter2(medical);
            r1.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityUser.this,ActivityMedicine.class);
                startActivity(intent);
            }
        });
    }

}
