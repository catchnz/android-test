package com.vsossella.mvvmapp.ui.book.view;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.vsossella.mvvmapp.MainApplication;
import com.vsossella.mvvmapp.R;
import com.vsossella.mvvmapp.databinding.BookActivityBinding;
import com.vsossella.mvvmapp.ui.book.interaction.BookActivityInteraction;
import com.vsossella.mvvmapp.ui.book.viewmodel.BookViewModel;

import javax.inject.Inject;

/**
 * Created by vsossella on 26/06/17.
 */

public class BookActivity extends AppCompatActivity
        implements BookActivityInteraction,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    BookViewModel bookViewModel;
    BookActivityBinding binding;
    ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        progress = new ProgressDialog(this);
        bookViewModel.setInteraction(this);

        binding = DataBindingUtil.setContentView(this, R.layout.book_activity);
        setupSwipeRefresh();
        bookViewModel.loadBooksFromAPI();
        binding.setViewModel(bookViewModel);
        setSupportActionBar(binding.toolbar);
    }

    private void setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    private void injectDependencies() {
        ((MainApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void showLoading() {
        progress.setMessage(getString(R.string.wait));
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    public void hideLoading() {
        progress.dismiss();
    }

    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        bookViewModel.loadBooksFromAPI();
        showToast("Swiped");
        binding.swipeRefresh.setRefreshing(false);
    }
}
