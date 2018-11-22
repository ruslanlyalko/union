package com.ruslanlyalko.union.presentation.ui.main.profile.edit;

import android.text.TextUtils;

import com.ruslanlyalko.union.data.models.User;
import com.ruslanlyalko.union.presentation.base.BasePresenter;

/**
 * Created by Ruslan Lyalko
 * on 05.09.2018.
 */
public class ProfileEditPresenter extends BasePresenter<ProfileEditView> {

    private User mUser;

    ProfileEditPresenter() {
    }

    public void onViewReady() {
        getView().showUser(getDataManager().getMyUser());
    }

    public void onSave(String phone, String card, final String newPassword) {
        getView().showProgress();
        if (!TextUtils.isEmpty(newPassword)) {
            getDataManager().changePassword(newPassword)
                    .addOnFailureListener(e -> {
                        if (getView() == null) return;
                        getView().showError(e.getLocalizedMessage());
                        getView().hideProgress();
                    })
                    .addOnSuccessListener(aVoid -> saveUserData(phone, card));
            return;
        }
        saveUserData(phone, card);
    }

    private void saveUserData(String phone, String card) {
        mUser.setPhone(phone);
        mUser.setCard(card);
        getDataManager().saveUser(mUser)
                .addOnSuccessListener(aVoid1 -> {
                    if (getView() == null) return;
                    getView().afterSuccessfullySaving();
                })
                .addOnFailureListener(e -> {
                    if (getView() == null) return;
                    getView().hideProgress();
                });
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(final User user) {
        mUser = user;
    }
}
