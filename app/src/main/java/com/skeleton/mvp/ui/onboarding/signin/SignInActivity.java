package com.skeleton.mvp.ui.onboarding.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;


/**
 * Developer: Click Labs
 */
public class SignInActivity extends BaseActivity implements View.OnClickListener, SignInView {

    private AppCompatEditText etEmail, etPassword;
    private SignInPresenter mSignInPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
        mSignInPresenter = new SignInPresenterImpl(this, new SignInInteractorImpl(RestClient.getRetrofitBuilder()));
        mSignInPresenter.onAttach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSignInPresenter.onDetach();
    }

    /**
     * Init the views
     */
    private void init() {
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);

        findViewById(R.id.btnSignIn).setOnClickListener(this);
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                mSignInPresenter.onSignInClicked(etEmail.getText().toString().trim(),
                        etPassword.getText().toString().trim());
                break;
            default:
                break;
        }
    }
}
