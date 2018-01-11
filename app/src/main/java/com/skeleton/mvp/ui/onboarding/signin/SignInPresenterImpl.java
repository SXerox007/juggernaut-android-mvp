package com.skeleton.mvp.ui.onboarding.signin;


import android.support.annotation.NonNull;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.ui.base.BasePresenterImpl;
import com.skeleton.mvp.util.ValidationUtil;

/**
 * Developer: Click Labs
 */

public class SignInPresenterImpl extends BasePresenterImpl implements SignInPresenter {

    private SignInView mSignInView;
    private SignInInteractor mSignInInteractor;

    /**
     * Constructor
     *
     * @param signInView        the associated SignIn view
     * @param mSignInInteractor sign in interactor
     */
    public SignInPresenterImpl(@NonNull final SignInView signInView, @NonNull final SignInInteractorImpl mSignInInteractor) {
        this.mSignInView = signInView;
        this.mSignInInteractor = mSignInInteractor;

    }

    @Override
    public void onSignInClicked(final String email, final String password) {

        // checking for validation
        if (!ValidationUtil.checkEmail(email)) {
            mSignInView.showErrorMessage(R.string.error_invalid_email);
            return;
        }

        if (!ValidationUtil.checkPassword(password)) {
            mSignInView.showErrorMessage(R.string.error_invalid_password);
            return;
        }

        mSignInView.showLoading();
        mSignInInteractor.login(email, password, new SignInInteractor.SignInListener() {
            @Override
            public void onSignInSuccess(final CommonResponse commonResponse) {
                //todo handle success
            }

            @Override
            public void onSignInFailed(final ApiError apiError, final Throwable throwable) {

                if (isViewAttached()) {
                    mSignInView.hideLoading();
                    if (apiError != null) {
                        mSignInView.showErrorMessage(apiError.getMessage());
                    } else {
                        // resolve error through throwable
                        mSignInView.showErrorMessage(parseThrowableMessage(throwable));

                    }
                }
            }
        });
    }


}
