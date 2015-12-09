package com.mark.carezoneshoper.helpers;

import android.app.Application;
import android.content.Context;

import com.marcos.autodatabases.utils.DatabaseHelper;
import com.mark.carezoneshoper.models.Category;
import com.mark.carezoneshoper.models.Item;

/**
 * Created by mark on 12/7/15.
 */
public class MyApplication extends Application{
    private static MyApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setupSingletons();
        setupDatabase();
    }

    private void setupSingletons(){
        DatabaseHelper.setupContext(this);
    }

    private void setupDatabase(){
        DatabaseHelper mDataHelper;
        mDataHelper = DatabaseHelper.getInstance();
        mDataHelper.addModel(Category.class);
        mDataHelper.addModel(Item.class);
        mDataHelper.createDatabase();
    }

    public static MyApplication getInstance(){
        return mInstance;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }
}
