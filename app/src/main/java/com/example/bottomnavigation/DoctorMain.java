package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigation.Adapters.patientsAdapter;
import com.example.bottomnavigation.pojo_classes.patients;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DoctorMain extends AppCompatActivity {


    DatabaseReference databaseReference;
    private TextView mType;
    private RecyclerView mRecycler;
    private String da;
    ArrayList<String> arr= new ArrayList<>();
    ArrayList<patients> data= new ArrayList<>();


    private patientsAdapter mAdapter;
    SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Hospital",MODE_PRIVATE);


        databaseReference = FirebaseDatabase.getInstance().getReference("Hospitals").child(sharedPreferences.getString("HospitalID",""));
        String type = getIntent().getStringExtra("Type");
        mType = findViewById(R.id.textView);
        mRecycler = findViewById(R.id.recycler);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(linearLayoutManager);
        mAdapter = new patientsAdapter(data , this);
        mRecycler.setAdapter(mAdapter);


        if(type.equals("today")){
            mType.setText("Today's Cases");
            Date dd= new Date();
            da= sdf.format(dd);

            databaseReference.child(da).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        data.clear();
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            final DataSnapshot dp= ds;
                            if(!(ds.child("User Id").getValue()+"").equals("Booked By Doctor"))
                            {
                                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child(""+ds.child("User Id").getValue());
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                        String Id= String.valueOf(dataSnapshot.child("id").getValue());
                                        String name = String.valueOf(dataSnapshot.child("Name").getValue());
                                        String ph_no = String.valueOf(dataSnapshot.child("Phone Number").getValue());
                                        String Age = String.valueOf(dataSnapshot.child("Age").getValue());
                                        data.add(new patients(Id,da,name,ph_no,dp.getKey(),Age));
                                        mAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            else
                            {
                                data.add(new patients("0",da,"Booked By Doctor","0",dp.getKey(),"0"));
                                mAdapter.notifyDataSetChanged();
                            }

                        }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }else {
            mType.setText("All Cases");
            //TODO
            //Request For All Appointments FIrebase
        }


    }
}
