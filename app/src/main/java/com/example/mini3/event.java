package com.example.mini3;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class event extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    private Button btnAddEvent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        btnAddEvent = findViewById(R.id.btnAddEvent);

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddEventDialog();
            }
        });

        // Call method to display event data from Firebase
        displayEventDataFromFirebase();
    }


    private void showAddEventDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Event");

        // Set up the input fields
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogLayout = inflater.inflate(R.layout.activity_addevent, null);
        final EditText eventNameInput = dialogLayout.findViewById(R.id.editTextEventName);
        final EditText clubNameInput=dialogLayout.findViewById(R.id.editTextClubName);
        final EditText eventDateInput = dialogLayout.findViewById(R.id.editTextEventDate);
        final EditText eventTimeInput = dialogLayout.findViewById(R.id.editTextEventTime);
        final EditText eventLocationInput = dialogLayout.findViewById(R.id.editTextEventLocation);
        final EditText eventDescriptionInput = dialogLayout.findViewById(R.id.editTextEventDescription);
        final EditText regLinkInput = dialogLayout.findViewById(R.id.editTextEventRegLink); // Added
        final EditText resLinkInput = dialogLayout.findViewById(R.id.editTextEventResLink); // Added
        final Button selectImageButton = dialogLayout.findViewById(R.id.btnSelectImage);

        builder.setView(dialogLayout);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String eventName = eventNameInput.getText().toString();
                String clubName=clubNameInput.getText().toString();
                String eventDate = eventDateInput.getText().toString();
                String eventTime = eventTimeInput.getText().toString();
                String eventLocation = eventLocationInput.getText().toString();
                String eventDescription = eventDescriptionInput.getText().toString();
                String regLink = regLinkInput.getText().toString(); // Added
                String resLink = resLinkInput.getText().toString(); // Added
                if (clubName.isEmpty()||eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || eventLocation.isEmpty() || eventDescription.isEmpty() || selectedImageUri == null) {
                    Toast.makeText(event.this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show();
                } else {
                    addEventToLayout(eventName,clubName, eventDate, eventTime, eventLocation, eventDescription, regLink, resLink, selectedImageUri); // Modified
                }
            }
        });
        builder.setNegativeButton("Cancel", null);

        // Set up click listener for image selection button
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open image picker
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        builder.show();
    }

    private void addEventToLayout(String eventName,String clubName, String eventDate, String eventTime, String eventLocation, String eventDescription, String regLink, String resLink, Uri imageUri) {
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("events");
        String eventId = eventsRef.push().getKey(); // Generate a unique key for the event
        Event_fb event = new Event_fb(eventId, eventName, clubName, eventDate, eventTime, eventLocation, eventDescription, regLink, resLink, imageUri.toString());
        eventsRef.child(eventId).setValue(event);


        LinearLayout layout = findViewById(R.id.eventCardLayout);
        // Inflate event item layout
        View eventItem = LayoutInflater.from(this).inflate(R.layout.event_item_layout, layout, false);
        // Set event name, date, and image
        TextView eventNameTextView = eventItem.findViewById(R.id.textViewEventName);
        eventNameTextView.setText(eventName);
        TextView eventDateTextView = eventItem.findViewById(R.id.textViewEventDate);
        eventDateTextView.setText(eventDate);
        ImageView eventImageView = eventItem.findViewById(R.id.card_image);
        eventImageView.setImageURI(imageUri);
        // Add event card to layout
        layout.addView(eventItem);
        // Set click listener to navigate to detailed view
        eventImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(event.this, join_event.class);
                intent.putExtra("eventName", eventName);

                intent.putExtra("clubName",clubName);
                intent.putExtra("eventDate", eventDate);
                intent.putExtra("eventTime", eventTime);
                intent.putExtra("eventLocation", eventLocation);
                intent.putExtra("eventDescription", eventDescription);
                intent.putExtra("regLink", regLink);
                intent.putExtra("resLink", resLink);
                intent.putExtra("imageUri", imageUri.toString());
                startActivity(intent);
            }
        });
    }
    private void displayEventDataFromFirebase() {
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("events");

        eventsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LinearLayout layout = findViewById(R.id.eventCardLayout);
                layout.removeAllViews(); // Clear existing views

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    Event_fb event = eventSnapshot.getValue(Event_fb.class);
                    if (event != null) {
                        addEventToLayout(event.getEventName(), event.getClubName(), event.getEventDate(), event.getEventTime(), event.getEventLocation(), event.getEventDescription(), event.getRegLink(), event.getResLink(), Uri.parse(event.getImageUrl()));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(event.this, "Failed to load events.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
        }
    }
}