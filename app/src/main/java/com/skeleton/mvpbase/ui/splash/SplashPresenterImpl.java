package com.skeleton.mvpbase.ui.splash;

import com.skeleton.mvpbase.database.CommonData;
import com.skeleton.mvpbase.fcm.FcmTokenInterface;
import com.skeleton.mvpbase.fcm.MyFirebaseInstanceIdService;
import com.skeleton.mvpbase.ui.base.BasePresenterImpl;
import com.skeleton.mvpbase.utils.RootUtils;

/**
 * Created by cl-macmini-01 on 9/19/17.
 */

class SplashPresenterImpl extends BasePresenterImpl implements SplashPresenter, FcmTokenInterface {

    private SplashView mSplashView;

    /**
     * Constructor
     *
     * @param splashView the associated splash view
     */
    SplashPresenterImpl(final SplashView splashView) {
        mSplashView = splashView;
    }

    @Override
    public void init() {

        // check for root
        if (RootUtils.isDeviceRooted()) {
            mSplashView.showDeviceRootedAlert(new RootConfirmationListener() {
                @Override
                public void onProceed() {
                    registerForFcmToken();
                }

                @Override
                public void onExit() {
                    mSplashView.exit();
                }
            });
        } else {
            registerForFcmToken();
        }

    }

    @Override
    public void registerForFcmToken() {

        if (!mSplashView.isNetworkConnected()) {
            mSplashView.showNetworkError();
            return;
        }
        if (!mSplashView.isPlayServiceAvailable()) {
            return;
        }

        // register for push
        MyFirebaseInstanceIdService.setCallback(this);
    }

    @Override
    public void onTokenReceived(final String token) {

        CommonData.updateFcmToken(token);
        //todo decide what to launch based on token
    }

    @Override
    public void onFailure() {

        if (isViewAttached()) {
            // retry
            MyFirebaseInstanceIdService.retry(this);
        }
    }
}


