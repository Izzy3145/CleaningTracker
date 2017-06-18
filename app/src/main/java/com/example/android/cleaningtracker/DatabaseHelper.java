package com.example.android.cleaningtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.cleaningtracker.data.CleaningContract;

import static com.example.android.cleaningtracker.data.CleaningContract.CleaningEntry.TABLE_CLEANING;


/**
 * Created by izzystannett on 18/06/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //essential info
    private static final String DATABASE_NAME = "cleaningDatabase";
    private static final int DATABASE_VERSION = 1;

    //create constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //required override of the onCreate method
    //which is implemented when the database is created for the first time
    //and builds the SQL command needed to build the table

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLEANING_TABLE = "CREATE TABLE " + TABLE_CLEANING +
                "(" +
                CleaningContract.CleaningEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CleaningContract.CleaningEntry.COLUMN_HOUSEMATE + " TEXT NOT NULL," +
                CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DONE + " TEXT NOT NULL, " +
                CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DURATION + " TEXT" +
                ")";

        db.execSQL(CREATE_CLEANING_TABLE);
    }

    //called when the database needs to be upgraded
    //this method will only be called if a database with the same DATABASE_NAME exists
    //but the DATABASE_VERSION is different
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLEANING);
            onCreate(db);
        }
    }
}
