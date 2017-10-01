package com.example.chanx.test.essays;

import android.util.Log;

import com.example.chanx.test.data.EssayItem;
import com.example.chanx.test.data.EssaysRepository;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chanx on 01/10/2017.
 */

public class EssaysPresenter implements EssaysContract.Presenter {

    private static final String TAG = EssaysPresenter.class.getSimpleName();

    private final EssaysRepository mEssayRepository;

    private final EssaysContract.View mEssaysView;

    private CompositeSubscription mSubscription;

    public EssaysPresenter(EssaysRepository essayRepository, EssaysContract.View essaysView) {
        this.mEssayRepository = essayRepository;
        this.mEssaysView = essaysView;

        mSubscription = new CompositeSubscription();
        mEssaysView.setPresenter(this);
    }


    @Override
    public void subscribe() {
        loadEssays(false);
    }

    @Override
    public void unSubscribe() {
        mSubscription.clear();
    }

    @Override
    public void loadEssays(boolean forceUpdate) {
        loadEssaysInner(forceUpdate);
    }

    private void loadEssaysInner(boolean update) {
        if (update) {
            mEssayRepository.refreshEssays();
        }

        mSubscription.clear();

        mSubscription.add(
                mEssayRepository.getEssays()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<EssayItem>>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.w(TAG, "onError", e);
                                //TODO: need to do thing about the error
                            }

                            @Override
                            public void onNext(List<EssayItem> essays) {
                                processEssays(essays);
                            }
                        })
        );

    }

    private void processEssays(List<EssayItem> essays) {
        if (essays.isEmpty()) {
            mEssaysView.showEmptyPage();
        } else {
            mEssaysView.showEssays(essays);
        }
    }

}
