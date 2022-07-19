package com.example.databasescomparison.data.local.sqloh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpenHelperDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "newsSqloh";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NEWS = "news";

    private static final String KEY_ID = "id";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PUBLISHED_AT = "publishedAt";
    private static final String KEY_SOURCE_ID = "source";
    private static final String KEY_SOURCE_NAME = "name";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String KEY_URL_TO_IMAGE = "urlToImage";

    public SQLiteOpenHelperDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NEWS +
                "(" + KEY_ID + "INTEGER PRIMARY KEY," +
                KEY_AUTHOR + "TEXT," +
                KEY_CONTENT + "TEXT," +
                KEY_DESCRIPTION + "TEXT," +
                KEY_PUBLISHED_AT + "TEXT," +
                KEY_SOURCE_ID + "TEXT," +
                KEY_SOURCE_NAME + "TEXT," +
                KEY_TITLE + "TEXT," +
                KEY_URL + "TEXT," +
                KEY_URL_TO_IMAGE + "TEXT" +
                ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // look for better upgrade process
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
            onCreate(sqLiteDatabase);
        }
    }
}
