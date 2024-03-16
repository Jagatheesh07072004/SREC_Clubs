package com.example.mini3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class join_event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);
        // Retrieve event details from intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String eventName = extras.getString("eventName");
            String eventDate = extras.getString("eventDate");
            String eventTime = extras.getString("eventTime");
            String clubName = extras.getString("clubName");
            String eventLocation = extras.getString("eventLocation");
            String description = extras.getString("eventDescription");
            String regLink = extras.getString("regLink");
            String resLink = extras.getString("resLink");
            String imageUriString = extras.getString("imageUri");

            // Set values to TextViews
            TextView eventNameTextView = findViewById(R.id.eventNameTextView);
            eventNameTextView.setText(eventName);
            TextView clubNameTextView = findViewById(R.id.clubNameTextView);
            clubNameTextView.setText(clubName);
            TextView eventDateTextView = findViewById(R.id.dateTextView);
            eventDateTextView.setText(eventDate);
            TextView eventTimeTextView = findViewById(R.id.timeTextView);
            eventTimeTextView.setText(eventTime);
            TextView eventLocationTextView = findViewById(R.id.locationTextView);
            eventLocationTextView.setText(eventLocation);
            TextView descriptionTextView = findViewById(R.id.descriptionTextView);
            descriptionTextView.setText(description);

            // Set image if available
            // Set image if available
            ImageView eventImageView = findViewById(R.id.eventImageView);
            if (imageUriString != null && !imageUriString.isEmpty()) {
                // Load the image using Glide
                Glide.with(this)
                        .load(Uri.parse(imageUriString))
                        .into(eventImageView);

                // Add click listener to open the image in full size
                eventImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Open the image in full size
                        Intent intent = new Intent(join_event.this, FullScreenImageActivity.class);
                        intent.putExtra("imageUri", imageUriString);
                        startActivity(intent);
                    }
                });
            }



            // Handle result button click
            ImageView resButton = findViewById(R.id.res_btn);
            if (resLink != null && !resLink.isEmpty()) {
                resButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Open result link
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(resLink));
                        startActivity(intent);
                    }
                });
            } else {
                // Hide the result button if there's no result link
                resButton.setVisibility(View.GONE);
            }

            // Handle registration button click
            Button regButton = findViewById(R.id.reg);
            if (regLink != null && !regLink.isEmpty()) {
                regButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Open registration link
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(regLink));
                        startActivity(intent);
                    }
                });
            } else {
                // Hide the registration button if there's no registration link
                regButton.setVisibility(View.GONE);
            }
        }
    }
}
