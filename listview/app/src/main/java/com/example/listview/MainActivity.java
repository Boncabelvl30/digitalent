package com.example.listview;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private ListView lvItem;
    private String[] namanegara = new String[] {
            "Indonesia", "Malaysia", "Singapore",
            "Italia", "Inggris", "Belanda",
            "Argentina", "Chile", "Mesir", "Uganda"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ListView Sederhana");
        } else {
            Log.e("MainActivity", "Action bar is null");
        }

        lvItem = findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, namanegara);

        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Memilih : " + namanegara[position], Toast.LENGTH_LONG).show();
            }
        });
    }
}
