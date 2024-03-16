package com.example.mini3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class clublist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clublist);

        // Find the TextView for the fac club
        TextView facClub = findViewById(R.id.faclist);

        // Set an OnClickListener to navigate to activity_club_view when the fac club TextView is clicked
        facClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to activity_club_view
                Intent intent = new Intent(clublist.this, club_view.class);

                // Pass any necessary data to the activity if needed
                // For example, you can pass the club name
                intent.putExtra("clubName", "Fac Club");

                // Start the activity
                startActivity(intent);
            }
        });

        // Similarly, you can add OnClickListener for other clubs as well
        // Repeat the process for each club TextView

        // Example for Phoratz club
        TextView phoratzClub = findViewById(R.id.phoratz);
        phoratzClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Phoratz Club");
                startActivity(intent);
            }
        });

        // Repeat the process for other clubs

        TextView quiz_club = findViewById(R.id.quiz);
        quiz_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "quiz");
                startActivity(intent);
            }
        });

        // Repeat the process for other clubs
    }
}

