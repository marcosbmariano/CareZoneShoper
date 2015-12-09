package com.mark.carezoneshoper.models;

import com.marcos.autodatabases.annotations.Column;
import com.marcos.autodatabases.annotations.Table;
import com.marcos.autodatabases.models.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mark on 12/8/15.
 */
@Table(name = "Item")
public class Item extends Model {

//    String category;
//    String createdAt;
//    String updatedAt;
//    int id;
//    String name;
//    int user_id;

    //obligatory default constructor
    public Item(){

    }


    public void save(){
        super.save();
    }

    public void update(){


    }
    public void deleteItem(){

    }

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

    public JSONObject toJSon(){
        JSONObject result = new JSONObject();
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put(category, category);
        try {
            result.put("item", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }




    private void saveToServer(){

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
