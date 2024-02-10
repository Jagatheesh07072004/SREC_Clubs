package com.example.mini3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        final Button selectImageButton = dialogLayout.findViewById(R.id.btnSelectImage);

        builder.setView(dialogLayout);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String eventName = eventNameInput.getText().toString();
                String eventDate = eventDateInput.getText().toString();
                if (eventName.isEmpty() || eventDate.isEmpty() || selectedImageUri == null) {
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

        TextView eventMonthTextView = eventItem.findViewById(R.id.textViewEventMonth);
        // Extract month from eventDate and set it to eventMonthTextView
        String month = extractMonthFromString(eventDate);
        eventMonthTextView.setText(month);

        ImageView eventImageView = eventItem.findViewById(R.id.card_image);
        eventImageView.setImageURI(imageUri);

        // Add event card to layout
        layout.addView(eventItem);
    }

    private String extractMonthFromString(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return DateFormat.format("MMM", calendar).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
        }
    }
}
