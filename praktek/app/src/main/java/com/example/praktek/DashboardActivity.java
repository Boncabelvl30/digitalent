package com.example.praktek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btnLihatData = findViewById(R.id.btnLihatData);
        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ListMahasiswaActivity.class);
                startActivity(intent);
            }
        });

        Button btnInformasi = findViewById(R.id.btnInformasi);
        btnInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, InformasiActivity.class);
                startActivity(intent);
            }
        });
    }
}
