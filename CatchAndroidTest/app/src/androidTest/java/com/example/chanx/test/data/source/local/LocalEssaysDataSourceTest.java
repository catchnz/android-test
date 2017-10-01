package com.example.chanx.test.data.source.local;

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.example.chanx.test.data.EssayItem;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.example.chanx.test.data.source.local.EssaysPersistenceContract.EssayEntry.TABLE_NAME;

/**
 * Created by chanx on 01/10/2017.
 */
public class LocalEssaysDataSourceTest {
    LocalEssaysDataSource mLocalEssayDataSource;

    @Before
    public void setUp() throws Exception {
        mLocalEssayDataSource =
                LocalEssaysDataSource.getInstance(InstrumentationRegistry.getTargetContext(),
                        Schedulers.immediate());

        EssaysDbHelper helper = new EssaysDbHelper(InstrumentationRegistry.getTargetContext());
        SqlBrite brite = new SqlBrite.Builder().build();
        BriteDatabase databaseHelper = brite.wrapDatabaseHelper(helper, Schedulers.immediate());

        databaseHelper.delete(TABLE_NAME,
                EssaysPersistenceContract.EssayEntry.COLUMN_NAME_ENTRY_ID + " > -1");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

        Log.d("TestRunner", "start test");
        Subscription subscription = mLocalEssayDataSource.getEssays().subscribe(new Subscriber<List<EssayItem>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<EssayItem> essayItems) {
                throw new RuntimeException("");
            }
        });
        subscription.unsubscribe();

        subscription = mLocalEssayDataSource.getEssay(0).subscribe(new Subscriber<EssayItem>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.w("TestRunner", e.getLocalizedMessage(), e);

            }

            @Override
            public void onNext(EssayItem essayItem) {
                if (essayItem != null) {
                    throw new RuntimeException();
                }
            }
        });
        subscription.unsubscribe();

        final EssayItem essay = new EssayItem(0, "title", "subtitle", "content");
        mLocalEssayDataSource.saveEssay(essay);

        subscription = mLocalEssayDataSource.getEssays().subscribe(new Action1<List<EssayItem>>() {
            @Override
            public void call(List<EssayItem> essayItems) {
                try {
                    Assert.assertEquals(essayItems.size(), 1);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        });
        subscription.unsubscribe();

        subscription = mLocalEssayDataSource.getEssay(0).subscribe(new Action1<EssayItem>() {
            @Override
            public void call(EssayItem essayItem) {
                try {
                    Assert.assertEquals(essayItem.getID(), essay.getID());
                    Assert.assertEquals(essayItem.getTitle(), essay.getTitle());
                    Assert.assertEquals(essayItem.getSubtitle(), essay.getSubtitle());
                    Assert.assertEquals(essayItem.getContent(), essay.getContent());
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        });
        subscription.unsubscribe();
    }

}