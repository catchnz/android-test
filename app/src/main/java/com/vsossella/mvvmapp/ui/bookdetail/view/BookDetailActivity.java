package com.vsossella.mvvmapp.ui.bookdetail.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vsossella.mvvmapp.R;
import com.vsossella.mvvmapp.databinding.BookDetailActivityBinding;

/**
 * Created by vsossella on 07/08/17.
 */

public class BookDetailActivity extends AppCompatActivity {

    BookDetailActivityBinding binding;
    public static final String EXTRA_BOOK_DETAIL_INFO = "book_detail_info";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.book_detail_activity);
        bindDetailInfos();
    }

    private void bindDetailInfos() {
        final String bookDetailInfos = getIntent().getStringExtra(EXTRA_BOOK_DETAIL_INFO);
        binding.bookDetailInfo.setText(bookDetailInfos);
    }

}
