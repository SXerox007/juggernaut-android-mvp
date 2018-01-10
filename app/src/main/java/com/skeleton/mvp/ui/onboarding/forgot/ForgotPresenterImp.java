package com.skeleton.mvp.ui.onboarding.forgot;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.util.ValidationUtil;

/**
 * Created by sumitthakur on 17/12/18.
 */

public class ForgotPresenterImp implements ForgotPresenter {

    private ForgotView forgotView;
    private ForgotInteractor forgotInteractor;

    /**
     * @param forgotView forgot view
     */
    public ForgotPresenterImp(final ForgotView forgotView) {
        this.forgotView = forgotView;
        forgotInteractor = new ForgotInteractorImpl();
    }

    @Override
    public void onForgotPasswordClicked(final String email) {
        if (!ValidationUtil.checkEmail(email)) {
            forgotView.showErrorMessage(R.string.error_invalid_email);
        }
        forgotView.showLoading();
        forgotInteractor.forgotPassword(email, new ForgotInteractor.ForgotPasswordListener() {
            @Override
            public void onforgotPasswordSuccess(final CommonResponse commonResponse) {

            }

            @Override
            public void onforgotPasswordFailed(final ApiError apiError, final Throwable throwable) {

            }
        });

    }
}
