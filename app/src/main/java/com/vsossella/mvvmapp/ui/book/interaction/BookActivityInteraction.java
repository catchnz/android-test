package com.vsossella.mvvmapp.ui.book.interaction;

/**
 * Created by vsossella on 26/06/17.
 */

public interface BookActivityInteraction {

    void showLoading();
    void hideLoading();
    void showToast(String toastMessage);

}
