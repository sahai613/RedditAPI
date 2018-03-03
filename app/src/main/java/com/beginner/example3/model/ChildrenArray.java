package com.beginner.example3.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sahai613 on 24-11-2017.
 */

public class ChildrenArray {
    @SerializedName("children")
    private List<Children> childrenList;
    public List<Children> getChildrenList(){
        return childrenList;
    }
}
