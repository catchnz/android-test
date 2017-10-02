package com.example.chanx.test.data.source;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.chanx.test.data.IEssaysDataSource;
import com.example.chanx.test.data.EssayItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chanx on 29/09/2017.
 */

public class FakeRemoteEssaysDataSource implements IEssaysDataSource {


    private static FakeRemoteEssaysDataSource INSTANCE;
    private final AssetManager mAssetManager;
    private static final String TAG = FakeRemoteEssaysDataSource.class.getSimpleName();

    private FakeRemoteEssaysDataSource(Context context) {
        mAssetManager = context.getAssets();
    }

    public static FakeRemoteEssaysDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FakeRemoteEssaysDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FakeRemoteEssaysDataSource(context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<EssayItem>> getEssays() {
        return loadEssaysFromAsset().subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<EssayItem> getEssay(int essayId) {
        return null;
    }

    @Override
    public void refreshEssays() {
    }

    @Override
    public void saveEssay(EssayItem essay) {

    }

    private Observable<List<EssayItem>> loadEssaysFromAsset() {
        Log.d(TAG, "loadEssaysFromAsset");
        try {
            return Observable.just(new InputStreamReader(mAssetManager.open("data.json")))
                    .doOnNext(new Action1<InputStreamReader>() {
                        @Override
                        public void call(InputStreamReader inputStreamReader) {
                            Log.d(TAG, "get reader " + inputStreamReader);
                        }
                    })
                    .map(new Func1<Reader, List<EssayItem>>() {
                        @Override
                        public List<EssayItem> call(Reader reader) {
                            Log.d(TAG, "load from json file");
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<EssayItem>>() {
                            }.getType();

                            return gson.fromJson(reader, type);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Observable.empty();
    }

}
