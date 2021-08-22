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

public class SignupActivity extends AppCompatActivity {

    EditText ue;
    EditText pa;
    Button but1;
    TextView tvsignin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        ue = (EditText) findViewById(R.id.e1);
        pa = (EditText) findViewById(R.id.e2);
        but1 = (Button) findViewById(R.id.b1);
        tvsignin=(TextView) findViewById(R.id.tvsin) ;

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
                    Toast.makeText(SignupActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "SignUp Unsuccessful, Please Try Again. ", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                FirebaseUser user=mAuth.getCurrentUser();
                                startActivity(new Intent(SignupActivity.this, Home.class));
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                    Intent intback = new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(intback);
                }
            }
        });

        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

}
