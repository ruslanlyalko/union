package com.ruslanlyalko.union.presentation.ui.main.users.edit;

import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseView;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public interface UserEditView extends BaseView<UserEditPresenter> {

    void showProgress();

    void hideProgress();

    void afterSuccessfullySaving(final User user);

    void showUserData(User user);
}
