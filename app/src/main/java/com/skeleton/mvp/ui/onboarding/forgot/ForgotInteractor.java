package com.skeleton.mvp.ui.onboarding.forgot;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;

/**
 * Created by sumitthakur on 17/12/17.
 */

public interface ForgotInteractor {

    /**
     * @param email                  the provided email
     * @param forgotPasswordListener listner
     */
    void forgotPassword(final String email, final ForgotPasswordListener forgotPasswordListener);

    /**
     * SignIn listener
     */
    interface ForgotPasswordListener {

        /**
         * On SignIn success
         *
         * @param commonResponse the parsed common response object
         */
        void onforgotPasswordSuccess(final CommonResponse commonResponse);

        /**
         * On SignIn failed
         *
         * @param apiError  the parsed api error if any
         * @param throwable the generated throwable if any
         */
        void onforgotPasswordFailed(final ApiError apiError, final Throwable throwable);
    }

}
