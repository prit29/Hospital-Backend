package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bottomnavigation.Adapters.patientsAdapter;

public class DoctorMain extends AppCompatActivity {

    private TextView mType;
    private RecyclerView mRecycler;
    private patientsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        String type = getIntent().getStringExtra("Type");
        mType = findViewById(R.id.textView);
        mRecycler = findViewById(R.id.recycler);

        if(type.equals("today")){
            mType.setText("Today's Cases");
            //TODO
            //Request For Today's Appointments Firebase
        }else {
            mType.setText("All Cases");
            //TODO
            //Request For All Appointments FIrebase
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(linearLayoutManager);
        mAdapter = new patientsAdapter(data , this);
        mRecycler.setAdapter(mAdapter);
    }
}
