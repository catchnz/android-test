package com.example.chanx.test.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chanx on 01/10/2017.
 */

public class EssaysDbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "essays.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EssaysPersistenceContract.EssayEntry.TABLE_NAME + " (" +
                    EssaysPersistenceContract.EssayEntry._ID + " TEXT" + " PRIMARY KEY," +
                    EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID + " INTEGER" + "," +
                    EssaysPersistenceContract.EssayEntry.COLUMN_NAME_TITLE + " TEXT" + "," +
                    EssaysPersistenceContract.EssayEntry.COLUMN_NAME_SUBTITLE + " TEXT" + "," +
                    EssaysPersistenceContract.EssayEntry.COLUMN_NAME_CONTENT + " TEXT" + " )";


    public EssaysDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
