package com.ruslanlyalko.union.presentation.ui.main.dashboard;

import com.ruslanlyalko.union.data.models.Order;
import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BasePresenter;
import com.ruslanlyalko.union.presentation.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public class DashboardPresenter extends BasePresenter<DashboardView> {

    private User mUser;
    private Date mDate = new Date();
    private List<Order> mOrders = new ArrayList<>();

    DashboardPresenter() {
    }

    public void onViewReady() {
        getView().showUser(getDataManager().getMyUser());
    }

    private List<Order> getReportsForCurrentDate() {
        List<Order> result = new ArrayList<>();
        for (Order r : mOrders) {
            if (r.getDate().after(DateUtils.getStart(mDate))
                    && r.getDate().before(DateUtils.getEnd(mDate))) {
                result.add(r);
            }
        }
        return result;
    }

    public void fetchReportsForDate(Date date) {
        mDate = DateUtils.getDate(date, 1, 1);
        getView().showReports(getReportsForCurrentDate());
    }

    public void onReportPhoneClicked(final Order order) {
        getView().dialNumber(order.getName(), order.getPhone());
    }

    public void onReportDeleteClicked(final Order order) {
//        order.setUpdatedAt(new Date());
//        getDataManager().saveOrder(order)
//                .addOnSuccessListener(aVoid ->
        getDataManager().removeOrder(order)
                .addOnCompleteListener(task -> {
                    if (getView() == null) return;
                    getView().showReports(getReportsForCurrentDate());
                });
//                )
//                .addOnFailureListener(e -> {
//                    if (getView() == null) return;
//                    getView().showWrongDateOnMobileError();
//                });
    }

    public void onReportLongClicked(final Order order) {
        getView().editReport(mUser, order);
    }

    public void onFabClicked() {
        getView().startAddReportScreen(mUser, mDate);
    }

    public Date getDate() {
        return mDate;
    }

    public List<Order> getOrders() {
        return mOrders;
    }

    public void setOrders(final List<Order> orders) {
        mOrders = orders;
        getView().showReports(getReportsForCurrentDate());
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(final User user) {
        mUser = user;
        if (mUser.getIsBlocked()) {
            getAuth().signOut();
            getView().showErrorAndStartLoginScreen();
        }
        if (mUser.getIsAdmin())
            getView().showReportsOnCalendar(getDataManager().getAllOrders());
        else
            getView().showReportsOnCalendar(getDataManager().getAllMyOrders());
    }
}
