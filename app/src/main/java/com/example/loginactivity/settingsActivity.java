package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class settingsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();


            if (itemId == R.id.settings) {
                finish();
                return true;

            } else if (itemId == R.id.home) {
                startActivity(new Intent(settingsActivity.this, MainActivity.class));

                finish();
                return true;
            }
            else if(itemId==R.id.report){
                startActivity(new Intent(settingsActivity.this, LoginActivity.class));



            }
// Return false if you don't handle the selection
            return false;
        });
    }
}