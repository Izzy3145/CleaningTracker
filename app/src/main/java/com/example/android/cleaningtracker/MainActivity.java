package com.example.android.cleaningtracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.cleaningtracker.data.CleaningContract;

import static android.R.color.white;
import static com.example.android.cleaningtracker.data.CleaningContract.CleaningEntry.TABLE_CLEANING;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DatabaseHelper(this);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertJob();
                displayDatabase();
            }
        });
    }

    private void insertJob() {
        //create a SQL database in writeable mode, ready to take the ContentValues
        mDatabase = mDbHelper.getWritableDatabase();

        //Create new set of values to put into database
        ContentValues values = new ContentValues();
        values.put(CleaningContract.CleaningEntry.COLUMN_HOUSEMATE, "Izzy");
        values.put(CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DONE, "Mopping");
        values.put(CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DURATION, CleaningContract.CleaningEntry.THIRTY);

        //insert new row
        long newRowId = mDatabase.insert(TABLE_CLEANING, null, values);

        Log.v("MainActivity", "Row ID: " + newRowId);
    }

    //method used to display cursor object in TextView on main screen
    private Cursor displayDatabase() {

        //create a readable database
        mDatabase = mDbHelper.getReadableDatabase();

        //define query inputs
        String[] projection = {
                CleaningContract.CleaningEntry._ID,
                CleaningContract.CleaningEntry.COLUMN_HOUSEMATE,
                CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DONE,
                CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DURATION
        };

        //get a cursor object that contains all table, as defined in projection
        Cursor cursor = mDatabase.query(CleaningContract.CleaningEntry.TABLE_CLEANING, projection, null, null, null, null, null);

        //create text view object in which to display the table
        TextView tableTextView = (TextView) findViewById(R.id.table_text_view);

        //populate table
        try {
            tableTextView.setText(R.string.table_header);
            tableTextView.append(CleaningContract.CleaningEntry._ID + "-" +
                    CleaningContract.CleaningEntry.COLUMN_HOUSEMATE + "-" +
                    CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DONE + "-" +
                    CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DURATION + "\n");

            //get index of every column
            int idColumnIndex = cursor.getColumnIndex(CleaningContract.CleaningEntry._ID);
            int housemateColumnIndex = cursor.getColumnIndex(CleaningContract.CleaningEntry.COLUMN_HOUSEMATE);
            int activityColumnIndex = cursor.getColumnIndex(CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DONE);
            int durationColumnIndex = cursor.getColumnIndex(CleaningContract.CleaningEntry.COLUMN_ACTIVITY_DURATION);

            //iterate through all rows in the cursor
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentHousemate = cursor.getString(housemateColumnIndex);
                String currentActivity = cursor.getString(activityColumnIndex);
                int currentDuration = cursor.getInt(durationColumnIndex);

                //display all items in the view
                tableTextView.append("\n" + currentID + "-" +
                        currentHousemate + "-" +
                        currentActivity + "-" +
                        currentDuration);
            }
        } finally {
            //close cursor to save memory
            cursor.close();
        }

        return cursor;
    }

}
