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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mark.carezoneshoper.R;
import com.mark.carezoneshoper.fragments.RecyclerVFragment;
import com.mark.carezoneshoper.singletons.VolleySingleton;

import java.net.URL;

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

    private void setRequest() {

        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
                //Volley.newRequestQueue(this);

        String uri = Uri.parse("http://www.google.com").buildUpon().build().toString();

        //URL url = new URL()
        StringRequest request = new StringRequest(Request.Method.GET, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this.getApplicationContext(),
                                "Worked \n" + response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this.getApplicationContext(),
                                "ERROR ", Toast.LENGTH_LONG).show();
                    }
                }

        );

        requestQueue.add(request);

//        String uri = Uri.parse("http://...")
//                .buildUpon()
//                .appendQueryParameter("key", "val")
//                .build().toString();


    }


}
