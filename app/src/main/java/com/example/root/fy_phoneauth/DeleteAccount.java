package com.example.root.fy_phoneauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteAccount extends AppCompatActivity {

    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        password = findViewById(R.id.passdwDeleteAccount);
    }

    public void deleteAccount(View view) {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential;
        System.out.println("Email " + user.getEmail());
        credential = EmailAuthProvider.getCredential(user.getEmail(), password.getText().toString());

        System.out.println(credential);

        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Calling delete to remove the user and wait for a result.
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Ok, user remove
                                    Toast.makeText(DeleteAccount.this, "Account deleted...!", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(DeleteAccount.this, Login.class));
                                } else {
                                    Toast.makeText(DeleteAccount.this, "Account not deleted...!", Toast.LENGTH_LONG).show();
                                    //Handle the exception
                                    task.getException();
                                }
                            }
                        });
                    }
                });


    }
}
