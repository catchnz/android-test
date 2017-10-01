package com.example.chanx.test.data.source.remote;

import android.util.Log;

import com.example.chanx.test.data.EssayItem;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by chanx on 01/10/2017.
 */
public class RemoteEssaysDataSourceTest {

    @Test
    public void testGet() throws Exception {
        final CountDownLatch cd = new CountDownLatch(150);

        RemoteEssaysDataSource remote = RemoteEssaysDataSource.getInstance();
        remote.getEssays().flatMap(new Func1<List<EssayItem>, Observable<EssayItem>>() {
            @Override
            public Observable<EssayItem> call(List<EssayItem> essayItems) {
                return Observable.from(essayItems);
            }
        }).subscribe(new Action1<EssayItem>() {
            @Override
            public void call(EssayItem essayItem) {
                Log.d("TestRunner", essayItem.getID() + " => " + essayItem.getTitle());
                cd.countDown();
            }
        });

        Assert.assertTrue(cd.await(5, TimeUnit.SECONDS));
    }
}