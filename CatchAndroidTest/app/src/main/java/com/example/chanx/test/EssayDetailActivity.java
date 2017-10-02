package com.example.chanx.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity for showing essay detail page
 */
public class EssayDetailActivity extends AppCompatActivity {

    private static final String ARG_CONTENT = "content";

    private String mContent;

    private TextView mContentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.essay_detail);

        if (savedInstanceState != null) {
            if (savedInstanceState.getString(ARG_CONTENT) != null) {
                mContent = savedInstanceState.getString(ARG_CONTENT);
            }
        }

        Bundle data = getIntent().getExtras();
        if (data != null) {
            mContent = data.getString(ARG_CONTENT);
        }

        mContentText = (TextView) findViewById(R.id.content);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mContentText != null) {
            mContentText.setText(mContent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_CONTENT, mContent);
        super.onSaveInstanceState(outState);
    }

    public static void startDetailActivity(Context context, String content) {
        Intent intent = new Intent(context, EssayDetailActivity.class);
        intent.putExtra(ARG_CONTENT, content);
        context.startActivity(intent);
    }
}
