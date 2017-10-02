package com.example.chanx.test.data.source.remote;

import com.example.chanx.test.data.EssayItem;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chanx on 01/10/2017.
 */

public interface RemoteAPI {
    @GET("data.json")
    Observable<List<EssayItem>> getAllEssays();
}
