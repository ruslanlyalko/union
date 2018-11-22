package com.ruslanlyalko.union.presentation.ui.main.profile.edit;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseView;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public interface ProfileEditView extends BaseView<ProfileEditPresenter> {

    void showUser(MutableLiveData<User> user);

    void showProgress();

    void hideProgress();

    void afterSuccessfullySaving();
}
