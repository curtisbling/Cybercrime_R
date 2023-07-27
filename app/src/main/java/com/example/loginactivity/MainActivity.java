package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button btnsafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsafe = findViewById(R.id.btnsafe);
        btnsafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();


            if (itemId == R.id.home) {
                finish();
                return true;
            } else if (itemId == R.id.report) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

                finish();
                return true;
            }
            else if(itemId==R.id.settings){
                startActivity(new Intent(MainActivity.this, settingsActivity.class));



            }

            return false;
        });
    }
}
