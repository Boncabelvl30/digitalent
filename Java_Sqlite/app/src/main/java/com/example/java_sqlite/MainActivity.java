package com.example.java_sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.java_sqlite.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    Adapter adapter;
    List<Data> dataList = new ArrayList<Data>();
    DbHelper SQLite = new DbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_TYPE = "type";
    public static final String TAG_EPISODES = "episodes";
    public static final String TAG_STATUS = "status";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        SQLite  = new DbHelper(getApplicationContext());



        listView = findViewById(R.id.listView);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEdit.class);
                startActivity(intent);
            }
        });
        adapter = new Adapter(MainActivity.this, dataList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = dataList.get(position).getId();
                final String name = dataList.get(position).getName();
                final String type = dataList.get(position).getType();
                final String episodes = dataList.get(position).getEpisode();
                final String status = dataList.get(position).getStatus();

                final CharSequence[] dialogitem = {"Detail", "Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 1:
                                Intent intentEdit = new Intent(MainActivity.this, AddEdit.class);
                                intentEdit.putExtra(TAG_ID, idx);
                                intentEdit.putExtra(TAG_NAME, name);
                                intentEdit.putExtra(TAG_TYPE, type);
                                intentEdit.putExtra(TAG_EPISODES, episodes);
                                intentEdit.putExtra(TAG_STATUS, status);
                                startActivity(intentEdit);
                                break;
                            case 2:
                                SQLite.delete(Integer.parseInt(idx));
                                dataList.clear();
                                getAllData();
                                break;
                            case 0:
                                Intent intentDetail = new Intent(MainActivity.this, DetailedActivity.class);
                                intentDetail.putExtra("ID", idx);
                                intentDetail.putExtra("NAME", name);
                                intentDetail.putExtra("TYPE", type);
                                intentDetail.putExtra("EPISODES", episodes);
                                intentDetail.putExtra("STATUS", status);
//                                intentDetail.putExtra(TAG_ID, idx);
//                                intentDetail.putExtra(TAG_NAME, name);
//                                intentDetail.putExtra(TAG_TYPE, type);
//                                intentDetail.putExtra(TAG_EPISODES, episodes);
//                                intentDetail.putExtra(TAG_STATUS, status);
                                startActivity(intentDetail);
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });

    getAllData();



    }

    private void getAllData(){
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++){
            String id = row.get(i).get(TAG_ID);
            String name = row.get(i).get(TAG_NAME);
            String type = row.get(i).get(TAG_TYPE);
            String episodes = row.get(i).get(TAG_EPISODES);
            String status = row.get(i).get(TAG_STATUS);

            Data data = new Data();

            data.setId(id);
            data.setName(name);
            data.setType(type);
            data.setEpisode(episodes);
            data.setStatus(status);

            dataList.add(data);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataList.clear();
        getAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}