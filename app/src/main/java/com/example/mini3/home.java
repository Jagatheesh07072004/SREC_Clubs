package com.example.mini3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class home extends AppCompatActivity {
    ImageView homebtn;
    ImageView clublistbtn;
    ImageView calbtn;


    ImageView profilebtn;
    ImageView seccutitybtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homebtn=findViewById(R.id.home);
        clublistbtn=findViewById(R.id.clublist);
        calbtn=findViewById(R.id.calendar);

        profilebtn=findViewById(R.id.profile);
        seccutitybtn=findViewById(R.id.securtiy);



        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this, event.class);
                startActivity(intent);
            }
        });
        clublistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,clublist.class);
                startActivity(intent);
            }
        });
        seccutitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,changepassword.class);
                startActivity(intent);
            }
        });
        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,calendar.class);
                startActivity(intent);
            }
        });

        // Add any additional initialization or logic for the Home activity here

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(home.this, profile.class);
                startActivity(intent);
            }
        });
    }
}

