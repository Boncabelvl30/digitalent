package com.example.praktekdummy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListMahasiswaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mahasiswaList = new ArrayList<>();
        // Add sample data
        mahasiswaList.add(new Mahasiswa("Aradhana Luqman WS", "3120600059", "Teknik Informatika"));
        mahasiswaList.add(new Mahasiswa("Puan Maharani", "654321", "Information Systems"));

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
