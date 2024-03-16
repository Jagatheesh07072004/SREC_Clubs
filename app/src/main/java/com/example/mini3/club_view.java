package com.example.mini3;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class club_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_view);

        TextView clubNameTextView = findViewById(R.id.club_name);

        // Retrieve the club name from the intent extras
        String clubName = getIntent().getStringExtra("clubName");

        // Set the club name to the TextView
        clubNameTextView.setText(clubName);

        // Retrieve and display data for the specified club from Firebase
        displayEventDataForClubFromFirebase(clubName);
    }

    private void displayEventDataForClubFromFirebase(String clubName) {
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("events");

        eventsRef.orderByChild("clubName").equalTo(clubName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LinearLayout layout = findViewById(R.id.eventCardLayout);
                layout.removeAllViews();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    String eventName = eventSnapshot.child("eventName").getValue(String.class);
                    String eventDate = eventSnapshot.child("eventDate").getValue(String.class);
                    String eventTime = eventSnapshot.child("eventTime").getValue(String.class);
                    String eventLocation = eventSnapshot.child("eventLocation").getValue(String.class);
                    String eventDescription = eventSnapshot.child("eventDescription").getValue(String.class);
                    String regLink = eventSnapshot.child("regLink").getValue(String.class);
                    String resLink = eventSnapshot.child("resLink").getValue(String.class);
                    String imageUrl = eventSnapshot.child("imageUrl").getValue(String.class);

                    if (eventName != null && eventDate != null && eventTime != null && eventLocation != null && eventDescription != null && imageUrl != null) {
                        addEventToLayout(eventName, clubName, eventDate, eventTime, eventLocation, eventDescription, regLink, resLink, imageUrl);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(club_view.this, "Failed to load events.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addEventToLayout(String eventName, String clubName, String eventDate, String eventTime, String eventLocation, String eventDescription, String regLink, String resLink, String imageUrl) {
        LinearLayout layout = findViewById(R.id.eventCardLayout);
        View eventItem = LayoutInflater.from(this).inflate(R.layout.event_item_layout, layout, false);

        TextView eventNameTextView = eventItem.findViewById(R.id.textViewEventName);
        eventNameTextView.setText(eventName);

        TextView eventDateTextView = eventItem.findViewById(R.id.textViewEventDate);
        eventDateTextView.setText(eventDate);

        ImageView eventImageView = eventItem.findViewById(R.id.card_image);
        Glide.with(this).load(imageUrl).into(eventImageView);

        eventItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(club_view.this, join_event.class);
                intent.putExtra("eventName", eventName);
                intent.putExtra("clubName", clubName);
                intent.putExtra("eventDate", eventDate);
                intent.putExtra("eventTime", eventTime);
                intent.putExtra("eventLocation", eventLocation);
                intent.putExtra("eventDescription", eventDescription);
                intent.putExtra("regLink", regLink);
                intent.putExtra("resLink", resLink);
                intent.putExtra("imageUri", imageUrl != null ? imageUrl.toString() : ""); // Handle null imageUri
                startActivity(intent);
            }
        });

        layout.addView(eventItem,0);
    }
}
