package com.skeleton.mvp.data.db;


import io.paperdb.Paper;

/**
 * Developer: CL
 */
public final class CommonData {

    private static final String PAPER_DEVICE_TOKEN = "paper_device_token";

    /**
     * Prevent instantiation
     */
    private CommonData() {
    }

    /**
     * Update fcm token.
     *
     * @param token the fcm token
     */
    public static void updateFcmToken(final String token) {
        Paper.book().write(PAPER_DEVICE_TOKEN, token);
    }

    /**
     * Gets fcm token.
     *
     * @return the fcm token
     */
    public static String getFcmToken() {
        return Paper.book().read(PAPER_DEVICE_TOKEN);
    }

}
