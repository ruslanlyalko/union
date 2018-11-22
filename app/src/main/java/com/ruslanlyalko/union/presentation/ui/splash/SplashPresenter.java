package com.ruslanlyalko.union.presentation.ui.splash;

import com.ruslanlyalko.union.presentation.base.BasePresenter;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    SplashPresenter() {
    }

    public void onViewReady() {
        if (getCurrentUser() != null) {
            getView().startDashboardScreen();
        } else {
            getView().startLoginScreen();
        }
    }
}
