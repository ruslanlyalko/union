package com.ruslanlyalko.union.presentation.ui.main;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseView;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public interface MainView extends BaseView<MainPresenter> {

    void showUser(MutableLiveData<User> myUserData);

    void showMenu(User user);

    void fabClickedFragment();
}
