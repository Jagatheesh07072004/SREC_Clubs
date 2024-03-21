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

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class event extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    private Button btnAddEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("events"); // Subscribe to the "events" topic

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        btnAddEvent = findViewById(R.id.btnAddEvent);

        // Check if user is authenticated and their email is jaga4305@gmail.com
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail().equals("jaga4305@gmail.com")) {
            btnAddEvent.setVisibility(View.VISIBLE);
        } else {
            btnAddEvent.setVisibility(View.GONE);
            Toast.makeText(this, "You are not authorized to add events.", Toast.LENGTH_SHORT).show();
        }

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
        final EditText clubNameInput = dialogLayout.findViewById(R.id.editTextClubName);
        final EditText eventDateInput = dialogLayout.findViewById(R.id.editTextEventDate);
        final EditText eventTimeInput = dialogLayout.findViewById(R.id.editTextEventTime);
        final EditText eventLocationInput = dialogLayout.findViewById(R.id.editTextEventLocation);
        final EditText eventDescriptionInput = dialogLayout.findViewById(R.id.editTextEventDescription);
        final EditText regLinkInput = dialogLayout.findViewById(R.id.editTextEventRegLink);
        final EditText resLinkInput = dialogLayout.findViewById(R.id.editTextEventResLink);
        final Button selectImageButton = dialogLayout.findViewById(R.id.btnSelectImage);

        builder.setView(dialogLayout);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String eventName = eventNameInput.getText().toString();
                String clubName = clubNameInput.getText().toString();
                String eventDate = eventDateInput.getText().toString();
                String eventTime = eventTimeInput.getText().toString();
                String eventLocation = eventLocationInput.getText().toString();
                String eventDescription = eventDescriptionInput.getText().toString();
                String regLink = regLinkInput.getText().toString();
                String resLink = resLinkInput.getText().toString();
                if (clubName.isEmpty() || eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || eventLocation.isEmpty() || eventDescription.isEmpty() || selectedImageUri == null) {
                    Toast.makeText(event.this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show();
                } else {
                    addEventToFirebase(eventName, clubName, eventDate, eventTime, eventLocation, eventDescription, regLink, resLink, selectedImageUri);
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

    private void addEventToFirebase(String eventName, String clubName, String eventDate, String eventTime, String eventLocation, String eventDescription, String regLink, String resLink, Uri imageUri) {
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("events");
        String eventId = eventsRef.push().getKey(); // Generate a unique key for the event

        // Upload the image to Firebase Storage
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        final StorageReference imageRef = storageRef.child("events/" + eventId + ".jpg");
        imageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Image uploaded successfully, get download URL
                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        Event_fb event = new Event_fb(eventId, eventName, clubName, eventDate, eventTime, eventLocation, eventDescription, regLink, resLink, imageUrl);
                        eventsRef.child(eventId).setValue(event);

                        // Display event in the layout
                        addEventToLayout(eventName, clubName, eventDate, eventTime, eventLocation, eventDescription, regLink, resLink, uri);

                        // Send notification to all users
                        sendEventNotification(eventName);
                    });
                })
                .addOnFailureListener(e -> {
                    // Handle image upload failure
                    Toast.makeText(event.this, "Failed to upload image.", Toast.LENGTH_SHORT).show();
                });
    }

    private void sendEventNotification(String eventName) {
        // Create notification message
        Map<String, String> messageData = new HashMap<>();
        messageData.put("title", "New Event Added!");
        messageData.put("body", eventName + " has been added.");

        // Send FCM message to the "events" topic
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder("events")
                .setData(messageData)
                .build());
    }

    private void addEventToLayout(String eventName, String clubName, String eventDate, String eventTime, String eventLocation, String eventDescription, String regLink, String resLink, Uri imageUri) {
        LinearLayout layout = findViewById(R.id.eventCardLayout);
        // Inflate event item layout
        View eventItem = LayoutInflater.from(this).inflate(R.layout.event_item_layout, layout, false);
        // Set event name, date, and image
        TextView eventNameTextView = eventItem.findViewById(R.id.textViewEventName);
        eventNameTextView.setText(eventName);
        TextView eventDateTextView = eventItem.findViewById(R.id.textViewEventDate);
        eventDateTextView.setText(eventDate);
        ImageView eventImageView = eventItem.findViewById(R.id.card_image);

        // Check if imageUri is not null and then load the image using Glide
        if (imageUri != null) {
            Glide.with(this)
                    .load(imageUri)
                    .into(eventImageView);
        }

        // Set click listener to navigate to detailed view
        eventItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(event.this, join_event.class);
                intent.putExtra("eventName", eventName);
                intent.putExtra("clubName", clubName);
                intent.putExtra("eventDate", eventDate);
                intent.putExtra("eventTime", eventTime);
                intent.putExtra("eventLocation", eventLocation);
                intent.putExtra("eventDescription", eventDescription);
                intent.putExtra("regLink", regLink);
                intent.putExtra("resLink", resLink);
                intent.putExtra("imageUri", imageUri != null ? imageUri.toString() : ""); // Handle null imageUri
                startActivity(intent);
            }
        });

        // Add event card to layout at the beginning
        layout.addView(eventItem, 0);
    }

    private void displayEventDataFromFirebase() {
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("events");

        eventsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear existing layout
                LinearLayout layout = findViewById(R.id.eventCardLayout);
                layout.removeAllViews();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    String eventName = eventSnapshot.child("eventName").getValue(String.class);
                    String clubName = eventSnapshot.child("clubName").getValue(String.class);
                    String eventDate = eventSnapshot.child("eventDate").getValue(String.class);
                    String eventTime = eventSnapshot.child("eventTime").getValue(String.class);
                    String eventLocation = eventSnapshot.child("eventLocation").getValue(String.class);
                    String eventDescription = eventSnapshot.child("eventDescription").getValue(String.class);
                    String regLink = eventSnapshot.child("regLink").getValue(String.class);
                    String resLink = eventSnapshot.child("resLink").getValue(String.class);
                    String imageUrl = eventSnapshot.child("imageUrl").getValue(String.class);

                    if (eventName != null && clubName != null && eventDate != null && eventTime != null && eventLocation != null && eventDescription != null && imageUrl != null) {
                        addEventToLayout(eventName, clubName, eventDate, eventTime, eventLocation, eventDescription, regLink, resLink, Uri.parse(imageUrl));
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