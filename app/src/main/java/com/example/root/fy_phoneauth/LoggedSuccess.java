package com.example.root.fy_phoneauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class LoggedSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_success);

    }

    public void logoutBtn(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(LoggedSuccess.this, Login.class));

    }
}
