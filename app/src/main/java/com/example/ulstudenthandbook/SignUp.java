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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText emailAddressText, passwordText, passwordText2;
    Button registerBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailAddressText = findViewById(R.id.EmailEditText);
        passwordText = findViewById(R.id.PasswordEditText);
        passwordText2 = findViewById(R.id.PasswordEditText2);
        registerBtn = findViewById(R.id.Register_Button);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
    }

    public void RegisterPressed(View view) {
        String email = emailAddressText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String passwordCheck = passwordText2.getText().toString().trim();

        //Checking user input
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

        if(password.length() < 6 || password.length() > 16)
        {
            passwordText.setError("Password must be 6-16 characters long");
            return;
        }

        if(passwordCheck.isEmpty())
        {
            passwordText2.setError("Confirm your password");
            return;
        }

        if(password.equals(passwordCheck) == false)
        {
            passwordText2.setError("Passwords must match");
        }

        progressBar.setVisibility(View.VISIBLE);

        //Registering user in database
        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignUp.this, "Registration was successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else
                {
                    Toast.makeText(SignUp.this, "Error! ." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void LogInPressed(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void OfflineModePressed(View view) {
        startActivity(new Intent(getApplicationContext(), Modules.class));
    }
}