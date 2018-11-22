package com.ruslanlyalko.union.presentation.ui.main.dashboard.order;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.union.data.models.Order;
import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseView;

import java.util.Date;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public interface OrderEditView extends BaseView<OrderEditPresenter> {

    void showProgress();

    void hideProgress();

    void afterSuccessfullySaving();

    void showReportData(Order order);

    void showDate(Date date);

    void showSpinnerUsersData(final User user1, MutableLiveData<List<User>> usersData);

    void showUnSaved();
}
