package com.example.java_sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
        this.inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row, parent, false);
        }

        TextView idTextView = convertView.findViewById(R.id.id);
        TextView nameTextView = convertView.findViewById(R.id.name);
        TextView typeTextView = convertView.findViewById(R.id.type);
        TextView episodeTextView = convertView.findViewById(R.id.episodes);
        TextView statusTextView = convertView.findViewById(R.id.status);


        Data item = items.get(position);

        // Assuming Data class has these fields. Adjust according to your actual Data class.
        idTextView.setText(String.valueOf(item.getId()));
        nameTextView.setText(item.getName());
        episodeTextView.setText(item.getEpisode());
        typeTextView.setText(item.getType());
        statusTextView.setText(item.getStatus());



        return convertView;
    }
}
