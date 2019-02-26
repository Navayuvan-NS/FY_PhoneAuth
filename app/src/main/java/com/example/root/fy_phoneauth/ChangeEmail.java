package com.example.root.fy_phoneauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeEmail extends AppCompatActivity {

    private EditText newUserEmail;
    private Button update;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    ImageView acc,hom;

    private EditText password;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference, mUserRef, mThisUserRef, mPhnNoRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        newUserEmail = findViewById(R.id.mail);
        update = findViewById(R.id.btnUpdateemail);
        acc= findViewById(R.id.accountsettings);
        hom=findViewById(R.id.home);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();

        mUserRef = databaseReference.child("Users");

        mThisUserRef = mUserRef.child(FirebaseAuth.getInstance().getUid());

        mPhnNoRef = mThisUserRef.child("Email ID");

        password = findViewById(R.id.passwordEditTextEmail);


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

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential(user.getEmail(), password.getText().toString());

        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.updateEmail(newUserEmail.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ChangeEmail.this, "Email updated", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(ChangeEmail.this, "Email updation failed...!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                });

        mPhnNoRef.setValue(newUserEmail.getText().toString());

        startActivity(new Intent(this, MainActivity.class));

    }
}
