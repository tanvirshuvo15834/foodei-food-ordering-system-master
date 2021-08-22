package com.example.foodei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Confrim extends AppCompatActivity {

    Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim);

        but1 = (Button) findViewById(R.id.bo1);


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intC = new Intent(Confrim.this,Home.class);
                startActivity(intC);
            }
        });
    }
}
