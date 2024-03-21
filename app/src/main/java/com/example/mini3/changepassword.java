package com.example.mini3;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepassword extends AppCompatActivity {

    private static final String TAG = "ChangePasswordActivity";

    private EditText mOldPasswordEditText, mNewPasswordEditText, mConfirmPasswordEditText;
    private Button mChangePasswordButton;
    private TextView mStatusTextView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mOldPasswordEditText = findViewById(R.id.OldPassword);
        mNewPasswordEditText = findViewById(R.id.editTextNewPassword);
        mConfirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
        mChangePasswordButton = findViewById(R.id.buttonChangePassword);
        mStatusTextView = findViewById(R.id.textViewStatus);

        mChangePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        String oldPassword = mOldPasswordEditText.getText().toString().trim();
        String newPassword = mNewPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(oldPassword)) {
            mOldPasswordEditText.setError("Please enter your old password");
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            mNewPasswordEditText.setError("Please enter your new password");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            mConfirmPasswordEditText.setError("Please confirm your new password");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            mConfirmPasswordEditText.setError("Passwords do not match");
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User password updated.");
                                mStatusTextView.setText("Password updated successfully");
                                Toast.makeText(changepassword.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e(TAG, "Error updating password", task.getException());
                                mStatusTextView.setText("Failed to update password");
                                Toast.makeText(changepassword.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Log.e(TAG, "User is not signed in");
            mStatusTextView.setText("User is not signed in");
            Toast.makeText(changepassword.this, "User is not signed in", Toast.LENGTH_SHORT).show();
        }
    }
}
