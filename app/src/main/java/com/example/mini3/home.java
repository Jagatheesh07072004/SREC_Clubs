package com.example.mini3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini3.R;

public class home extends AppCompatActivity {

    ImageView homebtn;
    ImageView clublistbtn;
    ImageView calbtn;
    ImageView event;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homebtn=findViewById(R.id.home);
        clublistbtn=findViewById(R.id.clublist);
        calbtn=findViewById(R.id.calendar);
        event=findViewById(R.id.notification);



        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this, homepage.class);
                startActivity(intent);
            }
        });

        clublistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this, clublist.class);
                startActivity(intent);
            }
        });
        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this, calendar.class);
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this, event.class);
                startActivity(intent);
            }
        });

        // Add any additional initialization or logic for the Home activity here
    }
}