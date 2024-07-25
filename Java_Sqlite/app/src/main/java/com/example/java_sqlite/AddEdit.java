package com.example.java_sqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEdit extends AppCompatActivity {
    EditText txt_id, txt_name, txt_type, txt_episodes, txt_status;
    Button btn_save, btn_cancel;
    String id, name, type, episodes, status;
    DbHelper SQLite = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_name);
        txt_type = findViewById(R.id.txt_type); // Updated
        txt_episodes = findViewById(R.id.txt_episodes); // Updated
        txt_status = findViewById(R.id.txt_status); // Updated
        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        type = getIntent().getStringExtra(MainActivity.TAG_TYPE); // Updated
        episodes = getIntent().getStringExtra(MainActivity.TAG_EPISODES); // Updated
        status = getIntent().getStringExtra(MainActivity.TAG_STATUS); // Updated

        if (id == null || id.isEmpty()) {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_type.setText(type); // Updated
            txt_episodes.setText(episodes); // Updated
            txt_status.setText(status); // Updated
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes the current activity and returns to the previous one
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().isEmpty()) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Submit", e.toString());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_type.setText(null); // Updated
        txt_episodes.setText(null); // Updated
        txt_status.setText(null); // Updated
    }

    private void save() {
        if (txt_name.getText().toString().isEmpty() || txt_type.getText().toString().isEmpty() || txt_episodes.getText().toString().isEmpty() || txt_status.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please input all fields...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_name.getText().toString().trim(), txt_type.getText().toString().trim(), txt_episodes.getText().toString().trim(), txt_status.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (txt_name.getText().toString().isEmpty() || txt_type.getText().toString().isEmpty() || txt_episodes.getText().toString().isEmpty() || txt_status.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please input all fields...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_name.getText().toString().trim(), txt_type.getText().toString().trim(), txt_episodes.getText().toString().trim(), txt_status.getText().toString().trim());
            blank();
            finish();
        }
    }

}