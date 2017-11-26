package com.skeleton.mvp.ui.base;

/**
 * Created by cl-macmini-01 on 9/27/17.
 */

public interface BaseView {

    /**
     * Checks if network connection is available
     *
     * @return if connectivity is available
     */
    boolean isNetworkConnected();

    /**
     * Show loader
     */
    void showLoading();

    /**
     * Hide loader
     */
    void hideLoading();

    /**
     * Show error message
     *
     * @param stringId the message id
     */
    void showErrorMessage(int stringId);

    /**
     * Show error message
     *
     * @param message the message to show
     */
    void showErrorMessage(String message);
}
