package com.example.java_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    Button btnLihatData, btnInputData, btnInformasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnLihatData = findViewById(R.id.btnLihatData);
        btnInputData = findViewById(R.id.btnInputData);
        btnInformasi = findViewById(R.id.btnInformasi);

        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, AddEdit.class);
                startActivity(intent);
            }
        });

        btnInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(Dashboard.this);
                Data latestData = dbHelper.getLatestInput();
                if (latestData != null) {
                    Intent intent = new Intent(Dashboard.this, DetailedActivity.class);
                    intent.putExtra("ID", latestData.getId());
                    intent.putExtra("NAME", latestData.getName());
                    intent.putExtra("TYPE", latestData.getType());
                    intent.putExtra("EPISODES", latestData.getEpisode());
                    intent.putExtra("STATUS", latestData.getStatus());
                    startActivity(intent);
                }
            }
        });
    }

    private String getFirstIdFromDatabase() {
        // Implement database access to fetch the first ID
        // This is a placeholder, replace with your actual database access code
        return "1"; // Assuming "1" is the first ID
    }
}