package com.example.vipin.inclass08;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vipin on 10/2/2017.
 */

public class Recipe_parser_AsyncTask extends AsyncTask<String, Void, ArrayList<Recipe>> {

    ImageButton btn1, btn2, btn3, btn4;
    Button finish;
    TextView titletv;
    TextView ingredientstv;
    TextView urltv;
    ImageView image;
    TextView igtv;
    MainActivity ractivity;
    ProgressBar progressbar;

    public Recipe_parser_AsyncTask(MainActivity Recipe) {
        this.ractivity = Recipe;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /*progressbar = (ProgressBar) ractivity.findViewById(R.id.progressbar);
        progressbar.setProgress(0);
    */}

    @Override
    protected void onPostExecute(ArrayList<Recipe> recipes) {
        super.onPostExecute(recipes);
      /*progressbar.setVisibility(View.GONE);
        TextView loadingtv = (TextView) ractivity.findViewById(R.id.loadingtv);
        loadingtv.setVisibility(View.GONE);

        image = (ImageView) ractivity.findViewById(R.id.imageView);
        titletv = (TextView) ractivity.findViewById(R.id.titletv);
        finish = (Button) ractivity.findViewById(R.id.finish);
        ingredientstv = (TextView) ractivity.findViewById(R.id.ingredientstv);
        urltv = (TextView) ractivity.findViewById(R.id.urltv);
        igtv = (TextView) ractivity.findViewById(R.id.igdv);

        image.setVisibility(View.VISIBLE);
        titletv.setVisibility(View.VISIBLE);
        finish.setVisibility(View.VISIBLE);
        ingredientstv.setVisibility(View.VISIBLE);
        urltv.setVisibility(View.VISIBLE);
        igtv.setVisibility(View.VISIBLE);*//*
    */}

    @Override
    protected ArrayList<Recipe> doInBackground(String... strings) {

        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status_Code = con.getResponseCode();

            if (status_Code == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line + "\n");
                    line = reader.readLine();

                }
                return Json.RecipeJSONParser.parseRecipes(sb.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}
