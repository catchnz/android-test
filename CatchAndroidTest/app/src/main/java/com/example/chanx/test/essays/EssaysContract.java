package com.example.chanx.test.essays;

import com.example.chanx.test.BasePresenter;
import com.example.chanx.test.BaseView;
import com.example.chanx.test.data.EssayItem;

import java.util.List;

/**
 * Created by chanx on 01/10/2017.
 *
 * Contract for the essays list view
 */

public interface EssaysContract {
    interface View extends BaseView<Presenter> {
        void showEssays(List<EssayItem> essays);
        void showEmptyPage();
        void showDetailPage(EssayItem essay);
    }

    interface Presenter extends BasePresenter {
        void loadEssays(boolean forceUpdate);
    }

}
