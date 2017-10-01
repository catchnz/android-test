package com.example.chanx.test.data.source.remote;

import com.example.chanx.test.data.IEssaysDataSource;
import com.example.chanx.test.data.EssayItem;
import com.example.chanx.test.network.NetworkModule;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chanx on 01/10/2017.
 *
 * Represents network data source for essays
 *
 */
public class RemoteEssaysDataSource implements IEssaysDataSource {

    private static RemoteEssaysDataSource INSTANCE;

    private final RemoteAPI REMOTE_API;

    private static final String BASE_URL =
            "https://raw.githubusercontent.com/catchnz/android-test/master/data/";
    private static final String TAG = RemoteEssaysDataSource.class.getSimpleName();

    private RemoteEssaysDataSource() {
        REMOTE_API =
                NetworkModule.provideRetrofit(BASE_URL).create(RemoteAPI.class);
    }

    public static RemoteEssaysDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteEssaysDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteEssaysDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<EssayItem>> getEssays() {
        if (isNetworkAvailable()) {
            return REMOTE_API.getAllEssays()
                    .subscribeOn(Schedulers.io());
        } else {
            return Observable.empty();
        }
    }

    private boolean isNetworkAvailable() {
        //TODO: check network
        return true;
    }

    @Override
    public Observable<EssayItem> getEssay(final int essayId) {
        return getEssays().flatMap(new Func1<List<EssayItem>, Observable<EssayItem>>() {
            @Override
            public Observable<EssayItem> call(List<EssayItem> essayItems) {
                return Observable.from(essayItems);
            }
        }).filter(new Func1<EssayItem, Boolean>() {
            @Override
            public Boolean call(EssayItem essayItem) {
                return essayItem.getID() == essayId;
            }
        }).first();
    }

    @Override
    public void refreshEssays() {
        // do nothing

    }

    @Override
    public void saveEssay(EssayItem essay) {
        // do nothing
    }
}
