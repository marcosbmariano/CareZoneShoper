package com.mark.carezoneshoper.models;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.marcos.autodatabases.annotations.Column;
import com.marcos.autodatabases.annotations.HasMany;
import com.marcos.autodatabases.annotations.Table;
import com.marcos.autodatabases.models.Model;

import com.mark.carezoneshoper.networkHelper.VolleyHelper;
import com.mark.carezoneshoper.singletons.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by mark on 12/7/15.
 */
@Table(name = "Category")
public class Category extends Model{

    @Column(name="name")
    private String name;

    @HasMany
    private static List<Item> items;

    public Category(){
        ////obligatory default constructor
    }

    public Category(String name){
        this.name = name;
    }

    public String getCategory(){
        return name;
    }

    public void save(){
        super.save();
    }

    public List<Item> getItems(){
        List<Item> itemsList = new ArrayList<>();
        itemsList.addAll((List<Item>) (List<?>) this.getChildren(Item.class));
        items = itemsList;
        return itemsList;
    }

    public static void getCategories(){
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        requestQueue.add(
                VolleyHelper.getGetArrayRequest(
                        getJsonArrayResponse(),
                        getErrorResponse()));

    }

    private static Response.Listener<JSONArray> getJsonArrayResponse(){
        return new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {

                if ( response != null){
                    Log.e("jsjsjsjs", response.toString());

                    JSONObject obj;
                    Category cat = null;
                    for( int i = 0; i < response.length(); i++){


                        try {
                            obj = response.getJSONObject(i);
                            String categoryName = obj.getString("category");

                            cat = Category.findCategory(categoryName);
                            if( cat == null ){
                                cat = new Category(categoryName);
                                cat.save();
                            }

                            cat.addItem(new Item(obj));
                            cat.save();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
               //ugly ugly fix

            }
        };
    }

    private static Category findCategory(String categoryName){
        List<Category> catList = Category.getCategoriesFromDB();

        for( Category category  : catList){
            if( category.getCategory().equals(categoryName)){
               return category;
            }
        }
        return null;
    }


    private static Response.ErrorListener getErrorResponse(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRRRRO", "ERRROOR " + error.toString());
            }
        };
    }


    public static List<Category> getCategoriesFromDB(){
        List<Model> list = Model.getModels(Category.class);
        List<Category> categoryListList = new ArrayList<>();
        categoryListList.addAll((List<Category>) (List<?>) list);
        return categoryListList;
    }

    public void addItem(String itemName){
        ItemBuilder builder = new ItemBuilder();
        builder.setCategory(getCategory());
        builder.setName(itemName);
        Item item = builder.build();
        item.save();
        addChildModel(item);
    }

    public void addItem(Item item){
        addChildModel(item);
        item.save();
        this.save();
    }


}
