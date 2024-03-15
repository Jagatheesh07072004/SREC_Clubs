package com.example.mini3;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        // Get the image URI from the intent extras
        String imageUriString = getIntent().getStringExtra("imageUri");

        // Load the image into the ImageView using Glide
        ImageView imageView = findViewById(R.id.fullScreenImageView);
        Glide.with(this)
                .load(Uri.parse(imageUriString))
                .into(imageView);
    }
}
