package com.example.foodei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    TextView t1;
    Button bt0;
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;

    String c = "";
    Double p = 0.00;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        bt0 = (Button) findViewById(R.id.b1);
        bt1 = (Button) findViewById(R.id.bu1);
        bt2 = (Button) findViewById(R.id.bu2);
        bt3 = (Button) findViewById(R.id.bu3);
        bt4 = (Button) findViewById(R.id.bu4);


        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intSignup = new Intent(Home.this,MainActivity.class);
                startActivity(intSignup);
            }

        });


    }
    public void add_to_list(View view)
    {
        if(view == findViewById(R.id.bu1) )
        {
            c = c+"Pizza"+"\n";
            p = p+1200;
        }
        if(view == findViewById(R.id.bu2) )
        {
            c = c+"Burger"+"\n";
            p = p+200;
        }
        if(view == findViewById(R.id.bu3) )
        {
            c = c+"Pastry"+"\n";
            p = p+80;
        }
    }

    public void PlaceOrder(View view)
    {
        Intent i = new Intent(Home.this, Order.class);
        Bundle bundle = new Bundle();
        bundle.putString("choice", c);
        bundle.putDouble("bdt", p);
        i.putExtras(bundle);
        startActivity(i);
        c = "";

    }

}
