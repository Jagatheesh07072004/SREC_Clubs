package com.example.mini3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import android.widget.Button;

import androidx.annotation.NonNull;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {
    TextInputLayout fullName, password;
    Button login;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fullName=findViewById(R.id.fullname);
        password=findViewById(R.id.passs1);
        login=findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ValidateFullname()|!ValidatePassword()){

                }else{

                    loginUser();
                }
            }
        });

    }

    public void loginUser(){
        String userUserName=fullName.getEditText().getText().toString().trim();
        String userPassword=password.getEditText().getText().toString().trim();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(userUserName, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, home.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Login failed. Please check your email and password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private Boolean ValidateFullname(){
        String val=fullName.getEditText().getText().toString();

        if (val.isEmpty()){
            fullName.setError("UserId cannot be Empty");
            return false;
        }
        else{
            fullName.setError(null);
            return true;
        }
    }
    private Boolean ValidatePassword(){
        String val=password.getEditText().getText().toString();

        if (val.isEmpty()){
            password.setError("Password cannot be Empty");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/
}