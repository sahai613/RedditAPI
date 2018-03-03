package com.beginner.example3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.beginner.example3.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahai613 on 01-12-2017.
 */

public class RedditDAO {
    /**
     * Singleton Pattern
     */
    private static RedditDAO sInstance = null;

    public static RedditDAO getsInstance() {
        if (sInstance == null) { sInstance=new RedditDAO();
        }
        return sInstance;
    }
    public boolean storePosts(Context context,List<Post> postList){
        List<Post> storedPost=RedditDAO.getsInstance().getPostfromDB(context);
        try {
            SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
            db.beginTransaction();
            for (Post post : postList) {
                boolean isinDB=false;
                for (Post storedPosts : storedPost){
                    if(post.getTitle().equals(storedPosts.getTitle())&&(post.getThumbnail().equals((storedPosts.getThumbnail()))) ){
                        isinDB=true;
                    }

                }
                if(!isinDB) {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseContract.PostTable.Title,post.getTitle());
                    cv.put(DatabaseContract.PostTable.link,post.getPermalink());
                    cv.put(DatabaseContract.PostTable.Imagelink,post.getThumbnail());
                    db.insert(DatabaseContract.PostTable.Table_Name, null, cv);
                    Log.d("DB Operations","Data Inserted");
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public List<Post> getPostfromDB(Context context){
        SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
        Cursor c = db.query(DatabaseContract.PostTable.Table_Name, null,null,null,null,null,null);
        c.moveToFirst();
        List<Post> postList1=new ArrayList<>();
        while (c.moveToNext()){
            String title=c.getString(c.getColumnIndex(DatabaseContract.PostTable.Title));
            String link=c.getString(c.getColumnIndex(DatabaseContract.PostTable.link));
            String Imagelink=c.getString(c.getColumnIndex(DatabaseContract.PostTable.Imagelink));
            Post post=new Post(link,Imagelink,title);
            postList1.add(post);
        }
        c.close();
        db.close();
        return postList1;

    }

}
