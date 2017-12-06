package com.skeleton.mvp.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
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
    private static final String DEFAULT_CHANNEL_ID = "default_01";
    private static final long[] NOTIFICATION_VIBRATION_PATTERN = new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400};
    private static NotificationManager mNotificationManager;

    /**
     * Clear notifications
     */
    public static void clearNotification() {
        if (mNotificationManager != null) {
            mNotificationManager.cancelAll();
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
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final Intent notificationIntent = new Intent(getApplicationContext(), SplashActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // The user-visible name of the channel.
            CharSequence name = getString(R.string.notification_channel_default);
            // The user-visible description of the channel.
            String description = getString(R.string.notification_channel_description_default);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(DEFAULT_CHANNEL_ID, name, importance);
            // Configure the notification channel.
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(NOTIFICATION_VIBRATION_PATTERN);
            mNotificationManager.createNotificationChannel(mChannel);
        }


        Notification mNotification = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(data.get(AppConstant.MESSAGE)))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle(getString(R.string.app_name))
                .setContentText(data.get(AppConstant.MESSAGE))
                .setContentIntent(pi)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true)
                .setChannelId(DEFAULT_CHANNEL_ID)
                .build();
        mNotificationManager.notify(0, mNotification);
    }
}
