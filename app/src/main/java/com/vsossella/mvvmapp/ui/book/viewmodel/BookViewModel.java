package com.vsossella.mvvmapp.ui.book.viewmodel;

import android.databinding.ObservableArrayList;

import com.vsossella.mvvmapp.api.CallbackListener;
import com.vsossella.mvvmapp.api.entity.book.BookOutput;
import com.vsossella.mvvmapp.service.book.BookService;
import com.vsossella.mvvmapp.ui.book.interaction.BookActivityInteraction;
import com.vsossella.mvvmapp.ui.book.model.Book;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by vsossella on 26/06/17.
 */

public class BookViewModel {

    public ObservableArrayList<Book> books = new ObservableArrayList<>();
    BookActivityInteraction interaction;
    BookService bookService;


    @Inject
    public BookViewModel(BookService bookService) {
        this.bookService = bookService;
    }

    public void loadBooksFromAPI() {
        interaction.showLoading();

        bookService.findAllBooks(new CallbackListener<List<BookOutput>, String>() {
            @Override
            public void onResponseSuccess(List<BookOutput> responseSuccess) {
                refreshBooks(responseSuccess);
                interaction.hideLoading();
                interaction.showToast("Books loaded with success");
            }

            @Override
            public void onResponseFailed(String responseFailed) {
                interaction.hideLoading();
                interaction.showToast(responseFailed);
            }
        });
    }

    public void refreshBooks(List<BookOutput> responseSuccess) {
        List<Book> booksMapped = new ArrayList<>();
        for (BookOutput bookOutput :
                responseSuccess) {
            booksMapped.add(new Book(bookOutput.getTitle(), bookOutput.getSubtitle(), bookOutput.getContent()));
        }
        books.clear();
        books.addAll(booksMapped);
    }

    public void setInteraction(BookActivityInteraction interaction) {
        this.interaction = interaction;
    }
}
