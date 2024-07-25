package com.example.praktek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class InputMahasiswaActivity extends AppCompatActivity {
    private EditText namaEditText;
    private EditText nimEditText;
    private EditText jurusanEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mahasiswa);

        databaseHelper = new DatabaseHelper(this);

        namaEditText = findViewById(R.id.namaEditText);
        nimEditText = findViewById(R.id.nimEditText);
        jurusanEditText = findViewById(R.id.jurusanEditText);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mahasiswa mahasiswa = new Mahasiswa(
                        namaEditText.getText().toString(),
                        nimEditText.getText().toString(),
                        jurusanEditText.getText().toString()
                );
                databaseHelper.addMahasiswa(mahasiswa);
                finish();
            }
        });
    }
}
