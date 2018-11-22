package com.ruslanlyalko.union.presentation.ui.main.users;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public interface UsersView extends BaseView<UsersPresenter> {

    void showUsers(MutableLiveData<List<User>> users);

    void starUserAddScreen();
}
