package com.vsossella.mvvmapp.ui.book.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vsossella.mvvmapp.databinding.BookItemViewBinding;
import com.vsossella.mvvmapp.ui.book.model.Book;

/**
 * Created by vsossella on 26/06/17.
 */

public class BookViewHolder extends RecyclerView.ViewHolder {

    BookItemViewBinding binding;

    public BookViewHolder(View itemView, BookItemViewBinding binding) {
        super(itemView);
        this.binding = binding;
    }

    public void bindBook(Book book) {
        this.binding.setViewModel(book);
        this.binding.executePendingBindings();
    }

}
