package com.example.praktekdummy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class InputMahasiswaActivity extends AppCompatActivity {
    private EditText namaEditText;
    private EditText nimEditText;
    private EditText jurusanEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mahasiswa);

        namaEditText = findViewById(R.id.namaEditText);
        nimEditText = findViewById(R.id.nimEditText);
        jurusanEditText = findViewById(R.id.jurusanEditText);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simpan data mahasiswa baru
                finish();
            }
        });
    }
}
