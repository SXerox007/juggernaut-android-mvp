package com.skeleton.mvp.ui.onboarding.signup;

/**
 * Created by sumitthakur on 11/12/17..
 */

public interface SignUpPresenter {

    /**
     * @param email      email
     * @param name       name
     * @param password   password
     * @param rePassword rePassword
     */
    void onSignUpClicked(final String email, final String name, final String password, final String rePassword);

}
