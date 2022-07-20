package com.example.databasescomparison.data.local.source.sqloh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.databasescomparison.data.model.remotenews.Article;
import com.example.databasescomparison.data.model.remotenews.Source;

import java.util.ArrayList;
import java.util.List;

public class SQLOHDatabaseHelper extends SQLiteOpenHelper {

    private static SQLOHDatabaseHelper mInstance;

    private static final String DATABASE_NAME = "sqloh-database";
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

    public static synchronized SQLOHDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SQLOHDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public SQLOHDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NEWS +
                "(" + KEY_ID + "INTEGER PRIMARY KEY, " +
                KEY_AUTHOR + " TEXT, " +
                KEY_CONTENT + " TEXT, " +
                KEY_DESCRIPTION + " TEXT, " +
                KEY_PUBLISHED_AT + " TEXT, " +
                KEY_SOURCE_ID + " TEXT, " +
                KEY_SOURCE_NAME + " TEXT, " +
                KEY_TITLE + " TEXT, " +
                KEY_URL + " TEXT, " +
                KEY_URL_TO_IMAGE + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // look for better upgrade process
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
            onCreate(sqLiteDatabase);
        }
    }

    public void addArticle(Article article) {
        // how does this method work and what does it return
        SQLiteDatabase db = getWritableDatabase();

        // what is the method? - discover
        db.beginTransaction();
        String getRowQuery = "SELECT * FROM " + TABLE_NEWS + " WHERE " + KEY_URL + " = ?";
        Cursor cursor = db.rawQuery(getRowQuery, new String[]{article.getUrl()});

        ContentValues values = new ContentValues();
        putArticleIntoValues(values, article);

        if (cursor == null || !cursor.moveToFirst()) {
            try {
                db.insertOrThrow(TABLE_NEWS, null, values);
                db.setTransactionSuccessful();
            } catch (SQLException e) {
                logError(e);
            } finally {
                db.endTransaction();
            }
        } else {
            updateArticle(article);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public void addArticles(List<Article> articles) {
        for (Article article : articles) {
            addArticle(article);
        }
    }

    public List<Article> getArticles() {
        SQLiteDatabase db = getReadableDatabase();
        List<Article> articles = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NEWS, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    articles.add(
                            new Article(
                                    cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    new Source(cursor.getString(4), cursor.getString(5)),
                                    cursor.getString(6),
                                    cursor.getString(7),
                                    cursor.getString(8)
                            )
                    );
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            logError(e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return articles;
    }

    public int updateArticle(Article article) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        putArticleIntoValues(values, article);

        return db.update(TABLE_NEWS, values, KEY_URL + " = ?", new String[]{(KEY_URL)});
    }

    public void deleteArticle(Article article) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            db.delete(TABLE_NEWS, KEY_URL + " = '" + article.getUrl() + "'", null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            logError(e);
        } finally {
            db.endTransaction();
        }
    }

    public void deleteAllArticles() {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            db.delete(TABLE_NEWS, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            logError(e);
        } finally {
            db.endTransaction();
        }
    }

    private void putArticleIntoValues(ContentValues values, Article article) {
        values.put(KEY_AUTHOR, article.getAuthor());
        values.put(KEY_CONTENT, article.getContent());
        values.put(KEY_DESCRIPTION, article.getDescription());
        values.put(KEY_PUBLISHED_AT, article.getPublishedAt());
        values.put(KEY_SOURCE_ID, article.getSource().getId());
        values.put(KEY_SOURCE_NAME, article.getSource().getId());
        values.put(KEY_TITLE, article.getTitle());
        values.put(KEY_URL, article.getUrl());
        values.put(KEY_URL_TO_IMAGE, article.getUrlToImage());
    }

    private void logError(Exception e) {
        Log.d("SQLOH error", e.getLocalizedMessage());
    }

}
