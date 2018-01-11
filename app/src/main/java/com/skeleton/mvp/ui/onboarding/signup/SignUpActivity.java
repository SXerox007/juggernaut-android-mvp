package com.skeleton.mvp.ui.onboarding.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.util.EditTextUtil;

/**
 * Developer: Click Labs
 */
public class SignUpActivity extends BaseActivity implements SignUpView, View.OnClickListener {
    private AppCompatEditText etEmail, etName, etPassword, etRePassword;
    private AppCompatButton btnSignUp;
    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();


    }

    /**
     * initialization
     */
    private void init() {
        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        signUpPresenter = new SignUpPresenterImpl(this,new SignUpInteractorImpl(RestClient.getRetrofitBuilder()));
        findViewById(R.id.btnSignUp).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                signUpPresenter.onSignUpClicked(EditTextUtil.getTextFromEditText(etEmail), EditTextUtil.getTextFromEditText(etName)
                        , EditTextUtil.getTextFromEditText(etPassword), EditTextUtil.getTextFromEditText(etRePassword));
                break;
            default:
        }

    }

}
