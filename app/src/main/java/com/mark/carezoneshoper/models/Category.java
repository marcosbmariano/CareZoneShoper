package com.mark.carezoneshoper.models;

import com.marcos.autodatabases.annotations.Column;
import com.marcos.autodatabases.annotations.HasMany;
import com.marcos.autodatabases.annotations.Table;
import com.marcos.autodatabases.models.Model;
import com.mark.carezoneshoper.models.Item;

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
    List<Item> items;

    public Category(){
        ////obligatory default constructor
    }

    public Category(String name){
        this.name = name;
    }

    public void save(){
        super.save();
    }

    public List<Item> getItems(){
        //List<Model> list = this.getChildren(Items.class);
        List<Item> itemsList = new ArrayList<>();
        itemsList.addAll((List<Item>)(List<?>) this.getChildren(Item.class));

        return itemsList;
    }

    public static List<Category> getCategories(){
        List<Model> list = Model.getModels(Category.class);
        List<Category> categoryListList = new ArrayList<>();
        categoryListList.addAll((List<Category>)(List<?>) list);
        return categoryListList;
    }

    public void addItem(String itemName){
        ItemBuilder builder = new ItemBuilder();
        builder.setCategory(name);
        builder.setName(itemName);
        Item item = builder.build();
        item.save();
        addChildModel(item);
    }

    public String getName(){
        return name;
    }





//    String category;
//    String createdAt;
//    String updatedAt;
//    int id;
//    String name;
//    int user_id;




}
