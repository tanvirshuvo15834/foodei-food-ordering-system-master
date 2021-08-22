package com.example.foodei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText ue;
    EditText pa;
    Button but1;
    TextView tvsup;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        ue = (EditText) findViewById(R.id.e1);
        pa = (EditText) findViewById(R.id.e2);
        but1 = (Button) findViewById(R.id.b1);
        tvsup = (TextView) findViewById(R.id.tvsup);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if ( mFirebaseUser != null)
                {
                    Toast.makeText(MainActivity.this, "Your are logged in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Home.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Login First!", Toast.LENGTH_SHORT).show();
                }

            }
        };

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ue.getText().toString();
                String pwd = pa.getText().toString();
                if (email.isEmpty()) {
                    ue.setError("Please Enter Email ID");
                    ue.requestFocus();
                }
                else if (pwd.isEmpty()) {
                    pa.setError("Please Enter Your Password");
                    pa.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user=mAuth.getCurrentUser();
                                Intent intToHome = new Intent(MainActivity.this,Home.class);
                                startActivity(intToHome);


                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Login Error, Please Try Again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }


            }

        });

        tvsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignup = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intSignup);
            }

        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
