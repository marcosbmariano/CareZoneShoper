package com.mark.carezoneshoper.activities;

import android.app.DownloadManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.mark.carezoneshoper.R;
import com.mark.carezoneshoper.fragments.RecyclerVFragment;
import com.mark.carezoneshoper.networkHelper.VolleyHelper;
import com.mark.carezoneshoper.singletons.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
    private RecyclerVFragment mRecView;
    private FloatingActionButton mFab;
    private EditText mEDTNewCategorieField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWidgets();



    }


    private void setupWidgets(){
        mRecView = (RecyclerVFragment)
                getSupportFragmentManager().findFragmentById(R.id.frgMainRecView);

        mEDTNewCategorieField = (EditText)findViewById(R.id.edtNewCategory);


        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecView.addItem(mEDTNewCategorieField.getText().toString());
                mEDTNewCategorieField.setText("");
                //setRequest();
            }
        });
    }



    String test; //TODO remove this
    private void setRequest() {

        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

//1294
//        requestQueue.add(VolleyHelper.getPostRequest(
//                getJsonObject(),
//                getJsonResponse(),
//                getErrorResponse()));

        //requestQueue.add(VolleyHelper.getDeleteRequest(1293, getStringResponse(), getErrorResponse()));

//        requestQueue.add(VolleyHelper.getPutRequest(1294, getJsonObject(),
//                getJsonResponse(), getErrorResponse()));

       // requestQueue.add(VolleyHelper.getGetArrayRequest(getJsonArrayResponse(), getErrorResponse()));
       // requestQueue.add(VolleyHelper.getGetSingleRequest(1294, getJsonResponse(), getErrorResponse()));
    }








}









