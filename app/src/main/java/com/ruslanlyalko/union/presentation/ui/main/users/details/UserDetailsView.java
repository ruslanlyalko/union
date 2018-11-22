package com.ruslanlyalko.union.presentation.ui.main.users.details;

import android.arch.lifecycle.MutableLiveData;
import android.util.SparseIntArray;

import com.ruslanlyalko.union.data.models.Order;
import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseView;

import java.util.Date;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public interface UserDetailsView extends BaseView<UserDetailsPresenter> {

    void showReports(MutableLiveData<List<Order>> vacationReportsData);

    void showReportsByYear(Date firstWorkingDate, SparseIntArray mYears);

    void showUserDetails(User user);
}
