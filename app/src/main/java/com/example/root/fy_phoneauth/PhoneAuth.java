package com.example.root.fy_phoneauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneAuth extends AppCompatActivity {

    public EditText mPhoneEditText;
    private Button mSendButton;
    private TextView mErrorText;
    public static String phnno;
    private LinearLayout mPhoneLayout;
    private String passwd;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        mPhoneEditText = findViewById(R.id.PhoneEditText);
        mSendButton = findViewById(R.id.sendButton);
        mErrorText = findViewById(R.id.ErrorText);

        mPhoneLayout = findViewById(R.id.PhoneLayout);
        mAuth = FirebaseAuth.getInstance();

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPhoneEditText.setEnabled(false);
                mSendButton.setEnabled(false);

                phnno = mPhoneEditText.getText().toString();
                phnno = "+91" + phnno;

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phnno,
                        60,
                        TimeUnit.SECONDS,
                        PhoneAuth.this,
                        mCallBacks
                );
            }
        });
        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                startActivity(new Intent(PhoneAuth.this, Logged.class));
                finish();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(PhoneAuth.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                mPhoneEditText.setEnabled(true);
                mSendButton.setEnabled(true);
            }
        };
    }

}
