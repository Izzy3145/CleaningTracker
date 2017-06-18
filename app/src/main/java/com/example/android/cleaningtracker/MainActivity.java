package com.example.android.cleaningtracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.cleaningtracker.data.CleaningContract;

import static com.example.android.cleaningtracker.data.CleaningContract.CleaningEntry.TABLE_CLEANING;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DatabaseHelper(this);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPet();
            }
        });
    }

    private void insertPet(){
        //create a SQL database in writeable mode, ready to take the ContentValues
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Create new set of values to put into database
        ContentValues values = new ContentValues();
        values.put(CleaningContract.CleaningEntry.COLUMN_HOUSEMATE, "Izzy");
        values.put(CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DONE, "Swimming");
        values.put(CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DURATION, "45 minutes");

        //insert new row
        long newRowId = db.insert(TABLE_CLEANING, null, values);

        Log.v("MainActivity", "Row ID: " + newRowId);
    }

}
