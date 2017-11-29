package com.skeleton.mvp.util.facebookutil;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.skeleton.mvp.R;
import com.skeleton.mvp.util.Log;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;


/**
 * Developer: Saurabh Verma
 * Dated: 07/07/17
 */
public final class FacebookManager {
    private static final String TAG = FacebookManager.class.getSimpleName();

    private static final Collection<String> PERMISSIONS_LIST = Arrays.asList("public_profile", "user_friends", "email");
    //fields
    private static final String FIELDS = "fields";
    private static final String FIELDS_LIST = "id,first_name,last_name,name,email,gender,picture";
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String GENDER = "gender";

    private Fragment fragment;
    private Activity mActivity;

    /**
     * Instantiates a new Facebook manager.
     *
     * @param fragment the fragment
     */
    public FacebookManager(final Fragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Instantiates a new Facebook manager.
     *
     * @param mActivity the m activity
     */
    public FacebookManager(final Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * Gets fb user details.
     *
     * @param mCallbackManager        the m callback manager
     * @param facebookResponseHandler the facebook response handler
     */
    public void getFbUserDetails(final CallbackManager mCallbackManager,
                                 final FacebookResponseHandler facebookResponseHandler) {
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        }
        if (mActivity != null) {
            LoginManager.getInstance().logInWithReadPermissions(mActivity, PERMISSIONS_LIST);
        } else {
            LoginManager.getInstance().logInWithReadPermissions(fragment, PERMISSIONS_LIST);
        }
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                getUserInfoFromAccessToken(loginResult, facebookResponseHandler);
            }

            @Override
            public void onCancel() {
                if (mActivity != null) {
                    facebookResponseHandler.onCancel(mActivity.getString(R.string.error_fb_login_failed));
                } else {
                    facebookResponseHandler.onCancel(fragment.getString(R.string.error_fb_login_failed));
                }
            }

            @Override
            public void onError(final FacebookException error) {
                facebookResponseHandler.onError(error);
            }
        });
    }

    /**
     * Get fb user infro from access token
     *
     * @param loginResult             the login result
     * @param facebookResponseHandler the facebook response handler
     */
    private void getUserInfoFromAccessToken(final LoginResult loginResult,
                                            final FacebookResponseHandler facebookResponseHandler) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject object,
                                            final GraphResponse response) {
                        if (response.getError() != null) {
                            //error
                            Log.e(TAG, response.getError().toString());
                        } else {
                            try {
                                String email = "";
                                if (object.has(EMAIL)) {
                                    email = object.getString(EMAIL);
                                }
                                Log.i(TAG, object + "");
                                FbUserDetails mFbUserDetails = new FbUserDetails(object.getString(ID),
                                        loginResult.getAccessToken().getToken(),
                                        object.getString(FIRST_NAME),
                                        object.getString(LAST_NAME),
                                        email,
                                        object.getString(GENDER),
                                        getUserProfileImageUrl(object.getString(ID)));
                                facebookResponseHandler.onSuccess(mFbUserDetails);
                            } catch (final Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString(FIELDS, FIELDS_LIST);
        request.setParameters(parameters);
        request.executeAsync();
    }

    /**
     * @param id facbook id
     * @return url of user fb image
     */
    private String getUserProfileImageUrl(final String id) {
        return "https://graph.facebook.com/" + id + "/picture?type=large";
    }
}
