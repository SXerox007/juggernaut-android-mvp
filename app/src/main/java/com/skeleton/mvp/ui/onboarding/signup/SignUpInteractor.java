package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;

/**
 * Created by sumitthakur on 11/12/17.
 */

public interface SignUpInteractor {

    /**
     * Do login
     *
     * @param email          the provided email
     * @param password       the provided password
     * @param name           the provided name
     * @param signUpListener the sign in listener
     */
    void signUp(final String email, final String password, final String name, final SignUpInteractor.SignUpListener signUpListener);

    /**
     * SignIn listener
     */
    interface SignUpListener {

        /**
         * On SignIn success
         *
         * @param commonResponse the parsed common response object
         */
        void onSignUpSuccess(final CommonResponse commonResponse);

        /**
         * On SignIn failed
         *
         * @param apiError  the parsed api error if any
         * @param throwable the generated throwable if any
         */
        void onSignUpFailed(final ApiError apiError, final Throwable throwable);
    }
}
