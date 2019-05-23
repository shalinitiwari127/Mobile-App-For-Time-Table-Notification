package com.example.vision.assignment_timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DBadapter {
    static final String KEY_DAYS="days";
    static final String KEY_PERIOD1="p1";
    static final String KEY_PERIOD2="p2";
    static final String KEY_PERIOD3="p3";
    static final String KEY_PERIOD4="p4";
    static final String KEY_PERIOD5="p5";
    static final String KEY_PERIOD6="p6";
    static final String DATABASE_NAME="Assignment";
    static final String DATABASE_TABLE="timetable";
    static final String DATABASE_VERSION="1";

    static final String DATABASE_CREATE="create table timetable (days text not null,p1 text ,p2 text not null,p3 text not null,p4 text not null,p5 text not null,p6 text not null )";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBadapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, Integer.parseInt(DATABASE_VERSION));
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "upgrading database from version " + oldVersion + "to" + newVersion + " ,which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    public DBadapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        DBHelper.close();
    }
    public long insertTimetable(String days, String p1,String p2,String p3,String p4,String p5,String p6) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DAYS, days);
        initialValues.put(KEY_PERIOD1,p1);
        initialValues.put(KEY_PERIOD2,p2);
        initialValues.put(KEY_PERIOD3,p3);
        initialValues.put(KEY_PERIOD4,p4);
        initialValues.put(KEY_PERIOD5,p5);
        initialValues.put(KEY_PERIOD6,p6);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteTimetable(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_DAYS + "=" + rowId, null) > 0;
    }

    public boolean deleteALLTimetable() {
        return db.delete(DATABASE_TABLE, null, null) > 0;
    }

    public Cursor getAllTimetable() {
        return db.query(DATABASE_TABLE, new String[]{KEY_DAYS, KEY_PERIOD1, KEY_PERIOD2,KEY_PERIOD3,KEY_PERIOD4,KEY_PERIOD5,KEY_PERIOD6}, null, null, null, null, null);
    }

    public Cursor getTimetable(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_DAYS, KEY_PERIOD1, KEY_PERIOD2,KEY_PERIOD3,KEY_PERIOD4,KEY_PERIOD5,KEY_PERIOD6}, KEY_DAYS + "=" + rowId, null, null, null, null, null);
        if(mCursor!=null)
            mCursor.moveToFirst();
        return mCursor;
    }
    public boolean updateTimetable(long rowId,String days, String p1,String p2,String p3,String p4,String p5,String p6){
        ContentValues args=new ContentValues();
        args.put(KEY_DAYS,days);
        args.put(KEY_PERIOD1,p1);
        args.put(KEY_PERIOD2,p2);
        args.put(KEY_PERIOD3,p3);
        args.put(KEY_PERIOD4,p4);
        args.put(KEY_PERIOD5,p5);
        args.put(KEY_PERIOD6,p6);
        return db.update(DATABASE_TABLE,args,KEY_DAYS+"="+rowId,null)>0;
    }
}

