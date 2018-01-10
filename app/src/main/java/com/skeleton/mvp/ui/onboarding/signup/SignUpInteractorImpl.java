package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ResponseResolver;
import com.skeleton.mvp.data.network.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sumitthakur on 11/01/17.
 */

public class SignUpInteractorImpl implements SignUpInteractor {

    @Override
    public void signUp(final String email, final String password, final String name, final SignUpListener signUpListener) {
        Map<String, String> map = new HashMap<>();

        RestClient.getApiInterface().signUp(map).enqueue(new ResponseResolver<CommonResponse>() {

            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                signUpListener.onSignUpSuccess(commonResponse);
            }

            @Override
            public void onError(final ApiError error) {
                signUpListener.onSignUpFailed(error, null);
            }

            @Override
            public void onFailure(final Throwable throwable) {
                signUpListener.onSignUpFailed(null, throwable);
            }

        });

    }
}
