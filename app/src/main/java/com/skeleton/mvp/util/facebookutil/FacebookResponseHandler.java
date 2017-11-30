package com.skeleton.mvp.util.facebookutil;

import com.facebook.FacebookException;

/**
 * Developer: Click Labs
 */
public interface FacebookResponseHandler {


    /**
     * On success.
     *
     * @param fbUserDetails the fb user details
     */
    void onSuccess(FbUserDetails fbUserDetails);

    /**
     * On cancel.
     *
     * @param msg the msg
     */
    void onCancel(String msg);

    /**
     * On error.
     *
     * @param e the e
     */
    void onError(FacebookException e);
}
