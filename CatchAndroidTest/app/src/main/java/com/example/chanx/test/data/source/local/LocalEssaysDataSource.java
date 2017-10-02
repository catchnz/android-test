package com.example.chanx.test.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.chanx.test.data.IEssaysDataSource;
import com.example.chanx.test.data.EssayItem;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by chanx on 01/10/2017.
 *
 * Represents local database data source
 *
 */

public class LocalEssaysDataSource implements IEssaysDataSource {


    private static LocalEssaysDataSource INSTANCE;
    private BriteDatabase mDatabaseHelper;
    private static final String TAG = LocalEssaysDataSource.class.getSimpleName();

    private LocalEssaysDataSource(@NonNull Context context, @NonNull Scheduler scheduler) {
        EssaysDbHelper helper = new EssaysDbHelper(context);
        SqlBrite brite = new SqlBrite.Builder().build();
        mDatabaseHelper = brite.wrapDatabaseHelper(helper, scheduler);
    }

    public static LocalEssaysDataSource getInstance(@NonNull Context context, @NonNull Scheduler scheduler) {
        if (INSTANCE == null) {
            synchronized (LocalEssaysDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalEssaysDataSource(context, scheduler);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public Observable<List<EssayItem>> getEssays() {
        String[] projection = {
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_TITLE,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_SUBTITLE,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_CONTENT
        };
        String sql = String.format("SELECT %s FROM %s", TextUtils.join(",", projection), EssaysPersistenceContract.EssayEntry.TABLE_NAME);
        return mDatabaseHelper.createQuery(EssaysPersistenceContract.EssayEntry.TABLE_NAME, sql)
                .mapToOneOrDefault(new Func1<Cursor, List<EssayItem>>() {
                    @Override
                    public List<EssayItem> call(Cursor cursor) {
                        ArrayList<EssayItem> list = new ArrayList<EssayItem>();
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            list.add(getEssay(cursor));
                            cursor.moveToNext();
                        }

                        return list;
                    }
                }, null).first();
    }

    private EssayItem getEssay(Cursor c) {
        int itemId = c.getInt(c.getColumnIndexOrThrow(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID));
        String title = c.getString(c.getColumnIndexOrThrow(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_TITLE));
        String subtitle =
                c.getString(c.getColumnIndexOrThrow(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_SUBTITLE));
        String content =
                c.getString(c.getColumnIndexOrThrow(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_CONTENT));
        return new EssayItem(itemId, title, subtitle, content);

    }

    @Override
    public Observable<EssayItem> getEssay(int essayId) {
        String[] projection = {
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_TITLE,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_SUBTITLE,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_CONTENT
        };
        String sql = String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection),
                EssaysPersistenceContract.EssayEntry.TABLE_NAME,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID);
        return mDatabaseHelper.createQuery(
                EssaysPersistenceContract.EssayEntry.TABLE_NAME, sql, String.valueOf(essayId))
                .mapToOneOrDefault(new Func1<Cursor, EssayItem>() {
                    @Override
                    public EssayItem call(Cursor cursor) {
                        return getEssay(cursor);
                    }
                }, null);

    }

    @Override
    public void refreshEssays() {
        // not need

    }

    @Override
    public void saveEssay(EssayItem essay) {
        ContentValues values = new ContentValues();
        values.put(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID, essay.getID());
        values.put(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_TITLE, essay.getTitle());
        values.put(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_SUBTITLE, essay.getSubtitle());
        values.put(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_CONTENT, essay.getContent());
        mDatabaseHelper.insert(EssaysPersistenceContract.EssayEntry.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
}
