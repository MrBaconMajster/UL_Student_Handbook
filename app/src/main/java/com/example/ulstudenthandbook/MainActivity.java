package com.example.ulstudenthandbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailAddressText, passwordText;
    Button logInBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAddressText = findViewById(R.id.EmailEditText);
        passwordText = findViewById(R.id.PasswordEditText);
        logInBtn = findViewById(R.id.LogIn_Button);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();

    }

    public void LoginPressed(View view) {

        String email = emailAddressText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
        {
            emailAddressText.setError("Valid Email is required");
            return;
        }

        if(password.isEmpty())
        {
            passwordText.setError("Password is required");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //Signs the user in
        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(getApplicationContext(), Map.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error! ." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void SignupPressed(View view) {
        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }

    public void OfflineModePressed(View view) {
    }
}