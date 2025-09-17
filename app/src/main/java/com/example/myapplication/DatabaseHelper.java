package com.example.panchakarma;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "panchakarma.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE patients(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age TEXT, therapy TEXT)");
        db.execSQL("CREATE TABLE schedule(id INTEGER PRIMARY KEY AUTOINCREMENT, patientId INTEGER, date TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS patients");
        db.execSQL("DROP TABLE IF EXISTS schedule");
        onCreate(db);
    }

    public boolean addPatient(String name, String age, String therapy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);
        cv.put("therapy", therapy);
        long result = db.insert("patients", null, cv);
        return result != -1;
    }
}
