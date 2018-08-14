package com.example.root.fy_phoneauth;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Logged extends PhoneAuth{

    private EditText mPassword;
    public EditText mConformPassword;
    public ProgressBar mPasswordProgressbar;
    private Button mCreateAccount;
    private String password;
    private String conformpassword;
    private String phonenumber;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);
        mPassword = findViewById(R.id.PasswordEditText);
        mConformPassword = findViewById(R.id.ConformPasswordEditText);
        mPasswordProgressbar = findViewById(R.id.PasswordprogressBar);
        mCreateAccount = findViewById(R.id.CreateAccountBtn);
        mAuth = FirebaseAuth.getInstance();

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Verification();
            }
        });
    }

    public void Verification(){

        password = mPassword.getText().toString();
        conformpassword = mConformPassword.getText().toString();

        if(password.compareTo(conformpassword) != 0){
            mConformPassword.setError("Password not Equal");
            mConformPassword.requestFocus();
        }
        else{

            mPasswordProgressbar.setVisibility(View.VISIBLE);
            createuser();
        }


    }
    public void createuser(){
        new PhoneAuth();
        phonenumber = phnno;
        phonenumber = phonenumber+"@gmail.com";
        Toast.makeText(Logged.this,phonenumber,
                Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(phonenumber, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(Logged.this,LoggedSuccess.class));
                            finish();
                            mPasswordProgressbar.setVisibility(View.INVISIBLE);

                        } else {

                            Toast.makeText(Logged.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            mPasswordProgressbar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

    }

}
