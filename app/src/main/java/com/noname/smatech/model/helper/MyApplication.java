package com.noname.smatech.model.helper;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {

    public static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication mInstance;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
       // Fabric.with(this, new Crashlytics());
        context = getApplicationContext();
        mInstance = this;
        PreferenceHelper preferenceHelper = new PreferenceHelper(mInstance);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}

