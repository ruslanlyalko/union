package com.ruslanlyalko.union.presentation.ui.main.profile;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseView;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public interface ProfileView extends BaseView<ProfilePresenter> {

    void startProfileEditScreen();

    void showUser(MutableLiveData<User> myUserData);

    void populateUser(User user);

    void showLoginScreen();
}
