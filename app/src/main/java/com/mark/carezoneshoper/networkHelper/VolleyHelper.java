package com.mark.carezoneshoper.networkHelper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mark on 12/8/15.
 */
public class VolleyHelper {
    private static final String APIKEY = "5K5u2pTzkvFiU7ChfgKy";
    private static final String URL = "http://czshopper.herokuapp.com";
    private static final String POST_GET = "/items.json";


    private VolleyHelper(){}


    //Not working
    public static JsonObjectRequest getGetSingleRequest(int id, Response.Listener<JSONObject> response,
                                                   Response.ErrorListener errorListener){
        JsonObjectRequest result =
                new JsonObjectRequest(Request.Method.GET,URL + getSingleItem(id),
                        response, errorListener){

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> header = new HashMap<>();
                        header.put("X-CZ-Authorization", APIKEY);
                        header.put("Accept", "application/json");
                        header.put("Content-Type", "application/json");
                        return header;
                    }
                };

        return result;
    }


    public static JsonArrayRequest getGetArrayRequest(Response.Listener<JSONArray> response,
                                                  Response.ErrorListener errorListener){
        JsonArrayRequest result =
                new JsonArrayRequest(Request.Method.GET, URL + POST_GET, response, errorListener){

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> header = new HashMap<>();
                        header.put("X-CZ-Authorization", APIKEY);
                        header.put("Accept", "application/json");
                        return header;
                    }
                };

        return result;
    }


    public static JsonObjectRequest getPostRequest(JSONObject jsonObject,
                                            Response.Listener<JSONObject> response,
                                            Response.ErrorListener errorListener){

        JsonObjectRequest result = new JsonObjectRequest(
                Request.Method.POST, URL + POST_GET,jsonObject, response, errorListener){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("X-CZ-Authorization", APIKEY);
                header.put("Accept", "application/json");
                header.put("Content-Type", "application/json");

                return header;
            }
        };

        return result;
    }

    public static StringRequest getDeleteRequest(int id,
                                          Response.Listener<String> response,
                                          Response.ErrorListener errorListener) {
        StringRequest result =
                new StringRequest(Request.Method.DELETE, URL + getSingleItem(id),
                        response, errorListener) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> header = new HashMap<>();
                        header.put("X-CZ-Authorization", APIKEY);
                        return header;
                    }
                };

        return result;
    }


    public static JsonObjectRequest getPutRequest(int id, JSONObject jsonObject,
                                            Response.Listener<JSONObject> response,
                                            Response.ErrorListener errorListener){

            JsonObjectRequest result = new JsonObjectRequest(
                    Request.Method.PUT,URL + getSingleItem(id),
                    jsonObject, response, errorListener){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<>();
                    header.put("X-CZ-Authorization", APIKEY);
                    header.put("Accept", "application/json");
                    header.put("Content-Type", "application/json");

                    return header;
                }
            };

            return result;
    }

    private static String getSingleItem(int id){
        return "/items/" + id + ":id.json";
    }


}
