package com.ruslanlyalko.union.presentation.ui.main.dashboard;

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
public interface DashboardView extends BaseView<DashboardPresenter> {

    void showUser(MutableLiveData<User> user);

    void showReportsOnCalendar(MutableLiveData<List<Order>> reports);

    void showReports(List<Order> orders);

    void showErrorAndStartLoginScreen();

    void editReport(User user, Order order);

    void startAddReportScreen(final User user, final Date date);

    void showWrongDateOnMobileError();

    void dialNumber(final String name, String phone);
}
