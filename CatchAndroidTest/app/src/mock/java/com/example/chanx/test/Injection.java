package com.example.chanx.test;

import android.content.Context;

import com.example.chanx.test.data.EssaysRepository;
import com.example.chanx.test.data.source.FakeRemoteEssaysDataSource;
import com.example.chanx.test.data.source.local.LocalEssaysDataSource;

import rx.schedulers.Schedulers;

/**
 * Created by chanx on 01/10/2017.
 */

public class Injection {
    public static EssaysRepository provideTasksRepository(Context context) {
        return EssaysRepository.getInstance(
                LocalEssaysDataSource.getInstance(context, Schedulers.io()),
                FakeRemoteEssaysDataSource.getInstance(context));

    }
}