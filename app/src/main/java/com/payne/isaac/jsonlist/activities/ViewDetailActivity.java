package com.payne.isaac.jsonlist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.payne.isaac.jsonlist.R;

public class ViewDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        String content = getIntent().getStringExtra("content");

        TextView textView = (TextView) findViewById(R.id.tv_text);
        textView.setText(content);
    }
}
