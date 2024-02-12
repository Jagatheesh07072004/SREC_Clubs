
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

public class event extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    private Button btnAddEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        btnAddEvent = findViewById(R.id.btnAddEvent);

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddEventDialog();
            }
        });
    }

    private void showAddEventDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Event");

        // Set up the input fields
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogLayout = inflater.inflate(R.layout.activity_addevent, null);
        final EditText eventNameInput = dialogLayout.findViewById(R.id.editTextEventName);
        final EditText eventDateInput = dialogLayout.findViewById(R.id.editTextEventDate);
        final EditText eventTimeInput = dialogLayout.findViewById(R.id.editTextEventLocation);
        final EditText eventLocationInput = dialogLayout.findViewById(R.id.editTextEventLocation);
        final EditText eventDescriptionInput = dialogLayout.findViewById(R.id.editTextEventDescription);
        final Button selectImageButton = dialogLayout.findViewById(R.id.btnSelectImage);

        builder.setView(dialogLayout);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String eventName = eventNameInput.getText().toString();
                String eventDate = eventDateInput.getText().toString();
                String eventTime = eventTimeInput.getText().toString();
                String eventLocation = eventLocationInput.getText().toString();
                String eventDescription = eventDescriptionInput.getText().toString();
                if (eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || eventLocation.isEmpty() || eventDescription.isEmpty() || selectedImageUri == null) {
                    Toast.makeText(event.this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show();
                } else {
                    addEventToLayout(eventName, eventDate, selectedImageUri);
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

    private void addEventToLayout(String eventName, String eventDate, Uri imageUri) {
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
                intent.putExtra("eventDate", eventDate);
                intent.putExtra("imageUri", imageUri.toString());
                startActivity(intent);
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

