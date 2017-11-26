package com.skeleton.mvp;

import android.app.Application;
import android.content.Context;

import com.skeleton.mvp.util.Foreground;

import java.lang.ref.WeakReference;

import io.paperdb.Paper;

/**
 * Created by cl-macmini-01 on 11/23/17.
 * The Application class
 */

public class MyApplication extends Application {

    private static WeakReference<Context> mWeakReference;

    /**
     * Getter to access Singleton instance
     *
     * @return instance of MyApplication
     */
    public static Context getAppContext() {
        return mWeakReference.get();
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Foreground.init(this);
        Paper.init(this);
        mWeakReference = new WeakReference<Context>(this);
    }

}
