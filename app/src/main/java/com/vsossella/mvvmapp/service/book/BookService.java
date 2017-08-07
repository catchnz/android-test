package com.vsossella.mvvmapp.service.book;

import com.vsossella.mvvmapp.api.CallbackListener;
import com.vsossella.mvvmapp.api.entity.book.BookOutput;
import com.vsossella.mvvmapp.api.interfaces.BookAPI;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by vsossella on 07/08/17.
 */

@Singleton
public class BookService {

    Retrofit retrofit;

    @Inject
    public BookService(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void findAllBooks(CallbackListener<List<BookOutput>, String> callbackListener) {

        retrofit.create(BookAPI.class).findAllBooks().enqueue(new Callback<List<BookOutput>>() {
            @Override
            public void onResponse(Call<List<BookOutput>> call, Response<List<BookOutput>> response) {
                if (response.isSuccessful())
                    callbackListener.onResponseSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<BookOutput>> call, Throwable t) {
                callbackListener.onResponseFailed(t.getMessage());
            }
        });
    }


}