package com.skeleton.mvp.ui.onboarding.signin;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiInterface;
import com.skeleton.mvp.data.network.ResponseResolver;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * Developer: Click Labs
 */

public class SignInInteractorImpl implements SignInInteractor {
    private ApiInterface mApiInterface;
    private Retrofit mRetrofit;

    /**
     *
     * @param retrofitBuilder retrofit Builder
     */
    public SignInInteractorImpl(final Retrofit retrofitBuilder) {
        this.mRetrofit = retrofitBuilder;
        this.mApiInterface = mRetrofit.create(ApiInterface.class);
    }

    @Override
    public void login(final String email, final String password, final SignInListener signInListener) {

        Map<String, String> map = new HashMap<>();

        // todo add params and hit api

        // dummy implementation
        mApiInterface.signIn(map).enqueue(new ResponseResolver<CommonResponse>(mRetrofit) {

            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                signInListener.onSignInSuccess(commonResponse);
            }

            @Override
            public void onError(final ApiError error) {
                signInListener.onSignInFailed(error, null);
            }

            @Override
            public void onFailure(final Throwable throwable) {
                signInListener.onSignInFailed(null, throwable);
            }
        });
    }
}
