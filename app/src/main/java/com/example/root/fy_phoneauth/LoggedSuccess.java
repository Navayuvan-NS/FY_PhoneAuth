package com.example.root.fy_phoneauth;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoggedSuccess extends AppCompatActivity {

   /* private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private DatabaseReference mRoot = firebaseDatabase.getReference();

    private DatabaseReference mUsers = mRoot.child("Users");

    private DatabaseReference mThisUser;

    private DatabaseReference mThisUserEmail;

    private DatabaseReference mThisUserPhnNo; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_success);

        /*mThisUser= mUsers.child(FirebaseAuth.getInstance().getUid().toString());

        mThisUserEmail = mThisUser.child("Email ID");

        mThisUserPhnNo = mThisUser.child("Phone Number");

        mThisUserEmail.setValue(getIntent().getStringExtra("EMAIL_ID"));

        mThisUserPhnNo.setValue(getIntent().getStringExtra("PHN_NO")); */

    }

    public void logoutBtn(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(LoggedSuccess.this, Login.class));

    }

    public void SettingsBtn(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SettingFragment settingFragment = new SettingFragment();

        fragmentTransaction.add(R.id.fragment, settingFragment);

        fragmentTransaction.addToBackStack("a");

        fragmentTransaction.commit();
    }
}
