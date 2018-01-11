package com.skeleton.mvp.ui.onboarding.signup;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.ui.base.BasePresenterImpl;
import com.skeleton.mvp.util.ValidationUtil;

/**
 * Created by sumitthakur on 11/12/17..
 */

public class SignUpPresenterImpl extends BasePresenterImpl implements SignUpPresenter {

    private SignUpView mSignUpView;
    private SignUpInteractor signUpInteractor;


    /**
     *
     * @param signUpView
     * @param signUpInteractor
     */
    public SignUpPresenterImpl(final SignUpView signUpView, final SignUpInteractorImpl signUpInteractor) {
        this.mSignUpView = signUpView;
        this.signUpInteractor = signUpInteractor;
    }

    @Override
    public void onSignUpClicked(final String email, final String name, final String password, final String rePassword) {

        if (!ValidationUtil.checkEmail(email)) {
            mSignUpView.showErrorMessage(R.string.error_invalid_email);
        } else if (ValidationUtil.validateName(name)) {
            mSignUpView.showErrorMessage(R.string.error_invalid_name);
        } else if (!ValidationUtil.checkPassword(password)) {
            mSignUpView.showErrorMessage(R.string.error_invalid_password);
        } else if (!ValidationUtil.checkPassword(rePassword)) {
            mSignUpView.showErrorMessage(R.string.error_invalid_re_password);
        } else if (matchPassword(password, rePassword)) {
            mSignUpView.showErrorMessage(R.string.error_match_password);
        }
        mSignUpView.showLoading();
        signUpInteractor.signUp(email, password, name, new SignUpInteractor.SignUpListener() {
            @Override
            public void onSignUpSuccess(final CommonResponse commonResponse) {

            }

            @Override
            public void onSignUpFailed(final ApiError apiError, final Throwable throwable) {

            }
        });
    }


    /**
     * @param password   password
     * @param rePassword re-Password
     */
    private boolean matchPassword(final String password, final String rePassword) {
        return password.equals(rePassword);
    }


}
