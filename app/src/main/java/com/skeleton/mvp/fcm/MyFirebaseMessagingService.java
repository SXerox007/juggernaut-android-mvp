package com.skeleton.mvp.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.skeleton.mvp.R;
import com.skeleton.mvp.ui.splash.SplashActivity;
import com.skeleton.mvp.util.AppConstant;
import com.skeleton.mvp.util.Foreground;

import java.util.Map;

/**
 * Developer: Click Labs
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getName();
    private static final String CHANNEL_ID = "default_01";
    private static NotificationManager notificationManager;

    /**
     * Clear notifications
     */
    public static void clearNotification() {
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {

        // Handle data payload of FCM messages.
        Log.d(TAG, getString(R.string.log_fcm_message_id) + remoteMessage.getMessageId());
        Log.d(TAG, getString(R.string.log_fcm_notification_message) + remoteMessage.getNotification());
        Log.d(TAG, getString(R.string.log_fcm_data) + remoteMessage.getData());
        Log.d(TAG, getString(R.string.log_fcm_data_message) + remoteMessage.getData().get(AppConstant.MESSAGE));
        /*
         * Foreground.get(getApplication()).isForeground() checks if the app is in foreground i.e visible not minimized or killed
         * if it is killed or minimized show notification
         */
        if (Foreground.get(getApplication()).isForeground()) {
            Intent mIntent = new Intent(AppConstant.NOTIFICATION_RECEIVED);
            Bundle mBundle = new Bundle();
            for (String key : remoteMessage.getData().keySet()) {
                mBundle.putString(key, remoteMessage.getData().get(key));
            }
            mIntent.putExtras(mBundle);
            LocalBroadcastManager.getInstance(this).sendBroadcast(mIntent);
        } else {
            showNotification(remoteMessage.getData());
        }
    }

    /**
     * Show notification
     *
     * @param data notification data map
     */
    public void showNotification(final Map<String, String> data) {
        final Intent notificationIntent = new Intent(getApplicationContext(), SplashActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(data.get(AppConstant.MESSAGE)))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(r.getString(R.string.app_name))
                .setContentText(data.get(AppConstant.MESSAGE))
                .setContentIntent(pi)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true)
                .build();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
