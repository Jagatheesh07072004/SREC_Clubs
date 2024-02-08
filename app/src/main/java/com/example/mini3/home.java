package com.example.mini3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini3.R;

public class home extends AppCompatActivity {
    public void second(View V) {
        Intent i = new Intent(this,homepage.class);
        startActivity(i);
    }
    public void third(View V) {
        Intent i = new Intent(this,clublist.class);
        startActivity(i);

    }
    public void fourth(View V) {
        Intent i = new Intent(this,calendar.class);
        startActivity(i);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Add any additional initialization or logic for the Home activity here
    }
}