package com.example.mini3;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Get the current user
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // Get the user's email
            String userEmail = user.getEmail();

            // Extract information from the email and set it to TextViews
            extractAndSetUserInfo(userEmail);
        } else {
            Log.e("ProfileActivity", "User is null");
        }
    }

    // Method to extract information from the email and set it to TextViews
    private void extractAndSetUserInfo(String userEmail) {
        // Extract information from the email
        String[] emailParts = userEmail.split("@");
        if (emailParts.length == 2) {
            String[] userNameParts = emailParts[0].split("\\.");
            if (userNameParts.length == 2) {
                // Extract Student_Name and roll number
                String studentName = capitalizeFirstLetter(userNameParts[0]);

                // Extract roll number and add "7181" in front
                String rollNumber = "7181" + userNameParts[1].substring(0, 7); // Adjust substring indices

                // Extract department code and map it to department name
                String departmentCode = userNameParts[1].substring(2, 4);
                String departmentName = getDepartmentName(departmentCode);

                // Set extracted information to TextViews
                TextView studentNameTextView = findViewById(R.id.nameTextView);
                studentNameTextView.setText(studentName);

                TextView rollNumberTextView = findViewById(R.id.rollNumberTextView);
                rollNumberTextView.setText(rollNumber);

                TextView departmentTextView = findViewById(R.id.departmentTextView);
                departmentTextView.setText(departmentName);
            }
        }
    }

    // Method to capitalize the first letter of a string
    private String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Method to map department code to department name
    private String getDepartmentName(String departmentCode) {
        String departmentName = "";
        switch (departmentCode) {
            case "01":
                departmentName = "CSE";
                break;
            // Add more cases for other department codes if needed
            default:
                departmentName = "Unknown"; // Default department name if code not recognized
        }
        return departmentName;
    }
}
