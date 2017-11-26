package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Created by cl-macmini-01 on 9/20/17.
 */

public interface SignInPresenter extends BasePresenter {

    /**
     * On SignIn clicked
     *
     * @param email    the provided email
     * @param password the provided password
     */
    void onSignInClicked(final String email, final String password);
}
