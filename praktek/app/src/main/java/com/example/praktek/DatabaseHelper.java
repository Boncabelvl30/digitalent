package com.example.praktek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "praktek.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MAHASISWA = "mahasiswa";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_NIM = "nim";
    private static final String COLUMN_JURUSAN = "jurusan";
    private static final String TAG = "DatabaseHelper";
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        copyDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tidak perlu membuat tabel di sini karena database sudah ada
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Lakukan upgrade database jika diperlukan
    }

    private void copyDatabase() {
        File dbFile = mContext.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                // Pastikan direktori database ada
                File dbDir = new File(dbFile.getParent());
                if (!dbDir.exists()) {
                    dbDir.mkdirs();
                }

                // Copy database dari /sdcard ke internal storage
                File sdcardDb = new File(Environment.getExternalStorageDirectory(), DATABASE_NAME);
                if (sdcardDb.exists()) {
                    FileInputStream inputStream = new FileInputStream(sdcardDb);
                    FileOutputStream outputStream = new FileOutputStream(dbFile);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }

                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                    Log.d(TAG, "Database copied successfully.");
                } else {
                    Log.e(TAG, "Database file not found on /sdcard.");
                }

            } catch (IOException e) {
                Log.e(TAG, "Error copying database", e);
            }
        } else {
            Log.d(TAG, "Database already exists.");
        }
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_NIM, mahasiswa.getNim());
        values.put(COLUMN_JURUSAN, mahasiswa.getJurusan());
        db.insert(TABLE_MAHASISWA, null, values);
        db.close();
    }

    public ArrayList<Mahasiswa> getAllMahasiswa() {
        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MAHASISWA;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                mahasiswa.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_NAMA)));
                mahasiswa.setNim(cursor.getString(cursor.getColumnIndex(COLUMN_NIM)));
                mahasiswa.setJurusan(cursor.getString(cursor.getColumnIndex(COLUMN_JURUSAN)));
                mahasiswaList.add(mahasiswa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return mahasiswaList;
    }

    public int updateMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_NIM, mahasiswa.getNim());
        values.put(COLUMN_JURUSAN, mahasiswa.getJurusan());

        return db.update(TABLE_MAHASISWA, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(mahasiswa.getId())});
    }

    public void deleteMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA, COLUMN_ID + " = ?",
                new String[]{String.valueOf(mahasiswa.getId())});
        db.close();
    }
}
