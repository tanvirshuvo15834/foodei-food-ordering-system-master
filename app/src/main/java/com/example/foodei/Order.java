package com.example.foodei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Order extends AppCompatActivity {

    TextView lv;
    TextView pv;
    Button but1;
    Button but2;
    String list;
    Double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        lv = (TextView) findViewById(R.id.t2);
        pv = (TextView) findViewById(R.id.t3);
        but1 = (Button)  findViewById(R.id.b1);
        but2 = (Button)  findViewById(R.id.b2);


        Bundle bundle = getIntent().getExtras();
        list = bundle.getString("choice");
        price= bundle.getDouble("bdt");


        lv.setText(list);
        pv.setText(price.toString());


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intO = new Intent(Order.this,Confrim.class);
                startActivity(intO);
            }
        });


        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intC = new Intent(Order.this,Home.class);
                startActivity(intC);
            }
        });
    }
}
