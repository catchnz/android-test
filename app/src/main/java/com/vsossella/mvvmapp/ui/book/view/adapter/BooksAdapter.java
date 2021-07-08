package com.vsossella.mvvmapp.ui.book.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vsossella.mvvmapp.R;
import com.vsossella.mvvmapp.databinding.BookItemViewBinding;
import com.vsossella.mvvmapp.ui.bookdetail.view.BookDetailActivity;
import com.vsossella.mvvmapp.ui.book.model.Book;

import java.util.List;

/**
 * Created by vsossella on 26/06/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BookViewHolder> {

    List<Book> books;
    Context context;
    BookItemViewBinding binding;
    LayoutInflater inflater;

    public BooksAdapter(List<Book> books, Context context) {
        super();
        this.books = books;
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.book_item_view, parent, false);
        return new BookViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bindBook(books.get(position));
        holder.binding.itemRecyclerView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.EXTRA_BOOK_DETAIL_INFO, books.get(position).getContent());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
