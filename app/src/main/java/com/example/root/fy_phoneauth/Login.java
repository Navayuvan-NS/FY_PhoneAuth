package com.example.root.fy_phoneauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText EmailLogin, passwordLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailLogin = findViewById(R.id.phoneNumberLoginEditText);

        passwordLogin = findViewById(R.id.passwordLoginEditText);

        mAuth = FirebaseAuth.getInstance();

    }

    public void loginBtn(View view) {

        String emailLogin = EmailLogin.getText().toString();

        String password = passwordLogin.getText().toString();


        FirebaseSignin(emailLogin, password);

    }

    public void FirebaseSignin(String emailLogin, String password){

        mAuth.signInWithEmailAndPassword(emailLogin, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            startActivity(new Intent(Login.this, LoggedSuccess.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(com.example.root.fy_phoneauth.TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    public void forgetPasswdBtn(View view) {

        startActivity(new Intent(Login.this, forgetPassword.class));
    }

    public void createAnAccountBtn(View view) {

        startActivity(new Intent(Login.this, PhoneAuth.class));

    }
}
