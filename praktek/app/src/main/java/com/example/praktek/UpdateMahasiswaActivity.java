package com.example.praktek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateMahasiswaActivity extends AppCompatActivity {
    private EditText namaEditText;
    private EditText nimEditText;
    private EditText jurusanEditText;
    private Mahasiswa mahasiswa;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        databaseHelper = new DatabaseHelper(this);

        mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("mahasiswa");

        namaEditText = findViewById(R.id.namaEditText);
        nimEditText = findViewById(R.id.nimEditText);
        jurusanEditText = findViewById(R.id.jurusanEditText);

        namaEditText.setText(mahasiswa.getNama());
        nimEditText.setText(mahasiswa.getNim());
        jurusanEditText.setText(mahasiswa.getJurusan());

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahasiswa.setNama(namaEditText.getText().toString());
                mahasiswa.setNim(nimEditText.getText().toString());
                mahasiswa.setJurusan(jurusanEditText.getText().toString());
                databaseHelper.updateMahasiswa(mahasiswa);
                finish();
            }
        });
    }
}
