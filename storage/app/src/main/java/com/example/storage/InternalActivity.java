package com.example.storage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILE_NAME = "namafile.txt";
    Button buatFile, ubahFile, bacaFile, deleteFile;
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        buatFile = findViewById(R.id.buttonBuatFile);
        ubahFile = findViewById(R.id.buttonUbahFile);
        bacaFile = findViewById(R.id.buttonBacaFile);
        deleteFile = findViewById(R.id.buttonHapusFile);
        textBaca = findViewById(R.id.textBaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        deleteFile.setOnClickListener(this);
    }

    void buatFile() {
        String isiFile = "Coba Isi Data File Text";
        File file = new File(getFilesDir(), FILE_NAME);

        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            file.createNewFile();
            outputStream.write(isiFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void ubahFile() {
        String isiFile = "Data Baru Untuk Isi File";
        File file = new File(getFilesDir(), FILE_NAME);

        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            outputStream.write(isiFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void bacaFile() {
        File file = new File(getFilesDir(), FILE_NAME);

        if (file.exists()) {
            StringBuilder text = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            textBaca.setText(text.toString());
        } else {
            textBaca.setText("File tidak ditemukan");
        }
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonBuatFile) {
            buatFile();
        } else if (id == R.id.buttonUbahFile) {
            ubahFile();
        } else if (id == R.id.buttonBacaFile) {
            bacaFile();
        } else if (id == R.id.buttonHapusFile) {
            hapusFile();
        }
    }
}
