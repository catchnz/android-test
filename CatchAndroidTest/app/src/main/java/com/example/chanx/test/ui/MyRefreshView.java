package com.example.chanx.test.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.dinuscxj.refresh.IRefreshStatus;

/**
 * Created by chanx on 01/10/2017.
 *
 * UI for refresh header, showing a inverse progressbar
 */

public class MyRefreshView extends LinearLayout implements IRefreshStatus {
    public MyRefreshView(Context context) {
        super(context);
    }

    public MyRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRefreshView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void reset() {

    }

    @Override
    public void refreshing() {

    }

    @Override
    public void refreshComplete() {
    }

    @Override
    public void pullToRefresh() {
    }

    @Override
    public void releaseToRefresh() {

    }

    @Override
    public void pullProgress(float pullDistance, float pullProgress) {

    }
}
