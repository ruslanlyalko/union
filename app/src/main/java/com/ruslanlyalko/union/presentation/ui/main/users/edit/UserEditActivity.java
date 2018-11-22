package com.ruslanlyalko.union.presentation.ui.main.users.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.ruslanlyalko.union.R;
import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BaseActivity;
import com.ruslanlyalko.union.presentation.utils.DateUtils;
import com.ruslanlyalko.union.presentation.view.SquareButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class UserEditActivity extends BaseActivity<UserEditPresenter> implements UserEditView {

    private static final String KEY_USER = "user";
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.button_save) SquareButton mButtonSave;
    @BindView(R.id.input_name) TextInputEditText mInputName;
    @BindView(R.id.input_phone) TextInputEditText mInputPhone;
    @BindView(R.id.input_comments) TextInputEditText mInputComments;
    @BindView(R.id.input_birthday) TextInputEditText mInputBirthday;
    @BindView(R.id.input_first_working_day) TextInputEditText mInputFirstWorkingDay;
    @BindView(R.id.switch_blocked) Switch mSwitchBlocked;
    @BindView(R.id.progress) ProgressBar mProgress;

    public static Intent getLaunchIntent(final Context activity, User user) {
        Intent intent = new Intent(activity, UserEditActivity.class);
        intent.putExtra(KEY_USER, user);
        return intent;
    }

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_user_edit;
    }

    @Override
    protected void initPresenter(final Intent intent) {
        setPresenter(new UserEditPresenter(intent.getParcelableExtra(KEY_USER)));
    }

    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        setToolbarTitle(R.string.title_edit);
        getPresenter().onViewReady();
    }

    @OnClick(R.id.button_save)
    public void onSaveClick() {
        if (TextUtils.isEmpty(mInputName.getText())) {
            mInputName.setError(getString(R.string.error_cant_be_empty));
            mInputName.requestFocus();
            return;
        }
        getPresenter().onSave(mInputName.getText().toString(),
                mInputPhone.getText().toString(),
                mInputComments.getText().toString(),
                mSwitchBlocked.isChecked());
    }

    @OnClick({R.id.input_birthday, R.id.input_first_working_day})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_birthday:
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(getPresenter().getUser().getBirthday());
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
                    Date newDate = DateUtils.getDate(calendar.getTime(), year, monthOfYear, dayOfMonth);
                    getPresenter().getUser().setBirthday(newDate);
                    mInputBirthday.setText(DateUtils.toStringStandardDate(newDate));
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setMaxDate(DateUtils.getYesterday());
                datePickerDialog.setFirstDayOfWeek(Calendar.MONDAY);
                datePickerDialog.showYearPickerFirst(true);
                datePickerDialog.show(getFragmentManager(), "birthday");
                break;
            case R.id.input_first_working_day:
                Calendar calendarWorking = Calendar.getInstance();
                calendarWorking.setTime(getPresenter().getUser().getFirstWorkingDate());
                DatePickerDialog workingPickerDialog = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
                    Date newDate = DateUtils.getDate(calendarWorking.getTime(), year, monthOfYear, dayOfMonth);
                    getPresenter().getUser().setFirstWorkingDate(newDate);
                    mInputFirstWorkingDay.setText(DateUtils.toStringStandardDate(newDate));
                }, calendarWorking.get(Calendar.YEAR), calendarWorking.get(Calendar.MONTH), calendarWorking.get(Calendar.DAY_OF_MONTH));
                workingPickerDialog.setMaxDate(DateUtils.getYesterday());
                workingPickerDialog.setFirstDayOfWeek(Calendar.MONDAY);
                workingPickerDialog.showYearPickerFirst(true);
                workingPickerDialog.show(getFragmentManager(), "working");
                break;
        }
    }

    @Override
    public void showProgress() {
        hideKeyboard();
        mProgress.setVisibility(View.VISIBLE);
        mButtonSave.showProgress(true);
    }

    @Override
    public void hideProgress() {
        mButtonSave.showProgress(false);
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void afterSuccessfullySaving(final User user) {
        Intent intent = new Intent();
        intent.putExtra(KEY_USER, user);
        setResult(RESULT_OK, intent);
        onBackPressed();
    }

    @Override
    public void showUserData(final User user) {
        mInputName.setText(user.getName());
        mInputPhone.setText(user.getPhone());
        mInputComments.setText(user.getComments());
        mInputBirthday.setText(DateUtils.toStringStandardDate(user.getBirthday()));
        mInputFirstWorkingDay.setText(DateUtils.toStringStandardDate(user.getFirstWorkingDate()));
        mSwitchBlocked.setChecked(user.getIsBlocked());
    }
}
