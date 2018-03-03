package com.beginner.example3.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sahai613 on 24-11-2017.
 */

public class Children {
    @SerializedName("data")
    private Post post;
    public Post getPost(){

        return post;

    }
}
