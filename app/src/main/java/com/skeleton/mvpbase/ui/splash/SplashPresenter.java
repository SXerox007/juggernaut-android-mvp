package com.skeleton.mvpbase.ui.splash;

import com.skeleton.mvpbase.ui.base.BasePresenter;

/**
 * Created by cl-macmini-01 on 9/19/17.
 */

public interface SplashPresenter extends BasePresenter {

    /**
     * Init the splash presenter
     */
    void init();

    /**
     * Registers for fcm token
     */
    void registerForFcmToken();

    /**
     * Root confirmation listener. The user can either
     * choose to proceed or quit
     */
    interface RootConfirmationListener {

        /**
         * On proceed despite root
         */
        void onProceed();

        /**
         * On exit
         */
        void onExit();

    }
}
