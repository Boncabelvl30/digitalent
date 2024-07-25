package com.example.java_sqlite;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);



        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Your Desired Title Here");
        setSupportActionBar(toolbar);


        TextView detailId = findViewById(R.id.detail_id);
        TextView detailName = findViewById(R.id.detail_name);
        TextView detailType = findViewById(R.id.detail_type);
        TextView detailEpisodes = findViewById(R.id.detail_episodes);
        TextView detailStatus = findViewById(R.id.detail_status);

        // Retrieve data from intent
        String id = getIntent().getStringExtra("ID");
        String name = getIntent().getStringExtra("NAME");
        String type = getIntent().getStringExtra("TYPE");
        String episodes = getIntent().getStringExtra("EPISODES");
        String status = getIntent().getStringExtra("STATUS");

        // Set data to TextViews
        detailId.setText(id != null ? "ID: " + id : "ID: Not available");
        detailName.setText(name != null ? "Name: " + name : "Name: Not available");
        detailType.setText(type != null ? "Type: " + type : "Type: Not available");
        detailEpisodes.setText(episodes != null ? "Episodes: " + episodes : "Episodes: Not available");
        detailStatus.setText(status != null ? "Status: " + status : "Status: Not available");
    }
}