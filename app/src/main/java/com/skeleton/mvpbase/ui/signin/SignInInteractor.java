package com.skeleton.mvpbase.ui.signin;

import com.skeleton.mvpbase.model.CommonResponse;
import com.skeleton.mvpbase.network.ApiError;

/**
 * Created by cl-macmini-01 on 9/27/17.
 */

public interface SignInInteractor {

    /**
     * Do login
     *
     * @param email          the provided email
     * @param password       the provided password
     * @param signInListener the sign in listener
     */
    void login(String email, String password, SignInListener signInListener);

    /**
     * SignIn listener
     */
    interface SignInListener {

        /**
         * On SignIn success
         *
         * @param commonResponse the parsed common response object
         */
        void onSignInSuccess(CommonResponse commonResponse);

        /**
         * On SignIn failed
         *
         * @param apiError  the parsed api error if any
         * @param throwable the generated throwable if any
         */
        void onSignInFailed(final ApiError apiError, final Throwable throwable);
    }

}
