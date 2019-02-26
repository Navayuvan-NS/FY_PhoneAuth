package com.example.root.fy_phoneauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePhoneNumber extends AppCompatActivity {

    private Button update;
    private EditText newPhoneno;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    ImageView acc,hom;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference, mUserRef, mThisUserRef, mPhnNoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);
        newPhoneno = findViewById(R.id.Phno);
        update = findViewById(R.id.btnUpdatephno);
        acc= findViewById(R.id.accountsettings);
        hom=findViewById(R.id.home);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

         databaseReference = firebaseDatabase.getReference();

         mUserRef = databaseReference.child("Users");

         mThisUserRef = mUserRef.child(FirebaseAuth.getInstance().getUid());

         mPhnNoRef = mThisUserRef.child("Phone Number");




    }
    public void accSettings (View v)
    {
        startActivity(new Intent(this, MainActivity.class));

    }
    public void home (View v)
    {
        startActivity(new Intent(this, MainActivity.class));


    }

    public void Update(View view) {

        mPhnNoRef.setValue(newPhoneno.getText().toString());

        Toast.makeText(ChangePhoneNumber.this, "Phone number updated...!!", Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, MainActivity.class));

    }
}
