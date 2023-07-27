package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class reportcrime extends AppCompatActivity {
    Button report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportcrime);
        report=findViewById(R.id.btnReport);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(reportcrime.this, "Thank you for contribution to create a Safer" +
                        " Digital Future #STAYsafeONLINE", Toast.LENGTH_LONG).show();

                // Get current date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale.getDefault());
                String currentDateAndTime = dateFormat.format(new Date());

                // Text to display
                String textToDisplay = "CARTEGORY: Cross Site Scripting.";

                // Create an Intent to navigate to the destination activity
                Intent intent = new Intent(reportcrime.this, feedback.class);

                // Pass the date and text values as extras
                intent.putExtra("currentDateAndTime", currentDateAndTime);
                intent.putExtra("textToDisplay", textToDisplay);

                // Start the ReportActivity
                startActivity(intent);


            }
        });





    }
}