package com.example.databasescomparison.data.local.source.sqloh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.databasescomparison.data.model.remotesensors.Sensor;

import java.util.ArrayList;
import java.util.List;

public class SQLOHDatabaseHelper extends SQLiteOpenHelper {

    private static SQLOHDatabaseHelper mInstance;

    private static final String DATABASE_NAME = "sqloh-database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SENSORS = "sensors";

    private static final String KEY_ID = "id";
    private static final String KEY_BROKER_NAME = "broker_name";
    private static final String KEY_ABOVE_SEA_LEVEL = "above_sea_level";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_RAW_ID = "raw_id";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_HEIGHT_ABOVE_GROUND = "height_above_ground";
    private static final String KEY_SENSOR_NAME = "sensor_name";
    private static final String KEY_THIRD_PARTY = "third_party";

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
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SENSORS +
                "(" + KEY_ID + "INTEGER PRIMARY KEY, " +
                KEY_BROKER_NAME + " TEXT, " +
                KEY_ABOVE_SEA_LEVEL + " DOUBLE, " +
                KEY_LOCATION + " TEXT, " +
                KEY_RAW_ID + " TEXT, " +
                KEY_LATITUDE + " DOUBLE, " +
                KEY_LONGITUDE + " DOUBLE, " +
                KEY_HEIGHT_ABOVE_GROUND + " DOUBLE, " +
                KEY_SENSOR_NAME + " TEXT, " +
                KEY_THIRD_PARTY + " BOOLEAN)";

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
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SENSORS);
            onCreate(sqLiteDatabase);
        }
    }

    public void addSensor(Sensor sensor) {
        // how does this method work and what does it return
        SQLiteDatabase db = getWritableDatabase();

        // what is the method? - discover
        db.beginTransaction();
        String getRowQuery = "SELECT * FROM " + TABLE_SENSORS + " WHERE " + KEY_LOCATION + " = ?;";
        Cursor cursor = db.rawQuery(getRowQuery, new String[]{sensor.getLocation()});

        ContentValues values = new ContentValues();
        putSensorIntoValues(values, sensor);

        if (cursor == null || !cursor.moveToFirst()) {
            try {
                db.insertOrThrow(TABLE_SENSORS, null, values);
                db.setTransactionSuccessful();
            } catch (SQLException e) {
                logError(e);
            } finally {
                db.endTransaction();
            }
        } else {
            updateSensor(sensor);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public void addSensors(List<Sensor> sensors) {
        for (Sensor sensor : sensors) {
            addSensor(sensor);
        }
    }

    public List<Sensor> getSensors() {
        SQLiteDatabase db = getReadableDatabase();
        List<Sensor> sensors = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SENSORS, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    sensors.add(
                            new Sensor(
                                    cursor.getString(0),
                                    cursor.getDouble(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    cursor.getDouble(4),
                                    cursor.getDouble(5),
                                    cursor.getDouble(6),
                                    cursor.getString(7),
                                    cursor.getExtras().getBoolean(KEY_THIRD_PARTY, false)
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
        return sensors;
    }

    public int updateSensor(Sensor sensor) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        putSensorIntoValues(values, sensor);

        return db.update(TABLE_SENSORS, values, KEY_LOCATION + " = ?", new String[]{(KEY_LOCATION)});
    }

    public void deleteSensor(Sensor sensor) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            db.delete(TABLE_SENSORS, KEY_LOCATION + " = '" + sensor.getLocation() + "'", null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            logError(e);
        } finally {
            db.endTransaction();
        }
    }

    public void deleteAllSensors() {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            db.delete(TABLE_SENSORS, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            logError(e);
        } finally {
            db.endTransaction();
        }
    }

    private void putSensorIntoValues(ContentValues values, Sensor sensor) {
        values.put(KEY_BROKER_NAME, sensor.getBrokerName());
        values.put(KEY_ABOVE_SEA_LEVEL, sensor.getAboveSeaLevel());
        values.put(KEY_LOCATION, sensor.getLocation());
        values.put(KEY_RAW_ID, sensor.getRawID());
        values.put(KEY_LATITUDE, sensor.getLatitude());
        values.put(KEY_LONGITUDE, sensor.getLongitude());
        values.put(KEY_HEIGHT_ABOVE_GROUND, sensor.getHeightAboveGround());
        values.put(KEY_SENSOR_NAME, sensor.getSensorName());
        values.put(KEY_THIRD_PARTY, sensor.getThirdParty());
    }

    private void logError(Exception e) {
        Log.d("SQLOH error", e.getLocalizedMessage());
    }

}
