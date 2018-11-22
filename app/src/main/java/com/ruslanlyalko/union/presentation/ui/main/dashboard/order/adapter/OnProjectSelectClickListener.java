package com.ruslanlyalko.union.presentation.ui.main.dashboard.order.adapter;

import android.view.View;

/**
 * Created by Ruslan Lyalko
 * on 14.01.2018.
 */

public interface OnProjectSelectClickListener {

    void onProjectAddClicked(View view, final int position);

    void onProjectChangeClicked(View view, final int position);
}
