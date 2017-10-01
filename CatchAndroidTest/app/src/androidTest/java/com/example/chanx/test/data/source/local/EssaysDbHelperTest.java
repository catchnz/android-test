package com.example.chanx.test.data.source.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.text.TextUtils;
import android.util.Log;

import com.example.chanx.test.data.EssayItem;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.example.chanx.test.data.source.local.EssaysPersistenceContract.EssayEntry.TABLE_NAME;

/**
 * Created by chanx on 01/10/2017.
 */
public class EssaysDbHelperTest {
    @Test
    public void testDb() {

        EssaysDbHelper helper = new EssaysDbHelper(InstrumentationRegistry.getTargetContext());
        SqlBrite brite = new SqlBrite.Builder().build();
        BriteDatabase databaseHelper = brite.wrapDatabaseHelper(helper, Schedulers.trampoline());

        databaseHelper.delete(TABLE_NAME,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID + " > -1");

        String[] projection = {
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_TITLE,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_SUBTITLE,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_CONTENT
        };
        String sql = String.format("SELECT %s FROM %s",
                TextUtils.join(",", projection),
                TABLE_NAME);
        Subscription subscription = databaseHelper.createQuery(TABLE_NAME, sql)
                .mapToList(new Func1<Cursor, EssayItem>() {
                    @Override
                    public EssayItem call(Cursor cursor) {
                        throw new RuntimeException();
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d("TestRunner", "onUnSubscribe");
                    }
                })
                .subscribe(new Subscriber<List<EssayItem>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("TestRunner", "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("TestRunner", "onError()", e);
                    }

                    @Override
                    public void onNext(List<EssayItem> essayItems) {
                        Assert.assertEquals(essayItems.size(), 0);
                    }
                });

        subscription.unsubscribe();

        ContentValues values = new ContentValues();
        values.put(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID, 0);
        values.put(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_TITLE, "title");
        values.put(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_SUBTITLE, "subtitle");
        values.put(EssaysPersistenceContract.EssayEntry.COLUMN_NAME_CONTENT, "content");
        databaseHelper.insert(TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);

        String[] projection1 = {
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_TITLE,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_SUBTITLE,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_CONTENT
        };
        sql = String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection1),
                TABLE_NAME,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID);
        subscription = databaseHelper.createQuery(
                TABLE_NAME, sql, String.valueOf(0))
                .mapToOneOrDefault(new Func1<Cursor, EssayItem>() {
                    @Override
                    public EssayItem call(Cursor cursor) {
                        Assert.assertEquals(cursor.getInt(0), 0);
                        Assert.assertEquals(cursor.getString(1), "title");
                        Assert.assertEquals(cursor.getString(2), "subtitle");
                        Assert.assertEquals(cursor.getString(3), "content");
                        return null;
                    }
                }, null).subscribe();

        subscription.unsubscribe();

    }

}