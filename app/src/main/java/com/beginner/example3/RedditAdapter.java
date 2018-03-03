package com.beginner.example3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beginner.example3.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sahai613 on 26-11-2017.
 */

public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.MyViewHolder> {
    List<Post> mPostList;
    Context context;



    public RedditAdapter(List<Post> postlist, Context context){
        mPostList=postlist;

        this.context=context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_post,parent,false);

        return new MyViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.mTextViewPostName.setText(mPostList.get(position).getTitle());
        Picasso.with(holder.imageViewThumbnail.getContext()).load(mPostList.get(position).getThumbnail()).into(holder.imageViewThumbnail);
        holder.mTextViewPostName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post link = mPostList.get(position);
                String link1 = link.getPermalink();
                String link2= "https://www.reddit.com"+link1;
                openbrower(link2);
            }
            public void openbrower(String link){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            holder.mTextViewPostName.getContext().startActivity(browserIntent);
        }

        });
    }


    @Override
    public int getItemCount() {
        return mPostList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView mTextViewPostName;
        ImageView imageViewThumbnail;
        Context context;


        public MyViewHolder(View itemView,Context context) {
            super(itemView);
            this.context=context;



            mTextViewPostName=itemView.findViewById(R.id.rowTextviewName);
            imageViewThumbnail=itemView.findViewById(R.id.Imageviewthumbnail);

        }


    }
}
