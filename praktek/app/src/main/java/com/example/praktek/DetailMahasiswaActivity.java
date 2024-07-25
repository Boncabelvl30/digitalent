package com.example.praktek;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailMahasiswaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("mahasiswa");

        TextView namaTextView = findViewById(R.id.namaTextView);
        TextView nimTextView = findViewById(R.id.nimTextView);
        TextView jurusanTextView = findViewById(R.id.jurusanTextView);

        namaTextView.setText(mahasiswa.getNama());
        nimTextView.setText(mahasiswa.getNim());
        jurusanTextView.setText(mahasiswa.getJurusan());
    }
}
