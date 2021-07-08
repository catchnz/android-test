package com.vsossella.mvvmapp;

import com.vsossella.mvvmapp.api.CallbackListener;
import com.vsossella.mvvmapp.api.entity.book.BookOutput;
import com.vsossella.mvvmapp.service.book.BookService;
import com.vsossella.mvvmapp.ui.book.interaction.BookActivityInteraction;
import com.vsossella.mvvmapp.ui.book.viewmodel.BookViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by vsossella on 18/07/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class BookViewModelTest {

    BookViewModel bookViewModel;

    @Mock
    BookService bookService;

    @Mock
    BookActivityInteraction interaction;

    @Captor
    private ArgumentCaptor<CallbackListener<List<BookOutput>, String>> cb;

    @Test
    public void loadBooksFromAPI_Success_Behaves_as_Expected() {
        bookViewModel = new BookViewModel(bookService);
        bookViewModel.setInteraction(interaction);

        bookViewModel.loadBooksFromAPI();
        verify(bookService).findAllBooks(cb.capture());


        List<BookOutput> responseSuccess = new ArrayList<>();
        BookOutput bookOutput = new BookOutput();
        bookOutput.setTitle("dsadas");
        bookOutput.setSubtitle("name");
        responseSuccess.add(bookOutput);
        cb.getValue().onResponseSuccess(responseSuccess);

        verify(interaction).showToast("Books loaded with success");
        verify(interaction).hideLoading();
    }

    @Test
    public void loadBooksFromAPI_Fail_Behaves_as_Expected() {
        bookViewModel = new BookViewModel(bookService);
        bookViewModel.setInteraction(interaction);

        bookViewModel.loadBooksFromAPI();
        verify(bookService).findAllBooks(cb.capture());

        cb.getValue().onResponseFailed("fail");

        verify(interaction).hideLoading();
        verify(interaction).showToast("fail");

    }


    @Captor
    ArgumentCaptor<CallbackListener<List<BookOutput>, String>> findBooksCaptor;

}
