package com.skeleton.mvp.ui.onboarding.forgot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skeleton.mvp.R;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.ui.customview.MaterialEditText;
import com.skeleton.mvp.util.EditTextUtil;

/**
 * Created by sumitthakur on 17/12/17.
 */
public class ForgotActivity extends BaseActivity implements ForgotView, View.OnClickListener {

    private MaterialEditText metEmail;
    private Button btnForgotPassword;
    private ForgotPresenter forgotPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        init();
    }

    /**
     * initilization
     */
    private void init() {
        forgotPresenter = new ForgotPresenterImp(this);
        metEmail = findViewById(R.id.metEmail);
        findViewById(R.id.btnForgotPassword).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btnForgotPassword:
                forgotPresenter.onForgotPasswordClicked(EditTextUtil.getTextFromEditText(metEmail));
                break;
            default:
        }

    }
}
