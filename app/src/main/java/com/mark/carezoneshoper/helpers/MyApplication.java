package com.mark.carezoneshoper.helpers;

import android.app.Application;
import android.content.Context;

/**
 * Created by mark on 12/7/15.
 */
public class MyApplication extends Application{
    private static MyApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public static MyApplication getInstance(){
        return mInstance;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }
}
