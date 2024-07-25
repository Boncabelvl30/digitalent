package com.example.praktek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListMahasiswaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        mahasiswaList = databaseHelper.getAllMahasiswa();

        if (mahasiswaList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }

        adapter = new MahasiswaAdapter(mahasiswaList, new MahasiswaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Mahasiswa mahasiswa) {
                showDialog(mahasiswa);
            }
        });
        recyclerView.setAdapter(adapter);

        Button btnInputData = findViewById(R.id.btnInputData);
        btnInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMahasiswaActivity.this, InputMahasiswaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDialog(Mahasiswa mahasiswa) {
        PilihanDialog dialog = new PilihanDialog(mahasiswa);
        dialog.show(getSupportFragmentManager(), "PilihanDialog");
    }
}
