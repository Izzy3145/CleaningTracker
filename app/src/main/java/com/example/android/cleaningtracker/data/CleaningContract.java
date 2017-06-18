package com.example.android.cleaningtracker.data;

import android.provider.BaseColumns;

/**
 * Created by izzystannett on 18/06/2017.
 */

public final class CleaningContract {

    public static final class CleaningEntry implements BaseColumns {

        //Table name
        public static final String TABLE_CLEANING = "cleaning";

        //column heading constants
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HOUSEMATE = "name";
        public static final String COLUMN_ACTIVITY_DONE = "activity";
        public static final String COLUMN_ACTIVITY_DURATION = "duration";

        //set up possible constants for feel good rating
        //public static final int ONE_STAR = 1;
        //public static final int TWO_STAR = 2;
        //public static final int THREE_STAR = 3;
    }
}
