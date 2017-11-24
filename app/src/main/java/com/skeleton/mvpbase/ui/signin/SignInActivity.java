package com.skeleton.mvpbase.ui.signin;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.skeleton.mvpbase.R;
import com.skeleton.mvpbase.ui.base.BaseActivity;


/**
 * Created by cl-macmini-01 on 9/20/17.
 */

public class SignInActivity extends BaseActivity implements View.OnClickListener, SignInView {

    private EditText mEdtEmail;
    private EditText mEdtPassword;
    private SignInPresenter mSignInPresenter;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
        mSignInPresenter = new SignInPresenterImpl(this);
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

        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mEdtEmail = (EditText) findViewById(R.id.edtEmail);

        mEdtPassword.setTypeface(Typeface.DEFAULT);
        mEdtPassword.setTransformationMethod(new PasswordTransformationMethod());

        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {

            case R.id.btnSignIn:

                mSignInPresenter.onSignInClicked(mEdtEmail.getText().toString().trim(),
                        mEdtPassword.getText().toString().trim());

                break;

            default:

                break;
        }
    }


}
