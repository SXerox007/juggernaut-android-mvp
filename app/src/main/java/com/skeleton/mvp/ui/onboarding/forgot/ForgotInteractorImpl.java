package com.skeleton.mvp.ui.onboarding.forgot;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ResponseResolver;
import com.skeleton.mvp.data.network.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sumitthakur on 17/12/18.
 */

public class ForgotInteractorImpl implements ForgotInteractor {

    @Override
    public void forgotPassword(final String email, final ForgotPasswordListener forgotPasswordListener) {

        Map<String, String> map = new HashMap<>();

        RestClient.getApiInterface().signUp(map).enqueue(new ResponseResolver<CommonResponse>() {

            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                forgotPasswordListener.onforgotPasswordSuccess(commonResponse);
            }

            @Override
            public void onError(final ApiError error) {
                forgotPasswordListener.onforgotPasswordFailed(error, null);
            }

            @Override
            public void onFailure(final Throwable throwable) {
                forgotPasswordListener.onforgotPasswordFailed(null, throwable);
            }

        });


    }
}
