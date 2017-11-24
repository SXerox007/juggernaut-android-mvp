package com.skeleton.mvpbase.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by cl-macmini-01 on 9/28/17.
 */

public final class ValidationUtils {

    private static final int PASSWORD_LENGTH = 6;

    /**
     * Prevent instantiation
     */
    private ValidationUtils() {
    }

    /**
     * Method to validate email id
     *
     * @param email user email
     * @return whether email is valid
     */
    public static boolean checkEmail(final String email) {
        if (TextUtils.isEmpty(email) || (!email.matches(Patterns.EMAIL_ADDRESS.toString()))) {
            return false;
        }
        return true;
    }

    /**
     * Method to validate password
     *
     * @param password user entered password
     * @return whether the password is valid
     */
    public static boolean checkPassword(final String password) {
        if (TextUtils.isEmpty(password) || (password.length() < PASSWORD_LENGTH)) {
            return false;
        }
        return true;
    }


}
