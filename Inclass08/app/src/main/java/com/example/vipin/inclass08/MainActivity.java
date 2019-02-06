package com.example.vipin.inclass08;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProgressFragment.OnFragmentInteractionListener, FirstFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener {

    ScrollView scrollView;
    String dish;
    final int[] i = {0};
    int count = 0;
    EditText disheditText;
    ArrayList<Recipe> list;
    Fragment fragment1, fragment2, fragment3;
    android.app.FragmentManager fragmentManager = getFragmentManager();

/*
    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount()>0){

            fragmentManager.popBackStack();
            */
/*for(int i =0; i <fragmentManager.getBackStackEntryCount();i++){
                fragmentManager.popBackStack();
                Log.d("demo",String.valueOf(i));
            }*//*

            */
/*fragmentManager.beginTransaction().remove(fragment3).commit();
*//*
}
        else {
            super.onBackPressed();
        }
    }
*/

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    static ArrayList<String> arrayList;
    String url = null;
    FirstFragment fragment;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new FirstFragment();
        fragment2 = new ProgressFragment();
        fragment3 = new SecondFragment();

        fragmentManager.beginTransaction()
                .add(R.id.container, fragment1, "first")
                .commit();
        fragment = new FirstFragment();
    }

    @Override
    public void onFragmentInteraction2(ArrayList<Recipe> list1) {
        list = list1;

    }

    @Override
    public void gotofinalfragment() {
      /*  fragmentManager.beginTransaction()
                .remove(fragment2)
                .addToBackStack(null)
                .commit();
*/
        fragmentManager.beginTransaction().replace((R.id.container), fragment3, "third").commit();

    }

    @Override
    public String geturl() {
        return this.url;
    }

    @Override
    public void gotofirst() {
        fragmentManager.popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new FirstFragment(), "first")
                .commit();

    }

    @Override
    public void onFragmentInteraction1(ArrayList<String> list1) {
        arrayList = list1;

    }

    @Override
    public void seturl(String url) {
        this.url = url;
    }

    @Override
    public void gotonextfragment() {

//        fragmentManager.beginTransaction().remove(fragment1);

        fragmentManager.beginTransaction().replace((R.id.container), fragment2, "second").addToBackStack(null).commit();

    }


    public ArrayList<Recipe> senddata() {
        return list;
    }

    @Override
    public void onFragmentInteraction(ArrayList<Recipe> uri) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void gotoFirstFragment() {
        fragmentManager.popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new FirstFragment(), "first")
                .commit();


    }
}

