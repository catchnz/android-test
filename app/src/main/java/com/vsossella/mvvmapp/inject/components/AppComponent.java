package com.vsossella.mvvmapp.inject.components;

import com.vsossella.mvvmapp.inject.modules.AppModule;
import com.vsossella.mvvmapp.inject.modules.NetworkModule;
import com.vsossella.mvvmapp.ui.book.view.BookActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vsossella on 26/06/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(BookActivity bookActivity);

}
