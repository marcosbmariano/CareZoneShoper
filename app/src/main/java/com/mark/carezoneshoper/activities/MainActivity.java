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
import com.mark.carezoneshoper.singletons.VolleySingleton;

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





//        JSONObject jsonObject = new JSONObject();
//        LinkedHashMap m1 = new LinkedHashMap();
//        m1.put("name","Kool-aid" );
//        m1.put("category", "Beverages");
//        try {
//            jsonObject.put("item", m1);
//        } catch (JSONException e) {
//
//            e.printStackTrace();
//        }

        //{"item":"{name=Kool-aid, category=Beverages}"}

        String input = "{item:{name: Kool-aid, category : Beverages}}";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(input);
            test = jsonObject.toString();
        } catch (JSONException e) {

            Toast.makeText(MainActivity.this.getApplicationContext(),
                    "Faiiiiled", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


        ;

//        {
//            "item": {
//            "name": "Kool-aid",
//            "category": "Beverages"
//        }
//        }

        //http://czshopper.herokuapp.com/pages/instructions

        String uri = Uri.parse("http://czshopper.herokuapp.com/")
                .buildUpon()
                .appendQueryParameter("X-CZ-Authorization", "5K5u2pTzkvFiU7ChfgKy")
                .appendQueryParameter("Accept", "application/json")
                .build().toString();




        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this.getApplicationContext(),
                        "Worked \n"  , Toast.LENGTH_LONG).show();
                if ( response != null){
                    MainActivity.this.test += " " + response.toString();
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this.getApplicationContext(),
                        "Faiiiiled \n"  , Toast.LENGTH_LONG).show();

                MainActivity.this.test += " " + error.toString();


            }
        };


        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,uri,jsonObject, response, errorListener){
//            @Override
//                    public String getBodyContentType() {
//                        return "application/json; charset=utf-8";
//                    }
        };

        mEDTNewCategorieField.setText(test + "\n" + request.toString());

        requestQueue.add(request);

    }




}






//        //URL url = new URL()
//        StringRequest request = new StringRequest(Request.Method.GET, uri,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(MainActivity.this.getApplicationContext(),
//                                "Worked \n" + response, Toast.LENGTH_LONG).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this.getApplicationContext(),
//                                "ERROR ", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//        );
//
//        requestQueue.add(request);



