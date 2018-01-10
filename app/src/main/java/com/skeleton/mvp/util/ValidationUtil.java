package com.skeleton.mvp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Developer: Click Labs
 */

public final class ValidationUtil {

    private static final int PASSWORD_LENGTH = 6;
    private static final String NAME_VALIDATE = "[a-zA-Z]+\\.?";
    private static final String REGEX_EMAIL_ADDRESS = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
            + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
    private static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    /**
     * Prevent instantiation
     */
    private ValidationUtil() {
    }

//    /**
//     * Method to validate email id
//     *
//     * @param email user email
//     * @return whether email is valid
//     */
//    public static boolean checkEmail(final String email) {
//        if (TextUtils.isEmpty(email) && (!email.matches(Patterns.EMAIL_ADDRESS.toString()))) {
//            return false;
//        }
//        return true;
//    }

    /**
     * Method to validate email id
     *
     * @param email user email
     * @return whether email is valid
     */
    public static boolean checkEmail(final String email) {
        return !(email.trim().isEmpty() || (!email.matches(Pattern.compile(REGEX_EMAIL_ADDRESS).toString())));
    }


//    /**
//     * Method to validate password
//     *
//     * @param password user entered password
//     * @return whether the password is valid
//     */
//    public static boolean checkPassword(final String password) {
//        if (TextUtils.isEmpty(password) || (password.length() < PASSWORD_LENGTH)) {
//            return false;
//        }
//        return true;
//    }


    /**
     * Method to validate password
     *
     * @param password user entered password
     * @return whether the password is valid
     */
    public static boolean checkPassword(final String password) {
        return !(password.trim().isEmpty() || (!password.matches(REGEX_PASSWORD)));
    }


    /**
     * @param name name
     * @return the name is valid or not
     */
    public static boolean validateName(final String name) {
        Pattern pattern = Pattern.compile(NAME_VALIDATE, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }


}
