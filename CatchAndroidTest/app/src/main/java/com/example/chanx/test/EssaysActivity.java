package com.example.chanx.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.Window;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.chanx.test.data.EssayItem;
import com.example.chanx.test.essays.EssaysContract;
import com.example.chanx.test.essays.EssaysPresenter;
import com.example.chanx.test.essays.EssaysRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for showing essays list
 */
public class EssaysActivity extends AppCompatActivity
        implements EssaysContract.View {

    private RecyclerRefreshLayout mSwipeRefreshLayout;
    private EssaysContract.Presenter mPresenter;
    private EssaysRecyclerViewAdapter mEssaysAdapter;
    private static final String TAG = EssaysActivity.class.getSimpleName();
    private boolean isEmptyPage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.essays_list);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        mSwipeRefreshLayout = (RecyclerRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mPresenter != null) {
                    mPresenter.subscribe();
                }
            }
        });
        mSwipeRefreshLayout.setRefreshStyle(RecyclerRefreshLayout.RefreshStyle.PINNED);
        mSwipeRefreshLayout.setRefreshView(getLayoutInflater().inflate(R.layout.header, null),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // Set the adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEssaysAdapter = new EssaysRecyclerViewAdapter(new ArrayList<EssayItem>(),
                new EssaysRecyclerViewAdapter.OnListItemClickListener() {
                    @Override
                    public void onClick(EssayItem item) {
                        showDetailPage(item);
                    }
                });
        recyclerView.setAdapter(mEssaysAdapter);

        mPresenter = new EssaysPresenter(Injection.provideTasksRepository(this), this);
        if (savedInstanceState != null && !savedInstanceState.getBoolean("isEmpty", true)) {
            mPresenter.subscribe();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!isEmptyPage) {
            outState.putBoolean("isEmpty", isEmptyPage);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setPresenter(EssaysContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showEssays(List<EssayItem> essays) {
        if (mEssaysAdapter != null) {
            mEssaysAdapter.replaceAll(essays);
            mEssaysAdapter.notifyItemInserted(mEssaysAdapter.getItemCount() - 1);
            isEmptyPage = false;
        }
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showEmptyPage() {
        //TODO
    }

    @Override
    public void showDetailPage(EssayItem essay) {
        EssayDetailActivity.startDetailActivity(this, essay.getContent());
    }

}
