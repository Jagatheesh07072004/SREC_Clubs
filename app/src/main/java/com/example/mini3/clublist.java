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
        TextView Tamilmandram_club = findViewById(R.id.tamil);
        Tamilmandram_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Tamilmandram club");
                startActivity(intent);
            }
        });
        TextView NCC = findViewById(R.id.ncc);
        NCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "NCC club");
                startActivity(intent);
            }
        });
        TextView RedRibbonclub = findViewById(R.id.redribbon);
        RedRibbonclub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Red ribbon club");
                startActivity(intent);
            }
        });
        TextView NSS = findViewById(R.id.nssclub);
        NSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "NSS club");
                startActivity(intent);
            }
        });
        TextView Yoga = findViewById(R.id.yoga);
        Yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Yoga club");
                startActivity(intent);
            }
        });
        TextView Sports = findViewById(R.id.sports);
      Sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Sports club");
                startActivity(intent);
            }
        });
        TextView Ace = findViewById(R.id.ace);
        Ace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Ace club");
                startActivity(intent);
            }
        });
        TextView Natureclub = findViewById(R.id.nature);
        Natureclub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Nature club");
                startActivity(intent);
            }
        });
        TextView Googlegdc = findViewById(R.id.gdc);
       Googlegdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Google gdc club");
                startActivity(intent);
            }
        });
        TextView finearts = findViewById(R.id.fac);
       finearts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Fine Arts club");
                startActivity(intent);
            }
        });
        TextView Uyirclub = findViewById(R.id.uyir);
        Uyirclub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Uyir club");
                startActivity(intent);
            }
        });
        TextView Philatelic_club = findViewById(R.id.pc);
       Philatelic_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Philatelic club");
                startActivity(intent);
            }
        });
        TextView Aeromodeling = findViewById(R.id.aero_modeling);
       Aeromodeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Aero Modeling club");
                startActivity(intent);
            }
        });
        TextView FOSSclub = findViewById(R.id.foss);
       FOSSclub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Foss club");
                startActivity(intent);
            }
        });
        TextView Redcross = findViewById(R.id.redcross);
       Redcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Red Cross club");
                startActivity(intent);
            }
        });
        TextView Social = findViewById(R.id.social);
        Social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Social Development club");
                startActivity(intent);
            }
        });
        TextView AI_Student= findViewById(R.id.ai);
        AI_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "AI Student club");
                startActivity(intent);
            }
        });
        TextView CSI = findViewById(R.id.csi);
        CSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "CSI club");
                startActivity(intent);
            }
        });
        TextView Yuva = findViewById(R.id.yuva);
        Yuva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Yuva club");
                startActivity(intent);
            }
        });
        TextView Els= findViewById(R.id.els);
      Els.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Els club");
                startActivity(intent);
            }
        });
        TextView Readymovement = findViewById(R.id.ready);
       Readymovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clublist.this, club_view.class);
                intent.putExtra("clubName", "Ready Movement club");
                startActivity(intent);
            }
        });

        // Repeat the process for other clubs
    }
}

