package com.payne.isaac.jsonlist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.payne.isaac.jsonlist.R;
import com.payne.isaac.jsonlist.adapters.DataListAdapter;
import com.payne.isaac.jsonlist.model.DataModel;
import com.payne.isaac.jsonlist.network.NetworkHandler;
import com.payne.isaac.jsonlist.network.NetworkParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NetworkHandler networkHandler;

    private ArrayList<DataModel> mDataList = new ArrayList<>();
    private DataListAdapter mDataListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkHandler = new NetworkHandler(this);

        setupUi();
    }

    private void setupUi() {
        mDataListAdapter = new DataListAdapter(this, mDataList);

        ListView dataListView = (ListView) findViewById(R.id.lv_data_view);

        dataListView.setAdapter(mDataListAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeRequest(getString(R.string.json_url));
            }
        });

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleItemSelect(position);
            }
        });
    }

    private void makeRequest(String url) {
        networkHandler.makeRequest(url,
                new NetworkHandler.OnResponseListener() {
                    @Override
                    public void onSuccess(JSONArray jsonArray) {
                        try {
                            mDataList.clear();
                            mDataList.addAll(NetworkParser.parseDataModel(jsonArray));
                            mDataListAdapter.notifyDataSetChanged();
                            mSwipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            showError("Error reading JSON");
                        }
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        showError(errorMessage);
                    }
                });
    }

    private void handleItemSelect(int pos) {
        Intent mIntent = new Intent(this, ViewDetailActivity.class);
        mIntent.putExtra("content", mDataList.get(pos).getContent());
        startActivity(mIntent);
    }

    private void showError(String message){
        Toast.makeText(this, "Error retrieving data: " + message, Toast.LENGTH_LONG).show();
    }

}
