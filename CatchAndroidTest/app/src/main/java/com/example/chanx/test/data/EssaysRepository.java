package com.example.chanx.test.data;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by chanx on 01/10/2017.
 *
 * Represent the essays data source, combined with local database and network source, as well
 * as a memory cache.
 *
 * Anyone who need get data, it will first check cache, and then database, at last the network,
 * so we don't need to visit network each time. But it still need add auto refresh strategy
 *
 */

public class EssaysRepository implements IEssaysDataSource {

    private static final String TAG = EssaysRepository.class.getSimpleName();

    private static EssaysRepository INSTANCE;

    private final IEssaysDataSource mLocalSource;

    private final IEssaysDataSource mRemoteSource;

    final List<EssayItem> mCachedEssays = new ArrayList<>();

    private boolean mIsCachedDirty = false;

    private EssaysRepository(IEssaysDataSource local, IEssaysDataSource remote) {
        mLocalSource = local;
        mRemoteSource = remote;
    }

    public static EssaysRepository getInstance(IEssaysDataSource local, IEssaysDataSource remote) {
        if (INSTANCE == null) {
            synchronized (EssaysRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EssaysRepository(local, remote);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<EssayItem>> getEssays() {
        if (!mCachedEssays.isEmpty() && !mIsCachedDirty) {
            return Observable.from(mCachedEssays).toList();
        } else {
            Observable<List<EssayItem>> local = getLocalAndSave();
            Observable<List<EssayItem>> remote = getRemoteAndSave();

            //if local source exist, load local, else load remote source
            return Observable.concat(local, remote)
                    .filter(new Func1<List<EssayItem>, Boolean>() {
                        @Override
                        public Boolean call(List<EssayItem> essayItems) {
                            return essayItems != null && essayItems.size() > 0;
                        }
                    }).first();
        }
    }

    /**
     * get essays data from server and save it to local & cache as well
     *
     * @return
     */
    private Observable<List<EssayItem>> getRemoteAndSave() {

        return mRemoteSource.getEssays()
                .flatMap(new Func1<List<EssayItem>, Observable<List<EssayItem>>>() {
                    @Override
                    public Observable<List<EssayItem>> call(List<EssayItem> essayItems) {
                        return Observable.from(essayItems).doOnNext(new Action1<EssayItem>() {
                            @Override
                            public void call(EssayItem essayItem) {
                                mLocalSource.saveEssay(essayItem);
                                mCachedEssays.add(essayItem);
                            }
                        }).toList().doOnCompleted(new Action0() {
                            @Override
                            public void call() {
                                mIsCachedDirty = false;
                            }
                        });
                    }
                });
    }

    private Observable<List<EssayItem>> getLocalAndSave() {
        return mLocalSource.getEssays().filter(
                new Func1<List<EssayItem>, Boolean>() {
                    @Override
                    public Boolean call(List<EssayItem> essayItems) {
                        return essayItems != null;
                    }
                })
                .flatMap(new Func1<List<EssayItem>, Observable<List<EssayItem>>>() {
                    @Override
                    public Observable<List<EssayItem>> call(List<EssayItem> essayItems) {
                        return Observable.from(essayItems).doOnNext(new Action1<EssayItem>() {
                            @Override
                            public void call(EssayItem essayItem) {
                                mCachedEssays.add(essayItem);
                            }
                        }).toList();
                    }
                });
    }

    @Override
    public Observable<EssayItem> getEssay(final int id) {
        final EssayItem essay = mCachedEssays.get(id);
        if (essay != null) {
            return Observable.just(essay);
        }

        //if cannot find from cache, load from disk or server
        Observable<EssayItem> local = mLocalSource.getEssay(id).doOnNext(new Action1<EssayItem>() {
            @Override
            public void call(EssayItem essayItem) {
                mCachedEssays.add(essayItem);
            }
        }).first();
        Observable<EssayItem> remote = mRemoteSource.getEssay(id).doOnNext(new Action1<EssayItem>() {
            @Override
            public void call(EssayItem essayItem) {
                mCachedEssays.add(essayItem);
                mLocalSource.saveEssay(essayItem);
            }
        });

        return Observable.concat(local, remote).first().map(new Func1<EssayItem, EssayItem>() {
            @Override
            public EssayItem call(EssayItem essayItem) {
                if (essayItem == null) {
                    throw new NoSuchElementException("No essay found with id: " + id);
                }
                return essayItem;
            }
        });
    }

    @Override
    public void refreshEssays() {
        mIsCachedDirty = true;
    }

    @Override
    public void saveEssay(EssayItem essay) {

    }
}
