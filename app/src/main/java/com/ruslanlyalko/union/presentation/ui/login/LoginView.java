package com.ruslanlyalko.union.presentation.ui.login;

import com.ruslanlyalko.union.presentation.base.BaseView;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public interface LoginView extends BaseView<LoginPresenter> {

    void showForgotPasswordButton();

    void errorWrongCredentials();

    void errorEmpty();

    void startMainScreen();

    void showProgress();

    void hideProgress();
}
