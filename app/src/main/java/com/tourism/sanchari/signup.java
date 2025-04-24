package com.tourism.sanchari;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(signup.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText Email = findViewById(R.id.email);
        EditText Password = findViewById(R.id.password);
        EditText Name = findViewById(R.id.name);
        EditText CnfPassword = findViewById(R.id.cnfpassword);
        Button loginSubmit = findViewById(R.id.signupSubmit);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        loginSubmit.setOnClickListener(view -> {
            String email = Email.getText().toString().trim();
            String password = Password.getText().toString().trim();
            String confirmPassword = CnfPassword.getText().toString().trim();
            String name = Name.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(signup.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(signup.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(signup.this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                Toast.makeText(signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(signup.this, task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Save user info to Firebase Realtime Database
                            String userId = user.getUid();
                            //User newUser = new User(name, email); // You need to create this class
                            //database.getReference().child("Users").child(userId).setValue(newUser);
                            String EMAIL=Email.getText().toString();
                            String NAME=Name.getText().toString();
                            String PASS=Password.getText().toString();
                            String CPASS=CnfPassword.getText().toString();

                            Toast.makeText(signup.this, "Account created!", Toast.LENGTH_SHORT).show();
                            AddSignUp su=new AddSignUp();
                            su.insertUser(NAME,EMAIL,PASS,CPASS);

                            startActivity(new Intent(signup.this, MainActivity.class));
                            finish();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signup.this, "Authentication failed: " +
                                    task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}
