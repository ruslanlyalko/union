package com.ruslanlyalko.union.presentation.ui.main.upcoming;

import android.app.AlertDialog;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ruslanlyalko.union.R;
import com.ruslanlyalko.union.data.models.Order;
import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseFragment;
import com.ruslanlyalko.union.presentation.ui.main.dashboard.adapter.OrdersAdapter;
import com.ruslanlyalko.union.presentation.ui.main.dashboard.order.OrderEditActivity;
import com.ruslanlyalko.union.presentation.view.OnReportClickListener;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class UpcomingOrdersFragment extends BaseFragment<UpcomingOrdersPresenter> implements UpcomingOrdersView {

    private static final String KEY_USER = "user";
    @BindView(R.id.recycler_orders) RecyclerView mRecyclerReports;
    @BindView(R.id.text_placeholder) TextView mTextPlaceholder;
    private OrdersAdapter mOrdersAdapter;

    public static UpcomingOrdersFragment newInstance(User user) {
        Bundle args = new Bundle();
        UpcomingOrdersFragment fragment = new UpcomingOrdersFragment();
        args.putParcelable(KEY_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.menu_upcoming, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                getPresenter().onFilterClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_upcoming_orders;
    }

    @Override
    protected void initPresenter(final Bundle args) {
        setPresenter(new UpcomingOrdersPresenter(args.getParcelable(KEY_USER)));
    }

    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        setToolbarTitle(R.string.title_vacations);
        getBaseActivity().showFab();
        mOrdersAdapter = new OrdersAdapter(new OnReportClickListener() {
            @Override
            public void onPhoneClicked(final View view, final int position) {
                if (mOrdersAdapter.getData().size() > position)
                    getPresenter().onReportPhoneClicked(mOrdersAdapter.getData().get(position));
            }

            @Override
            public void onReportClicked(final View view, final int position) {
                if (mOrdersAdapter.getData().size() > position)
                    getPresenter().onReportLongClicked(mOrdersAdapter.getData().get(position));
            }

            @Override
            public void onReportRemoveClicked(final View view, final int position) {
                AlertDialog.Builder build = new AlertDialog.Builder(getContext());
                build.setMessage(R.string.text_delete);
                build.setPositiveButton(R.string.action_delete, (dialog, which) -> {
                    if (mOrdersAdapter.getData().size() > position)
                        getPresenter().onReportDeleteClicked(mOrdersAdapter.getData().get(position));
                    dialog.dismiss();
                });
                build.setNegativeButton(R.string.action_cancel, (dialog, which) -> dialog.dismiss());
                build.show();
            }
        });
        mRecyclerReports.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerReports.setAdapter(mOrdersAdapter);
        mOrdersAdapter.setUser(getPresenter().getUser());
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerReports, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        getPresenter().onViewReady();
    }

    @Override
    public void onFabClicked() {
        getPresenter().onFabClicked();
    }

    @Override
    public void editReport(final User user, final Order order) {
        startActivity(OrderEditActivity.getLaunchIntent(getContext(), user, order));
    }

    @Override
    public void dialNumber(final String name, final String phone) {
        Uri number = Uri.parse("tel:" + phone);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
        if (!TextUtils.isEmpty(name)) {
            showMessage(name);
        }
    }

    @Override
    public void showOrders(final MutableLiveData<List<Order>> vacationReportsData) {
        vacationReportsData.observe(this, list -> getPresenter().setReports(list));
    }

    @Override
    public void setReportsToAdapter(final List<Order> list) {
        mOrdersAdapter.setData(list);
        mTextPlaceholder.setVisibility((list != null && list.isEmpty()) ? VISIBLE : GONE);
    }

    @Override
    public void startAddReportScreen(final User user, final Date date) {
        startActivity(OrderEditActivity.getLaunchIntent(getContext(), user, date));
    }
}
