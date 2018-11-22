package com.ruslanlyalko.union.presentation.view;

import android.view.View;

/**
 * Created by Ruslan Lyalko
 * on 14.01.2018.
 */

public interface OnReportClickListener {

    void onPhoneClicked(View view, final int position);
    void onReportClicked(View view, final int position);
    void onReportRemoveClicked(View view, final int position);
}
