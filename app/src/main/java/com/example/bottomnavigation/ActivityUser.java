package com.example.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;

import com.example.bottomnavigation.Adapters.Adapter2;
import com.example.bottomnavigation.pojo_classes.Medicine;
import com.example.bottomnavigation.pojo_classes.patients;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ActivityUser extends AppCompatActivity {

    RecyclerView mRecycler;
    Adapter2 adapter;
    ArrayList<Medicine> medical;
    TextView mName,mContact;
    patients userprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        String user = getIntent().getStringExtra("User");
        Gson gson = new Gson();
        userprofile = gson.fromJson(user,patients.class);

        mName = findViewById(R.id.name);
        mContact = findViewById(R.id.contact);

        mName.setText(userprofile.getName());
        mContact.setText(userprofile.getContact());

        mRecycler = findViewById(R.id.recyclerMedicine);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));


        //TODO
        //Get Data From Firebase For UserID about medicins and create an arraylist<patients> and pass to recyclerview adapter

        /*Adapter2 adapter2 = new Adapter2(medicineData);
        mRecycler.setAdapter(adapter2);
*/
        FloatingActionButton fab = findViewById(R.id.addmedicine);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityUser.this,ActivityMedicine.class);
                intent.putExtra("UserID",userprofile.getId());
                startActivity(intent);
            }
        });
    }
}
