package com.example.java_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3; // Step 1: Increment version
    static final String DATABASE_NAME = "data.db";
    public static final String TABLE_NAME = "data";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE = "type"; // New field
    public static final String KEY_EPISODES = "episodes"; // New field
    public static final String KEY_STATUS = "status"; // New field

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT NOT NULL, " +
                KEY_TYPE + " TEXT NOT NULL, " + // Step 2: Add new fields
                KEY_EPISODES + " TEXT NOT NULL, " +
                KEY_STATUS + " TEXT NOT NULL " +
                " );";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put(KEY_ID, cursor.getString(0));
                map.put(KEY_NAME, cursor.getString(1));
                map.put(KEY_TYPE, cursor.getString(2)); // Adjusted for new fields
                map.put(KEY_EPISODES, cursor.getString(3));
                map.put(KEY_STATUS, cursor.getString(4));
                wordList.add(map);
            } while (cursor.moveToNext());
            Log.e("select sqlite ", "" + wordList);
        }
        cursor.close();
        db.close();
        return wordList;
    }

    public void insert(String name, String type, String episodes, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_NAME + " (name, type, episodes, status) " +
                "VALUES ('" + name + "', '" + type + "', '" + episodes + "', '" + status + "')";
        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String name, String type, String episodes, String status){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME + " SET " +
                KEY_NAME + " = '" + name + "', " +
                KEY_TYPE + " = '" + type + "', " +
                KEY_EPISODES + " = '" + episodes + "', " +
                KEY_STATUS + " = '" + status + "' " +
                "WHERE " + KEY_ID + " = '" + id + "'";

        Log.e("update sqlite", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + KEY_ID + "='" + id + "'";
        Log.e("delete sqlite", deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Data getLatestInput() {
        SQLiteDatabase db = this.getReadableDatabase();
        Data latestData = null;

        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            latestData = new Data();
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int typeIndex = cursor.getColumnIndex(KEY_TYPE);
            int episodesIndex = cursor.getColumnIndex(KEY_EPISODES);
            int statusIndex = cursor.getColumnIndex(KEY_STATUS);

            if (idIndex >= 0) latestData.setId(cursor.getString(idIndex));
            if (nameIndex >= 0) latestData.setName(cursor.getString(nameIndex));
            if (typeIndex >= 0) latestData.setType(cursor.getString(typeIndex));
            if (episodesIndex >= 0) latestData.setEpisode(cursor.getString(episodesIndex));
            if (statusIndex >= 0) latestData.setStatus(cursor.getString(statusIndex));
        }
        cursor.close();
        db.close();
        return latestData;
    }
}