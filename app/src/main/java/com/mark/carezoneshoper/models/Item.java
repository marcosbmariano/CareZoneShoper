package com.mark.carezoneshoper.models;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.marcos.autodatabases.annotations.Column;
import com.marcos.autodatabases.annotations.Table;
import com.marcos.autodatabases.models.Model;
import com.mark.carezoneshoper.networkHelper.VolleyHelper;
import com.mark.carezoneshoper.singletons.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mark on 12/8/15.
 */
@Table(name = "Item")
public class Item extends Model {
    final private static String CATEGORY = "category";
    final private static String CREATED_AT = "created_at";
    final private static String SERVER_ID = "id";
    final private static String NAME = "name";
    final private static String UPDATED_AT = "updated_at";
    final private static String USER_ID ="user_id";

    @Column(name="name", notNull = false)
    private String name;

    @Column(name="created_at")
    private String createdAt;


    @Column(name="updated_at")
    private String updated_at;

    @Column(name="cagetory", notNull = false)
    private String category;

    @Column(name= "server_id")
    private long serverId;


    //obligatory default constructor
    public Item(){

    }

    public Item(JSONObject obj){
        this();
        setFieldsFromJSOn(obj);
    }


    public void save(){
        super.save();

        if (!isItemSaved()){
            saveToServer();
        }
    }

    private void saveToServer(){
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        requestQueue.add(
                VolleyHelper.getPostRequest(
                        this.toJSon(),
                        getJsonResponse(),
                        getErrorResponse()));
    }


    public void update(){


    }
    public void deleteItem(){

    }

    private Response.Listener<JSONObject> getJsonResponse(){
        return new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                if ( response != null){

                    setFieldsFromJSOn(response);

                    Log.e("Inide Response", "Response " + response.toString() + "\n " + getServerId());

                }

            }
        };

    }



    private Response.Listener<String> getStringResponse() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {

                }

            }
        };
    }

    private Response.ErrorListener getErrorResponse(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Log.e("ERRRRRO", "ERRROOR " + error.toString());

            }
        };
    }
    private void setFieldsFromJSOn( JSONObject json){

        try {
            setCategory(json.getString(CATEGORY));
            setCreatedAt(json.getString(CREATED_AT));
            setServerId(json.getInt(SERVER_ID));
            setUpdated_at(json.getString(UPDATED_AT));
            setName(json.getString(NAME));
            save();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public JSONObject toJSon(){
        JSONObject result = new JSONObject();
        Map<String, String> params = new HashMap<>();
        params.put(NAME, getName());
        params.put(CATEGORY, getCategory());
        try {
            result.put("item", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Json", result.toString());
        return result;
    }


    private boolean isItemSaved(){
        return serverId != 0;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
