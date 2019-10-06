package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigation.pojo_classes.Medicine;
import com.example.bottomnavigation.pojo_classes.patients;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityMedicine extends AppCompatActivity {

    EditText e1,days;
    CheckBox c1;
    CheckBox c2;
    CheckBox c3;
    SharedPreferences pref;
    Button b1;
    TextView b2;
    DatabaseReference reference;
    ArrayList<Medicine> data= new ArrayList<>();

    patients userprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        DisplayMetrics dm = new DisplayMetrics();
         pref = getSharedPreferences("Hospital",MODE_PRIVATE);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width*.8),(int)(width*1.2));

        String user = getIntent().getStringExtra("User");
        Gson gson = new Gson();
        userprofile = gson.fromJson(user, patients.class);

        e1 = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        c1 = findViewById(R.id.checkBox);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);
        days = findViewById(R.id.days);


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

                String medicine = e1.getText().toString();
                String day = days.getText().toString();

                data.add(new Medicine(medicine,a,b,c,Integer.parseInt(day)));
                e1.setText("");
                days.setText("");
                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson= new Gson();
                String json= gson.toJson(data);
                HashMap<String,Object> mp= new HashMap<>();
                mp.put("Medicines",json);

                DatabaseReference rrr= FirebaseDatabase.getInstance().getReference("Users").child(userprofile.getId());
                rrr.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Medicine Sended",Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
            }
        });
    }
}
