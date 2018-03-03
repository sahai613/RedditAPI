package com.beginner.example3;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sahai613 on 23-11-2017.
 */

public class ConnectionManager {
    private static RequestQueue queue;
    public static RequestQueue getInstance(Context context){
        if (queue==null){
            queue = Volley.newRequestQueue(context);
        }
        return queue;
    }


}
