package com.mark.carezoneshoper.activities;

import android.app.DownloadManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mark.carezoneshoper.R;
import com.mark.carezoneshoper.fragments.RecyclerVFragment;
import com.mark.carezoneshoper.networkHelper.VolleyHelper;
import com.mark.carezoneshoper.singletons.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
                //mRecView.addItem(mEDTNewCategorieField.getText().toString());
                // mEDTNewCategorieField.setText("");
                setRequest();
            }
        });
    }
    String test;
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

        requestQueue.add(VolleyHelper.getGetArrayRequest(getJsonArrayResponse(), getErrorResponse()));
        requestQueue.add(VolleyHelper.getGetSingleRequest(1294, getJsonResponse(), getErrorResponse()));
    }



    private JSONObject getJsonObject(){

        String input = "{item:{name: Coca-cola, category : Beverages}}";
        JSONObject result = null;
        try {
            result = new JSONObject(input);
            test = result.toString();
        } catch (JSONException e) {

            Toast.makeText(MainActivity.this.getApplicationContext(),
                    "Json Failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        return result;
    }


    private Response.Listener<JSONObject> getJsonResponse(){
        return new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this.getApplicationContext(),
                        "Worked \n"  , Toast.LENGTH_LONG).show();
                if ( response != null){
                    mEDTNewCategorieField.setText(response.toString());
                }

            }
        };

    }

    private Response.Listener<JSONArray> getJsonArrayResponse(){
        return new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this.getApplicationContext(),
                        "Worked \n"  , Toast.LENGTH_LONG).show();
                if ( response != null){
                    mEDTNewCategorieField.setText(response.toString());
                }

            }
        };

    }



    private Response.Listener<String> getStringResponse() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this.getApplicationContext(),
                        "Worked \n", Toast.LENGTH_LONG).show();
                if (response != null) {
                    mEDTNewCategorieField.setText(response.toString());
                }

            }
        };
    }

    private Response.ErrorListener getErrorResponse(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this.getApplicationContext(),
                        "Failed \n" , Toast.LENGTH_LONG).show();

                mEDTNewCategorieField.setText(test + "\n" + error.toString());

            }
        };
    }



}









