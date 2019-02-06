package com.example.vipin.inclass08;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    private ArrayList<Recipe> mDataset;
    public Context context;

    public MyAdapter2(ArrayList<Recipe> mDataset, Context applicationContext) {
        this.context = applicationContext;
        this.mDataset = mDataset;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public EditText editText;
        ImageButton btn1, btn2, btn3, btn4;
        Button finish;
        TextView titletv;
        TextView ingredientstv;
        TextView urltv;
        ImageView image;

        public ViewHolder(View v) {
            super(v);
            view = v;
            editText = v.findViewById(R.id.ed2);
            titletv = (TextView) view.findViewById(R.id.titletv);
            image = (ImageView) view.findViewById(R.id.imageView);
            urltv = (TextView) view.findViewById(R.id.urltv);
            ingredientstv = (TextView) view.findViewById(R.id.ingredientstv);

        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_item2, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.titletv.setText("Title: " + mDataset.get(position).getTitle());
        holder.urltv.setText("url: " + mDataset.get(position).getUrl());
        Picasso.with(context).load(mDataset.get(position).getImage()).into(holder.image);

        holder.ingredientstv.setText(mDataset.get(position).getIngredients());

        holder.urltv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(mDataset.get(position).getUrl()));
                context.startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}


