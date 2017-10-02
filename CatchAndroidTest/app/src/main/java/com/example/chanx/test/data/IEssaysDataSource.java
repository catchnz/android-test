package com.example.chanx.test.data;

import java.util.List;

import rx.Observable;

/**
 * Created by chanx on 01/10/2017.
 */

public interface IEssaysDataSource {

    Observable<List<EssayItem>> getEssays();
    Observable<EssayItem> getEssay(int id);
    void refreshEssays();
    void saveEssay(EssayItem essay);
}
