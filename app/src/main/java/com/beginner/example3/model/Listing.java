package com.beginner.example3.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahai613 on 24-11-2017.
 */

public class Listing {
    @SerializedName("data")
    public ChildrenArray childrenArray;
    public List<Post> getPostlist(){
        List<Post> Postlist= new ArrayList<Post>();
        for(Children children:childrenArray.getChildrenList()){
            Postlist.add(children.getPost());
        }
        return Postlist;

    }
}
