package com.skeleton.mvp;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.skeleton.mvp.util.Foreground;

import io.fabric.sdk.android.Fabric;
import java.lang.ref.WeakReference;

import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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
        Fabric.with(this, new Crashlytics());
        Foreground.init(this);
        Paper.init(this);
        mWeakReference = new WeakReference<Context>(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
