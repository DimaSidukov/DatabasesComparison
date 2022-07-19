package com.example.databasescomparison.data.local.sqloh;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static SQLiteDatabaseHelper mInstance;

    public static synchronized SQLiteDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SQLiteDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE);
    }
}
