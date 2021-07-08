package com.vsossella.mvvmapp.api.interfaces;

import com.vsossella.mvvmapp.api.entity.book.BookOutput;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vsossella on 07/08/17.
 */

public interface BookAPI {

    @GET("data.json")
    Call<List<BookOutput>> findAllBooks();

//    https://raw.githubusercontent.com/catchnz/android-test/master/data/data.json

}
