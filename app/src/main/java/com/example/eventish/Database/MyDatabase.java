package com.example.eventish.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_FILE_NAME = "favEventsDb";
    public static final String DATABASE_TABLE_NAME = "favs";
    public static final String PKEY = "pkey";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String IMAGE = "image";
    public static final String GENRE = "genre";
    public static final String SUBGENRE = "subgenre";
    public static final String VENUE = "venue";
    public static final String CITY = "city";

    public MyDatabase(@Nullable Context context){
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

//    public MyDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE "+DATABASE_TABLE_NAME+" ("+
                PKEY+" INTEGER PRIMARY KEY,"+NAME+" TEXT,"+DATE+" TEXT,"+IMAGE+" TEXT,"+GENRE+" TEXT,"+SUBGENRE+" TEXT, "+VENUE+" TEXT,"+CITY+" TEXT);";
        db.execSQL(DATABASE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
