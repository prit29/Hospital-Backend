package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllContent extends AppCompatActivity {

    private Button mToday,mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_content);

        mToday = findViewById(R.id.today);
        mBook = findViewById(R.id.book);

        mToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllContent.this,DoctorMain.class);
                intent.putExtra("Type","today");
                startActivity(intent);
            }
        });

        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllContent.this,SlotBooking.class));
            }
        });
    }
}
