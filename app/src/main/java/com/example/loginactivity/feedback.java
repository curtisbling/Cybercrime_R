package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class feedback extends AppCompatActivity {
    TextView displaydate, displaytext;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        confirm=findViewById(R.id.btnConfirm);

        // Retrieve the date and text values from the received Intent's extras
        String currentDateAndTime = getIntent().getStringExtra("currentDateAndTime");
        String textToDisplay = getIntent().getStringExtra("textToDisplay");

        // Find the TextViews in the layout
        TextView dateTextView = findViewById(R.id.displayDate);
        TextView textTextView = findViewById(R.id.displaytext);

        // Set the date and text values to the TextViews
        dateTextView.setText(currentDateAndTime);
        textTextView.setText(textToDisplay);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(feedback.this, MainActivity.class));



            }
        });




    }
}