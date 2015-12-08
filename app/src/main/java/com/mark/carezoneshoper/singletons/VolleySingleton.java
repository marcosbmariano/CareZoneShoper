package com.mark.carezoneshoper.singletons;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mark.carezoneshoper.helpers.MyApplication;

import org.json.JSONObject;

/**
 * Created by mark on 12/7/15.
 */
public class VolleySingleton {
    private RequestQueue mRequestQueue;

    private static Context mContext;

    private VolleySingleton(Context context){
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
    }

    public static synchronized VolleySingleton getInstance( ){
        return Holder.mInstance;

    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    private static class Holder{
        private static VolleySingleton mInstance =
                new VolleySingleton(MyApplication.getAppContext());
    }





}
