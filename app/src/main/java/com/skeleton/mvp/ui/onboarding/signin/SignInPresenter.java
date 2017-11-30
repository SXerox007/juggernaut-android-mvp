package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Developer: Click Labs
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
