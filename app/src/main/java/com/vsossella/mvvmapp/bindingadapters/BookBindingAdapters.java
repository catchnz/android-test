package com.vsossella.mvvmapp.bindingadapters;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vsossella.mvvmapp.ui.book.model.Book;
import com.vsossella.mvvmapp.ui.book.view.adapter.BooksAdapter;

/**
 * Created by vsossella on 26/06/17.
 */

public class BookBindingAdapters {

    @BindingAdapter("bind:books")
    public static void bindBooks(final RecyclerView view, ObservableArrayList<Book> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new BooksAdapter(list, view.getContext()));
    }

}
